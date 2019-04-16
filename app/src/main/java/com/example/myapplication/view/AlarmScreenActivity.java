package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class AlarmScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_screen);
    }


        public void openMainActivity(View view) {
            Intent webIntent = new Intent(this, MainActivity.class);
            startActivity(webIntent);
    }
    public void openSettingActivity(View view) {
        Intent webIntent = new Intent(this, SettingPage.class);
        startActivity(webIntent);
    }
}
