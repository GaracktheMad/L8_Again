package com.example.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
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

        CheckBox sunday = findViewById(R.id.chkSun);
        CheckBox monday = findViewById(R.id.chkMon);
        CheckBox tuesday = findViewById(R.id.chkTues);
        CheckBox wednesday = findViewById(R.id.chkWed);
        CheckBox thursday = findViewById(R.id.chkThur);
        CheckBox friday = findViewById(R.id.chkFri);
        CheckBox saturday = findViewById(R.id.chkSat);


        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(v -> {
            try {
                Controller.me.alarm.setHour(tp.getHour());
                Controller.me.alarm.setMinute(tp.getMinute());
                //TODO Handle days of week
            } catch (InvalidHourException | InvalidMinuteExcception e) {
                e.printStackTrace();
            }
            //TODO Save file, set next alarm, Cancel old alarms
        });

    }
}

