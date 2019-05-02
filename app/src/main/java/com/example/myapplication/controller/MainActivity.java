package com.example.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.model.InvalidZipCodeException;
import com.example.myapplication.model.User;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Controller.firstRun == true) {
            Controller.ah = new AlarmHandler(this.getApplicationContext(), new Intent(MainActivity.this, AlarmHandler.class));
            Controller.firstRun = false;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button alarmsBtn = findViewById(R.id.alarmsBtn);
        alarmsBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AlarmPickerActivity.class)));

        Button snoozeBtn = findViewById(R.id.snoozeBtn);
        snoozeBtn.setOnClickListener(v -> {
            boolean b = Controller.ah.snooze(Controller.me.onTimePercentage());
            if (b == false) {
                startActivity(new Intent(MainActivity.this, WebMeme.class));
            }
        });

        Button lateBtn = findViewById(R.id.lateBtn);
        lateBtn.setOnClickListener(v -> {
            Controller.me.wasLateAgain();
            updateProgress();
            setNextAlarm();
        });

        Button onTimeBtn = findViewById(R.id.onTimeBtn);
        onTimeBtn.setOnClickListener(v -> {
            Controller.me.wasOnTime();
            updateProgress();
            setNextAlarm();
        });

        pb = findViewById(R.id.progressBar);
        try {
            Controller.getState();
        } catch (Exception e) {
            try {
                Controller.me = new User("John Cena", 10109);
            } catch (InvalidZipCodeException e1) {
                e1.printStackTrace();
            }
        }
        pb.setMax(100);
        updateProgress();
    }

    private void setNextAlarm() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        //TODO Create logic to set next alarm. Duh.
    }

    private void updateProgress() {
        pb.setProgress(Controller.me.onTimePercentage());
    }


}
