package com.example.myapplication.controller;

import com.example.myapplication.model.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Controller {
    public static User me;
    public static boolean loaded = false;



    public static void saveState() {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("userData.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(me);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void getState() throws IOException, ClassNotFoundException {
        User u = null;
        FileInputStream fileIn = new FileInputStream("userData.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        u = (User) in.readObject();
        in.close();
        fileIn.close();
        me = u;
    }
}
