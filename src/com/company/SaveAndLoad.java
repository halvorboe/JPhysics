package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SaveAndLoad {


    public static void save (Scene s, String fileName){
        // write object to file
        try{
            FileOutputStream fos = new FileOutputStream(new File(fileName + ".dat"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s);
            oos.close();
            fos.close();
        }
        catch(IOException e){
            System.out.print("Error: " + e.getMessage());
        }
    }

    public static Scene load (String fileName){
        // read object from file
        try{
            FileInputStream fis = new FileInputStream(new File(fileName + ".dat"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            Scene scene = (Scene)ois.readObject();
            ois.close();
            fis.close();
            return scene;
        }
        catch(IOException|ClassNotFoundException e){
            System.out.print("Error: " + e.getMessage());
        }
        return null;
    }
}
