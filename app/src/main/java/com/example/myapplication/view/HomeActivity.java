package com.example.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.myapplication.R;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button profileBtn = findViewById(R.id.btnProfilePage);
        profileBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ProfileActivity.class)));
        Button alarmsBtn = findViewById(R.id.btnAlarmsPage);
        alarmsBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, AlarmScreenActivity.class)));
        Button weatherBtn = findViewById(R.id.btnWeather);
        weatherBtn.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, WeatherActivity.class)));

    }

}



