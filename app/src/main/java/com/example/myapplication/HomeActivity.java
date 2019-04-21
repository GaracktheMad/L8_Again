package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void openAlarmScreenActivity(View view) {
        Intent alarmIntent = new Intent(this, AlarmScreenActivity.class);
        startActivity(alarmIntent);
    }
    public void openProfileActivity(View view) {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileIntent);
    }
    public void openWeatherActivity(View view) {
        Intent weatherIntent = new Intent(this, WeatherActivity.class);
        startActivity(weatherIntent);
    }
}
