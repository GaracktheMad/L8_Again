package com.example.myapplication.controller;

import android.content.Context;

import com.example.myapplication.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Controller {
    public static User me;
    public static AlarmHandler ah = null;
    public static boolean firstRun = true;
    public static Context context;

    public static void saveState() {
        try {
            File path = context.getFilesDir();
            File file = new File(path, "userData.ser");
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(me);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void getState() {
        User u = null;File path = context.getFilesDir();
        File file = new File(path, "userData.ser");
        try {
            u = new User("Lemmy", 89109);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            u = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        me = u;
    }

    public static void activateAlarms() {
        if (me.alarm.isOn()) {
            try {
                ah.cancelAlarms();
            } catch (Exception e) {
            }
            ah.setAlarms(me.alarm.getDaysOfWeekStates());
        }
    }
}
