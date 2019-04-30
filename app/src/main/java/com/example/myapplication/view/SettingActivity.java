package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button alarmsBtn = findViewById(R.id.btnAlarms);
        alarmsBtn.setOnClickListener(v -> startActivity(new Intent(SettingActivity.this, AlarmScreenActivity.class)));
    }

}
