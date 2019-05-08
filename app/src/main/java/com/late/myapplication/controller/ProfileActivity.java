package com.late.myapplication.controller;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.late.myapplication.R;
import com.late.myapplication.model.InvalidZipCodeException;

public class ProfileActivity extends AppCompatActivity {
    //image view and button to fill image view
    ImageView myImage;
    Button pickImage;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (Controller.firstRun == true) {
            PendingIntent[] intents = {
                    PendingIntent.getActivity(this, 10001,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10002,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10003,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10004,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10005,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10006,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10007,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT),
                    PendingIntent.getActivity(this, 10008,
                            new Intent(this, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT)
            };
            Controller.ah = new AlarmHandler((AlarmManager) getSystemService(Activity.ALARM_SERVICE),
                    intents);
            Controller.firstRun = false;
            Controller.context = getApplicationContext();
        }
        Controller.getState();

        EditText nameTxt = findViewById(R.id.txtProfileName);
        nameTxt.setText(Controller.me.getName());

        EditText zipTxt = findViewById(R.id.txtZipCode);
        zipTxt.setText(String.valueOf(Controller.me.getZipCode()));

        TextView validationTxt = findViewById(R.id.validationTextView);

        Button alarmBtn = findViewById(R.id.btnAlarm);
        alarmBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, AlarmPickerActivity.class)));

        Button weatherBtn = findViewById(R.id.btnWeather);
        weatherBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, Weather.class)));

        Button saveBtn = findViewById(R.id.btnSaveProfile);
        saveBtn.setOnClickListener(v -> {
            try {
                Controller.me.setZipCode(Integer.valueOf(zipTxt.getText().toString()));
                Controller.me.setName(nameTxt.getText().toString());
                Controller.saveState();
                validationTxt.setText("Success!");
            } catch (NumberFormatException e) {
                validationTxt.setText("Zipcode must be a number!");
            } catch (InvalidZipCodeException e) {
                validationTxt.setText("Zipcode must be in the range [0, 99999]");
            }
        });

        //Code to fill imageView
        myImage = findViewById(R.id.targetimage);
        pickImage = findViewById(R.id.btnloadImage);
        //button click
        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check runtime permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){//Permission not granted, grant it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //Show pop up for runtime permission
                        requestPermissions(permissions, PERMISSION_CODE);

                    }else{//Permission already granted
                        pickImageImageFromGallery();

                    }
                }else{//System OS less than marshmallow
                    pickImageImageFromGallery();

                }


            }
        });

    }//end onCreate()
    //Method for picking an image
    private void pickImageImageFromGallery() {
        //Intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    //handle result of runtime permission request
    //@Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions , int[] grantResults) {
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageImageFromGallery();
                }else{
                    //permission denied
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();

                }
            }

        }
    }

    //handle image picked code

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //set image to image view
            myImage.setImageURI(data.getData());
        }
    }
}//End public class
