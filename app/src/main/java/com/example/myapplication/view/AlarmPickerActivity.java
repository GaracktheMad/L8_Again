package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.myapplication.R;
import com.example.myapplication.controller.Controller;

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
        if (Controller.me.alarm.isAm() == true) {
            tp.setHour(Controller.me.alarm.getHour());
        } else {
            tp.setHour(Controller.me.alarm.getHour() + 12);
        }
        tp.setMinute(Controller.me.alarm.getMinute());

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(v -> {

        });

    }
}

