package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class SettingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        Button alarmsBtn = findViewById(R.id.btnAlarms);
        alarmsBtn.setOnClickListener(v -> startActivity(new Intent(SettingPage.this, AlarmScreenActivity.class)));
    }

}
