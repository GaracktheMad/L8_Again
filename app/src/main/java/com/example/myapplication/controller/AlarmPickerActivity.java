package com.example.myapplication.controller;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TimePicker;

import com.example.myapplication.R;
import com.example.myapplication.model.InvalidHourException;
import com.example.myapplication.model.InvalidMinuteExcception;

public class AlarmPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_picker);

        if (Controller.firstRun == true) {
            PendingIntent[] intents = {
                    PendingIntent.getActivity(this, 10001,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10002,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10003,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10004,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10005,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10006,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10007,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10008,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT)
            };
            Controller.ah = new AlarmHandler((AlarmManager) getSystemService(Activity.ALARM_SERVICE),
                    intents);
            Controller.firstRun = false;
            Controller.context = getApplicationContext();
        }
        Controller.getState();


        Button profileBtn = findViewById(R.id.btnProfile);
        profileBtn.setOnClickListener(v -> startActivity(new Intent(AlarmPickerActivity.this, ProfileActivity.class)));

        TimePicker tp = findViewById(R.id.simpleTimePicker);
        tp.setIs24HourView(false);
        tp.setHour(Controller.me.alarm.getHour());

        tp.setMinute(Controller.me.alarm.getMinute());

        CheckBox[] chkers = {findViewById(R.id.chkSun),
                findViewById(R.id.chkMon), findViewById(R.id.chkTues), findViewById(R.id.chkWed),
                findViewById(R.id.chkThur), findViewById(R.id.chkFri), findViewById(R.id.chkSat)};
        int c = 0;
        for (CheckBox cb : chkers) {
            cb.setChecked(Controller.me.alarm.getDayOfWeekState(c));
            c++;
        }

        Switch offSwitch = findViewById(R.id.offSwitch);
        offSwitch.setChecked(Controller.me.alarm.isOn());

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(v -> {
            try {
                Controller.me.alarm.setHour(tp.getHour());
                Controller.me.alarm.setMinute(tp.getMinute());
                boolean[] dayStates = new boolean[chkers.length];
                int counter = 0;
                for (CheckBox cb : chkers) {
                    dayStates[counter] = cb.isChecked();
                    counter++;
                }
                Controller.me.alarm.setDaysOfWeek(dayStates);
                Controller.me.alarm.setOn(offSwitch.isChecked());
                Controller.ah.cancelAlarms();
                Controller.activateAlarms();
                Controller.saveState();
            } catch (InvalidHourException | InvalidMinuteExcception | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });

    }
}

