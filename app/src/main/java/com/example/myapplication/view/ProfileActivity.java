package com.example.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button homeBtn = findViewById(R.id.btnBackHome);
        homeBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, HomeActivity.class)));
    }
}
