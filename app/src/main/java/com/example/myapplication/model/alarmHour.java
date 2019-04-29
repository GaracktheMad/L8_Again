package com.example.myapplication.model;

public interface alarmHour {
    default void checkhour(int hour) throws InvalidHourException {
        if (hour > 12 || hour < 1) {
            throw new InvalidHourException();
        }
    }
}
