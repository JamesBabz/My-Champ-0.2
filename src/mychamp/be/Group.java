/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.be;

import javafx.collections.ObservableList;

/**
 *
 * @author James
 */
public class Group {

    String name;
    int teamsInGroup;
    int[] homeTeams1;
    int[] awayTeams1;
    int[] homeTeams2;
    int[] awayTeams2;
    int currentRound;
    private ObservableList<Team> teams;

    public Group(String name, int teams)
    {
        this.currentRound = 1;
        this.name = name;
        this.teamsInGroup = teams;
        groupPlay(teamsInGroup);
    }

    public Group(String name, ObservableList<Team> teams)
    {
        this.currentRound = 1;
        this.name = name;
//        this.teamsInGroup = teams;
        this.teams = teams;
        this.teamsInGroup = teams.size();
        groupPlay(teamsInGroup);
    }

    private void groupPlay(int teams)
    {
        switch (teams)
        {
            case 3:
                groupOf3();
                break;
            case 4:
                groupOf4();
                break;
            default:
                System.out.println("ERROR");
                break;
        }
    }

    private void groupOf3()
    {
        homeTeams1 = new int[]
        {
            1, 2, 1, 2, 3, 3
        };
        awayTeams1 = new int[]
        {
            2, 3, 3, 1, 1, 2
        };
    }

    private void groupOf4()
    {
        homeTeams1 = new int[]
        {
            1, 4, 1, 1, 2, 4
        };
        awayTeams1 = new int[]
        {
            2, 1, 3, 4, 1, 2
        };
        homeTeams2 = new int[]
        {
            3, 2, 2, 3, 4, 3
        };
        awayTeams2 = new int[]
        {
            4, 3, 4, 2, 3, 1
        };
    }

    public int getTeamsInGroup()
    {
        return teamsInGroup;
    }

    public int[] getHomeTeams1()
    {
        return homeTeams1;
    }

    public int[] getAwayTeams1()
    {
        return awayTeams1;
    }

    public int[] getHomeTeams2()
    {
        return homeTeams2;
    }

    public int[] getAwayTeams2()
    {
        return awayTeams2;
    }

    public int getCurrentRound()
    {
        return currentRound;
    }

    public void setCurrentRound(int currentRound)
    {
        this.currentRound = currentRound;
    }

    public ObservableList<Team> getTeams()
    {
        return teams;
    }
    
    
}
