package mychamp.dal;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import mychamp.be.Team;

/**
 * Read & Write binary data to a file.
 * @author Stephan Fuhlendorff, Jacob Enemark, Thomas Hansen, Simon Birkedal
 */
public class TeamDAO 
{
    public void writeObjectData(ArrayList<Team> teams, String fileName) throws IOException
    {        
        try (FileOutputStream fos = new FileOutputStream(fileName))
        {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(teams);
        }
    }

    public ArrayList<Team> readObjectData(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList<Team> teamList = new ArrayList<Team>();

        try (FileInputStream fis = new FileInputStream(fileName))
        {
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            teamList = (ArrayList<Team>) ois.readObject();
        }

        return teamList;
    }
}
