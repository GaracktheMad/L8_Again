package com.example.myapplication.model;

public class Alarm {
    public Alarm(boolean amPm) {
        this.amPm = amPm;
    }

    private boolean dayOfWeek[] = {false, false, false, false, false, false, false};

    public boolean getDayOfWeekState(int day){
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

    private boolean amPm;

    public boolean isAmPm() {
        return amPm;
    }

    public void setAmPm(boolean amPm) {
        this.amPm = amPm;
    }

    private boolean onOff;

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }


}
