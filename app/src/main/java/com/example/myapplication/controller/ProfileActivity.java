package com.example.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.model.InvalidZipCodeException;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView nameTxt = findViewById(R.id.txtProfileName);
        nameTxt.setText(Controller.me.getName());

        TextView zipTxt = findViewById(R.id.txtZipCode);
        zipTxt.setText(Controller.me.getZipCode());

        TextView validationTxt = findViewById(R.id.validationTextView);

        Button homeBtn = findViewById(R.id.btnBackHome);
        homeBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, MainActivity.class)));

        Button saveBtn = findViewById(R.id.btnSaveProfile);
        saveBtn.setOnClickListener(v -> {
            try{
                Controller.me.setZipCode(Integer.valueOf(zipTxt.getText().toString()));Controller.me.setName(nameTxt.getText().toString());
                Controller.saveState();
                validationTxt.setText("Success!");
            }catch(NumberFormatException e){
                validationTxt.setText("Zipcode must be a number!");
            }catch(InvalidZipCodeException e){
                validationTxt.setText("Zipcode must be in the range [0, 99999]");
            }
        });
    }
}
