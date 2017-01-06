/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.bll;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import mychamp.be.Team;
import mychamp.dal.TeamDAO;
import mychamp.gui.model.ChampModel;

/**
 *
 * @author Thomas
 */
public class TeamManager {

    public TeamDAO teamDAO;
    public ChampModel model;

    public TeamManager()
    {

        teamDAO = new TeamDAO();
        model = ChampModel.getInstance();

    }

    public void loadTeamData() throws IOException, FileNotFoundException, ClassNotFoundException
    {

        model.getTeams().clear();
        for (Team team : teamDAO.readObjectData("TeamData.dat"))
        {
            model.addTeam(team.getName());

        }

    }

    public void saveTeamData() throws IOException
    {
        ArrayList<Team> teamsToSave = new ArrayList<>();
        for (Team team : model.getTeams())
        {
            teamsToSave.add(team);

        }

        teamDAO.writeObjectData(teamsToSave, "TeamData.dat");

    }

}
