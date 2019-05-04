package com.example.myapplication.model;

import java.io.Serializable;

public class Alarm implements Serializable, isAlarm {
    public Alarm() {
    }

    /**
     * Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
     */
    private boolean dayOfWeek[] = {false, false, false, false, false, false, false};

    /**
     * Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
     */
    public boolean getDayOfWeekState(int day) {
        return dayOfWeek[day];
    }

    /**
     * Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
     */
    public void setDaysOfWeek(boolean[] days) throws ArrayIndexOutOfBoundsException {
        if (days.length != dayOfWeek.length) throw new ArrayIndexOutOfBoundsException();
        for (int i = 0; i < days.length; i++) dayOfWeek[i] = days[i];
    }

    /**
     * Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
     *
     * @return Deep copy of dayOfWeek
     */
    public boolean[] getDaysOfWeekStates() {
        boolean[] b = new boolean[7];
        for (int i = 0; i < b.length; i++) {
            b[i] = dayOfWeek[i];
        }
        return b;
    }

    private int hour;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) throws InvalidHourException {
        checkHour(hour);
        this.hour = hour;
    }


    private int minute;

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) throws InvalidMinuteExcception {
        checkMinute(minute);
        this.minute = minute;
    }

    private boolean isOn;

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        this.isOn = on;
    }


}
