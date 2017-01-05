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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class NextRoundViewController implements Initializable
{

    @FXML
    private Label lblRoundNumb;
    @FXML
    private Label lblHome1;
    @FXML
    private Label lblHome2;
    @FXML
    private Label lblAway1;
    @FXML
    private Label lblAway2;
    @FXML
    private TextField txtHome1;
    @FXML
    private TextField txtHome2;
    @FXML
    private TextField txtAway1;
    @FXML
    private TextField txtAway2;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
