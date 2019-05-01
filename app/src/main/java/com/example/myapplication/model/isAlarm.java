package com.example.myapplication.model;

public interface isAlarm {
    default void checkHour(int hour) throws InvalidHourException {
        if (hour > 12 || hour < 1) {
            throw new InvalidHourException();
        }
    }
    default void checkMinute(int minute) throws InvalidMinuteExcception {
        if (minute > 59 || minute < 0) {
            throw new InvalidMinuteExcception();
        }
    }
}
