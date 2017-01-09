/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mychamp.be.Group;
import mychamp.be.Team;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class NextRoundViewController implements Initializable {

    Team homeTeam1;
    Team awayTeam1;
    Team homeTeam2;
    Team awayTeam2;

    ChampModel model;
    Group group;

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
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = ChampModel.getInstance();
        group = model.getGroup();
        getLblName(model);
        getLblRound();
    }

    /**
     * Shows the current round in the NextRoundView.
     */
    private void getLblRound()
    {
        lblRoundNumb.setText("Round: " + group.getCurrentRound());
    }

    /**
     * Handles which teams Shows up as home or away in the nextroundview.
     * In cases with only 3 teams in a group the second match lbls are disabled.
     * @param model 
     */
    private void getLblName(ChampModel model)
    {
        ArrayList<Team> teams = model.getTeams();
        for (Team team : teams)
        {
            if (team.getId() == model.getFirstMatch()[0])
            {
                homeTeam1 = team;
                lblHome1.setText(team.getName());
            }
            else if (team.getId() == model.getFirstMatch()[1])
            {
                awayTeam1 = team;
                lblAway1.setText(team.getName());
            }

            if (model.getSecondMatch()[0] != 0)
            {
                if (team.getId() == model.getSecondMatch()[0])
                {
                    homeTeam2 = team;
                    lblHome2.setText(team.getName());
                }
                else if (team.getId() == model.getSecondMatch()[1])
                {
                    awayTeam2 = team;
                    lblAway2.setText(team.getName());

                }

            }
            else
            {
                lblHome2.setDisable(true);
                lblAway2.setDisable(true);
                txtHome2.setDisable(true);
                txtAway2.setDisable(true);

            }
        }
    }

    /**
     * Handles the save button, and put the data from this into the GroupViewController.
     */
    @FXML
    private void handleSave()
    {
        int homeScore = 0;
        int awayScore = 0;
        if (!"".equals(txtHome1.getText()) && !"".equals(txtAway1.getText()))
        {
            homeScore = Integer.parseInt(txtHome1.getText());
            awayScore = Integer.parseInt(txtAway1.getText());
            setMatch(homeTeam1, awayTeam1, homeScore, awayScore);
        }
        if (!"".equals(txtHome2.getText()) && !"".equals(txtAway2.getText()))
        {
            homeScore = Integer.parseInt(txtHome2.getText());
            awayScore = Integer.parseInt(txtAway2.getText());
            setMatch(homeTeam2, awayTeam2, homeScore, awayScore);
        }
        closeWindow();
    }

    /**
     * Closes the view.
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

    /**
     * Sets the match scores for each match.
     * @param homeTeam
     * @param awayTeam
     * @param homeScore
     * @param awayScore 
     */
    private void setMatch(Team homeTeam, Team awayTeam, int homeScore, int awayScore)
    {

        homeTeam.setPlayed(homeTeam.getPlayed() + 1);
        awayTeam.setPlayed(awayTeam.getPlayed() + 1);

        if (homeScore > awayScore)
        {
            homeTeam.setWon(homeTeam.getWon() + 1);
            awayTeam.setLost(awayTeam.getLost() + 1);
        }
        else if (homeScore < awayScore)
        {
            awayTeam.setWon(awayTeam.getWon() + 1);
            homeTeam.setLost(homeTeam.getLost() + 1);
        }
        else
        {
            homeTeam.setDraw(homeTeam.getDraw()+1);
            awayTeam.setDraw(awayTeam.getDraw()+1);
        }

        homeTeam.setGoalFor(homeTeam.getGoalFor() + homeScore);
        awayTeam.setGoalFor(awayTeam.getGoalFor() + awayScore);
        
        homeTeam.setGoalAgainst(homeTeam.getGoalAgainst() + awayScore);
        awayTeam.setGoalAgainst(awayTeam.getGoalAgainst() + homeScore);
        
        homeTeam.setPoint();
        awayTeam.setPoint();

        ObservableList<Team> teamsInGroup = group.getTeams();
        int x = 0;
        for (Team team : teamsInGroup)
        {
            if (team.getPlayed() == group.getCurrentRound())
            {
                x++;
            }
        }
        if (x == teamsInGroup.size() || teamsInGroup.size() == 3 && x == 2)
        {
            group.setCurrentRound(group.getCurrentRound() + 1);
        }
        
    }
}
