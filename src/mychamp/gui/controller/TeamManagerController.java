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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mychamp.bll.TeamManager;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class TeamManagerController implements Initializable {

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

    private final static int MIN_TEAMS = 12;
    private final static int MAX_TEAMS = 16;

    private ObservableList teams;
    private ChampModel model;
    private TeamManager teamManager;

    private int selectedTeamIndex;
    @FXML
    private AnchorPane anchorPane;

    public TeamManagerController()
    {
        model = ChampModel.getInstance();
        observableListListener(model.getTeamNames());
        teamManager = new TeamManager();
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
        try
        {
            teamManager.loadTeamData();
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listTeams.getSelectionModel().selectedItemProperty().addListener((selected, oldValue, newValue)
                -> 
                {
                    if (selected.getValue() == null)
                    {
                        btnEdit.setDisable(true);
                        btnRemove.setDisable(true);
                    }
                    else
                    {
                        selectedTeamIndex = listTeams.getSelectionModel().getSelectedIndex();
                        btnEdit.setDisable(false);
                        btnRemove.setDisable(false);
                    }
        });
    }

    private void observableListListener(ObservableList list)
    {
        list.addListener(new ListChangeListener() {
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
                }else if (amount < MAX_TEAMS)
                {
                    btnAdd.setDisable(false);
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
            model.openNewView(anchorPane, "TeamNameView", "New team");
        }
        catch (IOException ex)
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
            model.openNewView(anchorPane, "TeamNameView", "Edit team");
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        }
        else
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

        try
        {
            teamManager.saveTeamData();
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage primaryStage = (Stage) listTeams.getScene().getWindow();
        primaryStage.close();

        try
        {
            model.openNewView(anchorPane, "GroupView", "");
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
