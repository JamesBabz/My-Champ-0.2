/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.be;

import java.io.Serializable;

/**
 *
 * @author Thomas
 */
public class Team implements Serializable
{
    int id;
    String name;
    int played;
    int won;
    int draw;
    int lost;
    int goalFor;
    int goalAgainst;
    int point;
    
    public Team(String name)
    {
        this.name = name;
        this.played = 1;
        this.won = 2;
        this.draw = 3;
        this.lost = 4;
        this.goalFor = 5;
        this.goalAgainst = 6;
        this.point = 7;
        
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    
    public int getPlayed()
    {
        return played;
    }

    public void setPlayed(int played)
    {
        this.played = played;
    }

    public int getWon()
    {
        return won;
    }

    public void setWon(int won)
    {
        this.won = won;
    }

    public int getDraw()
    {
        return draw;
    }

    public void setDraw(int draw)
    {
        this.draw = draw;
    }

    public int getLost()
    {
        return lost;
    }

    public void setLost(int lost)
    {
        this.lost = lost;
    }

    public int getGoalFor()
    {
        return goalFor;
    }

    public void setGoalFor(int goalFor)
    {
        this.goalFor = goalFor;
    }

    public int getGoalAgainst()
    {
        return goalAgainst;
    }

    public void setGoalAgainst(int goalAgainst)
    {
        this.goalAgainst = goalAgainst;
    }

    public int getPoint()
    {
        return point;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }
  
}
