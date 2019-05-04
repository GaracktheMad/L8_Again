package com.example.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Button profileBtn = findViewById(R.id.btnProfile);
        profileBtn.setOnClickListener(v -> startActivity(new Intent(AlarmPickerActivity.this, ProfileActivity.class)));

        Button homeBtn = findViewById(R.id.btnHome);
        homeBtn.setOnClickListener(v -> startActivity(new Intent(AlarmPickerActivity.this, MainActivity.class)));

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
                Controller.ah.cancelAlarm();
                if (Controller.me.alarm.isOn() == true) {
                    Controller.ah.setNextAlarm();
                }
                Controller.saveState();
            } catch (InvalidHourException | InvalidMinuteExcception | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        });

    }
}

