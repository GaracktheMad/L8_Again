package com.example.myapplication.view;

class AlarmHandling {
    public static boolean snooze(double onTimePercentage) {
        if (onTimePercentage > .5){
            //TODO Snooze logic
            return true;
        }
        else{
            return false;
        }
    }
}
