package com.example.myapplication.model;

import java.io.Serializable;

public class User implements UsesZipcodes, Serializable {
    public Alarm alarm;
    private String name;
    private int zipCode;
    private double successes;
    private double total;

    public User(String name, int zipCode) throws InvalidZipCodeException {
        this.name = name;
        checkZip(zipCode);
        this.zipCode = zipCode;
        successes = 1; total = 1;
        alarm = new Alarm();
        try {
            alarm.setHour(5);alarm.setMinute(0);
            boolean[] b = {false,true,true,true,true,true,false};
            alarm.setDaysOfWeek(b);
        } catch (InvalidTimeFormatException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) throws InvalidZipCodeException {
        checkZip(zipCode);
        this.zipCode = zipCode;
    }

    public int onTimePercentage(){
        return (int) Math.ceil((successes /total) * 100);
    }

    public void wasLateAgain(){
        total++;
    }
    public void wasOnTime(){
        total ++;
        successes++;
    }

}
