/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author James
 */
public class TeamNameController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtName;

    
    private ChampModel model;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = ChampModel.getInstance();
    }    

    /**
     * Saves when "save" is pressed
     */
    @FXML
    private void handleSave()
    {
        String name = txtName.getText();
<<<<<<< HEAD
        if (name.equals(""))
        {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Team name cannot be empty");
        alert.showAndWait();
        }
        else
        {
            if (isEdit)
            {
                model.editTeam(name);
            }
            else
            {
                model.addTeam(name);
            }
            closeWindow();
        }
=======
        model.addTeam(name);
        closeWindow();
>>>>>>> refs/remotes/origin/master
    }

    /**
     * Cancels and closes the window when "cancel" is pressed
     */
    @FXML
    private void handleCancel()
    {
        closeWindow();
    }
    
    private void closeWindow()
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    
    
    
}
