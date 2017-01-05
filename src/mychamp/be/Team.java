/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.be;

/**
 *
 * @author Thomas
 */
public class Team
{
    String name;
    int id;
    int played;
    int won;
    int draw;
    int lost;
    int goalFor;
    int goalAgainst;
    int point;
    
    public Team(String name)
    {
        this.goalFor = 0;
        this.lost = 0;
        this.draw = 0;
        this.won = 0;
        this.played = 0;
        this.goalAgainst = 0;
        this.point = 0;
        this.name = name;
        
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
