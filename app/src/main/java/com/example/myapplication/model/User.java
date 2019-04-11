package com.example.myapplication.model;

public class User {
    private Alarm[] alarms = {new Alarm(), new Alarm(), new Alarm(), new Alarm(), new Alarm()};
    private String name;
    private int zipCode;
    private int id;
    private static int idCounter = 0;

    public User(String name, int zipCode) throws InvalidZipCodeException{
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

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public Alarm getAlarm(int index) throws IndexOutOfBoundsException{
        return alarms[index];
    }

    public int getKey(){
        return id;
    }

    public static void checkZip(int zip) throws InvalidZipCodeException{
        //TODO check zip code for errors, if exists throw exception
    }
}
