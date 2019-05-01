package com.example.myapplication.model;

import java.io.Serializable;

public class User implements UsesZipcodes, Serializable {
    @Deprecated
    private Alarm[] alarms = {new Alarm(true), new Alarm(true), new Alarm(true), new Alarm(true), new Alarm(true)};
    public Alarm alarm;
    private String name;
    private int zipCode;
    private int id;
    private static int idCounter = 0;
    private int successes;
    private int total;

    public User(String name, int zipCode) throws InvalidZipCodeException {
        this.name = name;
        id = idCounter++;
        checkZip(zipCode);
        this.zipCode = zipCode;
        successes = 1; total = 1;
        alarm = new Alarm(true);
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

    @Deprecated
    public Alarm getAlarmAt(int alarmNumber) throws IndexOutOfBoundsException {
        return alarms[alarmNumber-1];
    }

    public int onTimePercentage(){
        return (successes /total) * 100;
    }

    public void wasLateAgain(){
        total++;
    }
    public void wasOnTime(){
        total ++;
        successes++;
    }

    public int getKey() {
        return id;
    }
}
