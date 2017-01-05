/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.gui.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mychamp.be.Team;
import mychamp.dal.TeamDAO;

/**
 *
 * @author Thomas
 */
public class ChampModel {

    private final ArrayList<Team> teams;
    private final ObservableList<String> teamNames;
    private Team editTeam;
    private TeamDAO teamDAO;

    private static ChampModel instance;

    //Singleton
    public static ChampModel getInstance() {
        if (instance == null) {
            instance = new ChampModel();
        }
        return instance;
    }

    private ChampModel() {
        this.teamNames = FXCollections.observableArrayList();
        teams = new ArrayList<>();
        teamDAO = new TeamDAO();

    }
    
       public void openNewView(Pane current, String viewName, String title) throws IOException {
        Stage primaryStage = (Stage) current.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mychamp/gui/view/" + viewName + ".fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));

        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(primaryStage);
        newStage.setTitle(title);

        newStage.show();
    }

    /**
     * Creates a new team
     *
     * @param name - Name of the team
     */
    public void addTeam(String name) {
        Team team = new Team(name);
        teams.add(team);
        setTeamNames();
    }

    /**
     * Returns ArrayList of all the teams
     *
     * @return - Team object
     */
    public ArrayList<Team> getTeams() {
        return teams;
    }

    /**
     * Returns ObservableList of all team names
     *
     * @return - Team names
     */
    public ObservableList<String> getTeamNames() {
        return teamNames;
    }

    /**
     * Sets the team names
     */
    private void setTeamNames() {
        teamNames.clear();
        for (Team team : teams) {
            teamNames.add(team.getName());
        }
    }

    public void removeTeam(int team) {

        teams.remove(team);
        setTeamNames();
    }

    public void setEditTeam(int team) {
        if (team >= 0) {
            editTeam = teams.get(team);
        } else {
            editTeam = null;
        }
    }

    public Team getEditTeam() {
        return editTeam;
    }

    public void editTeam(String name) {
        int index = teams.indexOf(editTeam);
        teams.set(index, new Team(name));
        editTeam = null;
        setTeamNames();
    }

    public void loadTeamData() throws IOException, FileNotFoundException, ClassNotFoundException {
        getTeams().clear();
        for (Team team : teamDAO.readObjectData("TeamData.dat")) {
            addTeam(team.getName());
        }
    }

    public void saveTeamData() throws IOException {
        ArrayList<Team> teamsToSave = new ArrayList<>();
        for (Team team : getTeams()) {
            teamsToSave.add(team);
        }
        teamDAO.writeObjectData(teamsToSave, "TeamData.dat");
    }
}
