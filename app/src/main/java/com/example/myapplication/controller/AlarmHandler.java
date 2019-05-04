package com.example.myapplication.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class AlarmHandler extends BroadcastReceiver {

    private AlarmManager am;
    private final PendingIntent[] pi;

    public AlarmHandler(AlarmManager manager,PendingIntent[] pendingIntent) {
        am = manager;
        pi = pendingIntent;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Wake up", Toast.LENGTH_LONG).show();
    }

    public boolean snooze(double onTimePercentage) {
        if (onTimePercentage > .5) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 15);
            am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi[8]);
            return true;
        } else {
            return false;
        }
    }

    public void cancelAlarms() {
        for(PendingIntent p: pi){
        am.cancel(p);}
    }

    public void setAlarms(boolean[] alarms) throws ArrayIndexOutOfBoundsException {
        if (alarms.length != 7) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Calendar c = Calendar.getInstance();
        System.out.println("BANANA" + c.getTimeInMillis());
        c.set(Calendar.HOUR_OF_DAY, Controller.me.alarm.getHour());
        c.set(Calendar.MINUTE, Controller.me.alarm.getMinute());
        final long weekMS = TimeUnit.MILLISECONDS.convert(7, TimeUnit.DAYS);
        int location = c.get(Calendar.DAY_OF_WEEK) - 1;
        for (int i = 0; i < 7; i++) {
            if (location > 6) {
                location = 0;
            }
            if (alarms[location] == true) {
                am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), weekMS, pi[i]);
                System.out.println(c.getTimeInMillis());
            }
            c.add(Calendar.DAY_OF_WEEK, 1);
        }
    }

}
