package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.controller.Controller;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button alarmsBtn = findViewById(R.id.alarmsBtn);
        alarmsBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AlarmScreenActivity.class)));
        Button homeBtn = findViewById(R.id.btnHome);
        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HomeActivity.class)));
        Button snoozeBtn = findViewById(R.id.snoozeBtn);
        snoozeBtn.setOnClickListener(v -> {
            boolean b = AlarmHandling.snooze(Controller.me.onTimePercentage());
            if (b == false) {
                startActivity(new Intent(MainActivity.this, WebMeme.class));
            }
        });
        ProgressBar pb = findViewById(R.id.progressBar);
        pb.setProgress(Controller.me.onTimePercentage());
    }


}
