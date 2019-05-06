package com.example.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
            Controller.getState();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button weatherBtn = findViewById(R.id.weatherBtn);
        weatherBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Weather.class)));
        /*weatherBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openWeather();
            }
        });*/

        Button alarmsBtn = findViewById(R.id.alarmsBtn);
        alarmsBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AlarmPickerActivity.class)));
        /*alarmsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openAlarmPicker();
            }
        });*/

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
            Controller.ah.setNextAlarm();
        });

        Button onTimeBtn = findViewById(R.id.onTimeBtn);
        onTimeBtn.setOnClickListener(v -> {
            Controller.me.wasOnTime();
            updateProgress();
            Controller.ah.setNextAlarm();
        });

        pb = findViewById(R.id.progressBar);
        pb.setMax(100);
        updateProgress();
    }

    private void updateProgress() {
        if(Controller.me != null){
        pb.setProgress(Controller.me.onTimePercentage());}
    }

    //weatherBtn()
    public void openWeather(){
        Intent intent = new Intent(this, Weather.class);
        startActivity(intent);
    }
    //alarmsBtn()
    public void openAlarmPicker(){
        Intent intent = new Intent(this, Weather.class);
        startActivity(intent);
    }
}
