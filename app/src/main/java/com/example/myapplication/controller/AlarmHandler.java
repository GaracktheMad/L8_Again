package com.example.myapplication.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;


public class AlarmHandler extends BroadcastReceiver  {

    private AlarmManager am;
    private final PendingIntent pi;

    public AlarmHandler() {
        am = null;
        pi = null;
    }

    public AlarmHandler(Context context, Intent destinationIntent) {
        am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        pi=PendingIntent.getBroadcast(context.getApplicationContext(),0, destinationIntent,0);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Wake up",Toast.LENGTH_LONG).show();
    }

    public boolean snooze(double onTimePercentage) {
        if (onTimePercentage > .5){
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 15);
            am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
            return true;
        }
        else{
            return false;
        }
    }

    public void nextAlarm(long ms){
        am.setExact(AlarmManager.RTC_WAKEUP, ms, pi);
    }

    public void cancelAlarm(){
        am.cancel(pi);
    }


}
