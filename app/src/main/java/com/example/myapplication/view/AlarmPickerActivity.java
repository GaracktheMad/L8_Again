package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;

public class AlarmPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_picker);
        Button profileBtn = findViewById(R.id.btnProfile);
        profileBtn.setOnClickListener(v -> startActivity(new Intent(AlarmPickerActivity.this, ProfileActivity.class)));
        Button homeBtn = findViewById(R.id.btnHome);
        homeBtn.setOnClickListener(v -> startActivity(new Intent(AlarmPickerActivity.this, MainActivity.class)));
    }
}
