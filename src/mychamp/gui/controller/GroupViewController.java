/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import mychamp.be.Group;
import mychamp.be.Team;
import mychamp.gui.model.ChampModel;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class GroupViewController implements Initializable {

    ChampModel model;

    private ArrayList<Team> teams;
    private ObservableList<Team> groupATeams;
    private ObservableList<Team> groupBTeams;
    private ObservableList<Team> groupCTeams;
    private ObservableList<Team> groupDTeams;

    private Group groupA;
    private Group groupB;
    private Group groupC;
    private Group groupD;

    @FXML
    private TableView<Team> tableA;
    @FXML
    private TableView<Team> tableB;
    @FXML
    private TableView<Team> tableC;
    @FXML
    private TableView<Team> tableD;

    @FXML
    private TableColumn<Team, String> colTeamA;
    @FXML
    private TableColumn<Team, Integer> colPlayedA;
    @FXML
    private TableColumn<Team, Integer> colWonA;
    @FXML
    private TableColumn<Team, Integer> colDrawnA;
    @FXML
    private TableColumn<Team, Integer> colLostA;
    @FXML
    private TableColumn<Team, Integer> colGFA;
    @FXML
    private TableColumn<Team, Integer> colGAA;
    @FXML
    private TableColumn<Team, Integer> colPointsA;

    @FXML
    private TableColumn<Team, String> colTeamB;
    @FXML
    private TableColumn<Team, Integer> colPlayedB;
    @FXML
    private TableColumn<Team, Integer> colWonB;
    @FXML
    private TableColumn<Team, Integer> colDrawnB;
    @FXML
    private TableColumn<Team, Integer> colLostB;
    @FXML
    private TableColumn<Team, Integer> colGFB;
    @FXML
    private TableColumn<Team, Integer> colGAB;
    @FXML
    private TableColumn<Team, Integer> colPointsB;

    @FXML
    private TableColumn<Team, String> colTeamC;
    @FXML
    private TableColumn<Team, Integer> colPlayedC;
    @FXML
    private TableColumn<Team, Integer> colWonC;
    @FXML
    private TableColumn<Team, Integer> colDrawnC;
    @FXML
    private TableColumn<Team, Integer> colLostC;
    @FXML
    private TableColumn<Team, Integer> colGFC;
    @FXML
    private TableColumn<Team, Integer> colGAC;
    @FXML
    private TableColumn<Team, Integer> colPointsC;

    @FXML
    private TableColumn<Team, String> colTeamD;
    @FXML
    private TableColumn<Team, Integer> colPlayedD;
    @FXML
    private TableColumn<Team, Integer> colWonD;
    @FXML
    private TableColumn<Team, Integer> colDrawnD;
    @FXML
    private TableColumn<Team, Integer> colLostD;
    @FXML
    private TableColumn<Team, Integer> colGFD;
    @FXML
    private TableColumn<Team, Integer> colGAD;
    @FXML
    private TableColumn<Team, Integer> colPointsD;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = ChampModel.getInstance();
        teams = model.getTeams();
        colTeamA.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeamB.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeamC.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTeamD.setCellValueFactory(new PropertyValueFactory<>("name"));

        groupInit();
        setTeamIds();

        tableA.setItems(groupATeams);
        tableB.setItems(groupBTeams);
        tableC.setItems(groupCTeams);
        tableD.setItems(groupDTeams);
//        System.out.println(Arrays.toString(groupD.getHomeTeams1()));
//        System.out.println(Arrays.toString(groupD.getAwayTeams1()));
//        System.out.println(Arrays.toString(groupD.getHomeTeams2()));
//        System.out.println(Arrays.toString(groupD.getAwayTeams2()));

    }

    private void groupInit()
    {
        groupATeams = FXCollections.observableArrayList();
        groupBTeams = FXCollections.observableArrayList();
        groupCTeams = FXCollections.observableArrayList();
        groupDTeams = FXCollections.observableArrayList();
        addTeamsToGroups(teams);
        groupA = new Group("A", groupATeams.size());
        groupB = new Group("B", groupBTeams.size());
        groupC = new Group("C", groupCTeams.size());
        groupD = new Group("D", groupDTeams.size());
    }

    private void setTeamIds()
    {
        for (Team team : teams)
        {
            team.setId(teams.indexOf(team) + 1);
        }
    }

    private void addTeamsToGroups(ArrayList<Team> teams)
    {
        Collections.shuffle(teams);
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

    @FXML
    private void openNextRoundViewA() throws IOException
    {
        setMatchRound("A");
        openNextRound("group A");
    }

    @FXML
    private void openNextRoundViewB() throws IOException
    {
        setMatchRound("B");
        openNextRound("group B");
    }

    @FXML
    private void openNextRoundViewC() throws IOException
    {
        setMatchRound("C");
        openNextRound("group C");
    }

    @FXML
    private void openNextRoundViewD() throws IOException
    {
        setMatchRound("D");
        openNextRound("group D");
    }

    private void openNextRound(String title)
    {

        try
        {
            model.openNewView(anchorPane, "NextRoundView", title);
        }
        catch (IOException ex)
        {
            Logger.getLogger(TeamManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setMatchRound(String groupName)
    {
        Group group;
        ObservableList<Team> groupTeams;
        int currRound;
        switch (groupName)
        {
            case "A":
                group = groupA;
                groupTeams = groupATeams;
                break;
            case "B":
                group = groupB;
                groupTeams = groupBTeams;
                break;
            case "C":
                group = groupC;
                break;
            case "D":
                group = groupD;
                groupTeams = groupDTeams;
                break;
            default:
                group = null;
                groupTeams = null;
                break;
        }
        currRound = group.getCurrentRound() - 1;
        Team home1;
        int home1Id;
        Team away1;
        int away1Id;
        Team home2;
        int home2Id = 0;
        Team away2;
        int away2Id = 0;

        home1 = groupTeams.get(group.getHomeTeams1()[currRound] - 1);
        away1 = groupTeams.get(group.getAwayTeams1()[currRound] - 1);
        home1Id = home1.getId();
        away1Id = away1.getId();

        if (group.getAwayTeams2() != null)
        {
            home2 = groupTeams.get(group.getHomeTeams2()[currRound] - 1);
            away2 = groupTeams.get(group.getAwayTeams2()[currRound] - 1);
            home2Id = home2.getId();
            away2Id = away2.getId();
        }
        model.setRoundTeams(home1Id, away1Id, home2Id, away2Id);
    }

}
