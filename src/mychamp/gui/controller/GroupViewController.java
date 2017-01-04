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
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class GroupViewController implements Initializable
{

    @FXML
    private TableView<?> tableA;
    @FXML
    private TableView<?> tableB;
    @FXML
    private TableView<?> tableC;
    @FXML
    private TableView<?> tableD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
