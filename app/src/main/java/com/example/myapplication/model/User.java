package com.example.myapplication.model;

public class User implements UsesZipcodes{
    private Alarm[] alarms = {new Alarm(), new Alarm(), new Alarm(), new Alarm(), new Alarm()};
    private String name;
    private int zipCode;
    private int id;
    private static int idCounter = 0;

    public User(String name, int zipCode) throws InvalidZipCodeException {
        this.name = name;
        id = idCounter++;
        checkZip(zipCode);
        this.zipCode = zipCode;
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

    public Alarm getAlarm(int alarmNumber) throws IndexOutOfBoundsException {
        return alarms[alarmNumber-1];
    }

    public int getKey() {
        return id;
    }
}
