/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import mychamp.be.Group;
import mychamp.be.Team;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class GroupViewController implements Initializable {

    @FXML
    private TableView<?> tableA;
    @FXML
    private TableView<?> tableB;
    @FXML
    private TableView<?> tableC;
    @FXML
    private TableView<?> tableD;

    private ArrayList<Team> teams;
    private ArrayList<Team> groupATeams;
    private ArrayList<Team> groupBTeams;
    private ArrayList<Team> groupCTeams;
    private ArrayList<Team> groupDTeams;

    Group groupA;
    Group groupB;
    Group groupC;
    Group groupD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO<
        groupATeams = new ArrayList<>();
        groupBTeams = new ArrayList<>();
        groupCTeams = new ArrayList<>();
        groupDTeams = new ArrayList<>();
        teams = ChampModel.getInstance().getTeams();
        addTeamsToGroups(teams);
        groupA = new Group("A");
        groupB = new Group("B");
        groupC = new Group("C");
        groupD = new Group("D");

//        System.out.println(groupATeams.toString());
    }

    private void addTeamsToGroups(ArrayList<Team> teams)
    {
        int currentGroup = 0;
        for (Team team : teams)
        {
            switch (currentGroup)
            {
                case 0:
                    groupATeams.add(team);
                    break;
                case 1:
                    groupBTeams.add(team);
                    break;
                case 2:

                    groupCTeams.add(team);
                    break;
                case 3:
                    groupDTeams.add(team);
                    break;
                default:
                    System.out.println("NOPE");
            }
            if (currentGroup == 3)
            {
                currentGroup = 0;
            }
            else
            {
                currentGroup++;
            }
        }
    }

}
