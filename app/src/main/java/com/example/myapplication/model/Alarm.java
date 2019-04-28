package com.example.myapplication.model;

import java.io.Serializable;

public class Alarm implements Serializable {
    public Alarm(boolean isAm) {
        this.isAm = isAm;
    }

    private boolean dayOfWeek[] = {false, false, false, false, false, false, false};

    public boolean getDayOfWeekState(int day) {
        return dayOfWeek[day];
    }

    public void setDayOfWeek(int day, boolean state) {
        this.dayOfWeek[day] = state;
    }

    private int hour;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    private int minute;

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    private int alarmID;

    public int getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(int alarmID) {
        this.alarmID = alarmID;
    }

    private boolean isAm;

    public boolean isAm() {
        return isAm;
    }

    public void setAm(boolean am) {
        this.isAm = am;
    }

    private boolean isOn;

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        this.isOn = on;
    }


}
