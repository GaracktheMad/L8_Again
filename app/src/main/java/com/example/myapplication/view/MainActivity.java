package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.controller.Controller;
import com.example.myapplication.model.InvalidZipCodeException;
import com.example.myapplication.model.User;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button alarmsBtn = findViewById(R.id.alarmsBtn);
        alarmsBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AlarmPickerActivity.class)));
        Button snoozeBtn = findViewById(R.id.snoozeBtn);
        snoozeBtn.setOnClickListener(v -> {
            boolean b = AlarmHandling.snooze(Controller.me.onTimePercentage());
            if (b == false) {
                startActivity(new Intent(MainActivity.this, WebMeme.class));
            }
        });
        pb = findViewById(R.id.progressBar);
        try {
            Controller.getState();
        } catch (Exception e) {
            try {
                Controller.me = new User("John Cena",10109);
            } catch (InvalidZipCodeException e1) {
                e1.printStackTrace();
            }
        }
        pb.setMax(100);
        pb.setProgress(Controller.me.onTimePercentage());
    }
    public void openWeather(View view) {
        Intent webIntent = new Intent(this, Weather.class);
        startActivity(webIntent);
    }



}
