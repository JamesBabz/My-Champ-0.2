/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class TeamManagerController implements Initializable
{

    @FXML
    private ListView listTeams;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnStart;
    
    private final int MIN_TEAMS;
    private final int MAX_TEAMS;

    private ObservableList teams;
    private ChampModel model;
    private int selectedTeamIndex;

    public TeamManagerController()
    {
        this.MIN_TEAMS = 0;
        this.MAX_TEAMS = 16;
        model = ChampModel.getInstance();
        observableListListener(model.getTeamNames());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        btnEdit.setDisable(true);
        btnRemove.setDisable(true);
        btnStart.setDisable(true);
        listTeams.setItems(model.getTeamNames());

        listTeams.getSelectionModel().selectedItemProperty().addListener(new javafx.beans.value.ChangeListener()
        {

            @Override
            public void changed(ObservableValue selected, Object oldValue, Object newValue)
            {
                if (selected.getValue() == null)
                {
                    btnEdit.setDisable(true);
                    btnRemove.setDisable(true);
                } else
                {
                    selectedTeamIndex = listTeams.getSelectionModel().getSelectedIndex();
                    btnEdit.setDisable(false);
                    btnRemove.setDisable(false);
                }
            }

        });
    }

    private void observableListListener(ObservableList list)
    {
        list.addListener(new ListChangeListener()
        {
            @Override
            public void onChanged(ListChangeListener.Change change)
            {
                int amount = list.size();
                if (amount >= MIN_TEAMS)
                {
                    btnStart.setDisable(false);
                }
                if (amount == MAX_TEAMS)
                {
                    btnAdd.setDisable(true);
                }
            }

        });
    }

    /**
     * Opens the TeamName view when pressed
     *
     * @throws IOException
     */
    @FXML
    private void handleAddTeam()
    {
        try
        {
            openNewView("TeamNameView", "New team");
        } catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Opens the TeamName view when pressed
     *
     * @throws IOException
     */
    @FXML
    private void handleEditTeam()
    {
        model.setEditTeam(selectedTeamIndex);
        try
        {
            openNewView("TeamNameView", "Edit team");
        } catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void openNewView(String viewName, String title) throws IOException
    {
        Stage primaryStage = (Stage) listTeams.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mychamp/gui/view/" + viewName + ".fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(primaryStage);
        newStage.setTitle(title);

        newStage.show();
    }

    @FXML
    private void handleRemoveTeam()
    {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Remove team");
        alert.setHeaderText("Do you want to remove this team?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            model.removeTeam(selectedTeamIndex);
        } else
        {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    private void macros(KeyEvent key) throws IOException
    {

        if (key.isControlDown())
        {
            if (KeyCode.N == key.getCode() && !btnAdd.isDisable())
            {
                handleAddTeam();
            }

            if (KeyCode.E == key.getCode() && !btnEdit.isDisable())
            {
                handleEditTeam();
            }
        }
        if (KeyCode.DELETE == key.getCode() && !btnRemove.isDisable())
        {
            handleRemoveTeam();
        }
    }

    @FXML
    private void handleStart()
    {

        Stage primaryStage = (Stage) listTeams.getScene().getWindow();
        primaryStage.close();

        try
        {
            openNewView("GroupView", "");
        } catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
