package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button alarmsBtn = findViewById(R.id.alarmsBtn);
        alarmsBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AlarmScreenActivity.class)));
        Button homeBtn = findViewById(R.id.btnHome);
        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, HomeActivity.class)));
    }


}
