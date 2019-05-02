package com.example.myapplication.controller;

import android.content.Intent;

import com.example.myapplication.model.User;
import com.example.myapplication.view.AlarmHandler;
import com.example.myapplication.view.MainActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Controller {
    public static User me;
    AlarmHandler ah = new AlarmHandler(this.getApplicationContext(), new Intent(MainActivity.class, AlarmHandler.class));

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

    public int getNextDay(int currentDay){
        int workingDay = currentDay++;
        for(int i = 1; i < 7; i++){
            if(workingDay > 7){
                workingDay = 0;
            }
            if(me.alarm.getDayOfWeekState(workingDay - 1) == true){
                return workingDay;
            }
            workingDay++;
        }
        return currentDay;
    }
}
