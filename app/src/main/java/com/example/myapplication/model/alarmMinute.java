package com.example.myapplication.model;

public interface alarmMinute {
    default void checkminute(int minute) throws InvalidMinuteExcception {
        if (minute > 59 || minute < 0) {
            throw new InvalidMinuteExcception();
        }
    }
}
