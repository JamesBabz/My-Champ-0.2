/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychamp.bll;

/**
 *
 * @author James
 */
public enum PropertyValue {
    NAME("name"),
    PLAYED("played"),
    WON("won"),
    DRAW("draw"),
    LOST("lost"),
    GF("goalFor"),
    GA("goalAgainst"),
    POINTS("point");

    private String PropertyValue;

    PropertyValue(String PropertyValue)
    {
        this.PropertyValue = PropertyValue;
    }

    @Override
    public String toString()
    {
        return PropertyValue;
    }
}
