package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void openAlarmScreenActivity(View view) {
            Intent webIntent = new Intent(this, AlarmScreenActivity.class);
            startActivity(webIntent);
    }

    public void openHomeActivity(View view) {
        Intent webIntent = new Intent(this, HomeActivity.class);
        startActivity(webIntent);
    }
}
