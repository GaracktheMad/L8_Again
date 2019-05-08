package com.late.myapplication.controller;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.late.myapplication.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pb;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Controller.getState();
        playSound(this, getAlarmUri());

        Button weatherBtn = findViewById(R.id.weatherBtn);
        weatherBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Weather.class)));

        Button alarmsBtn = findViewById(R.id.alarmsBtn);
        alarmsBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AlarmPickerActivity.class)));

        Button snoozeBtn = findViewById(R.id.snoozeBtn);
        snoozeBtn.setOnClickListener(v -> {
            boolean b = Controller.ah.snooze(Controller.me.onTimePercentage());
            mMediaPlayer.stop();
            if (b == false) {
                startActivity(new Intent(MainActivity.this, WebMeme.class));
            } else {
                finish();
            }
        });

        Button lateBtn = findViewById(R.id.lateBtn);
        lateBtn.setOnClickListener(v -> {
            Controller.me.wasLateAgain();
            updateProgress();
            mMediaPlayer.stop();
            finish();
        });

        Button onTimeBtn = findViewById(R.id.onTimeBtn);
        onTimeBtn.setOnClickListener(v -> {
            Controller.me.wasOnTime();
            updateProgress();
            mMediaPlayer.stop();
            finish();
        });

        pb = findViewById(R.id.progressBar);
        pb.setMax(100);
        updateProgress();


    }

    private void updateProgress() {
        if (Controller.me != null) {
            pb.setProgress(Controller.me.onTimePercentage());
        }
    }

    private void playSound(Context context, Uri alert) {
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(context, alert);
            final AudioManager audioManager = (AudioManager) context
                    .getSystemService(Context.AUDIO_SERVICE);
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            }
        } catch (IOException e) {
            System.out.println("OOPS");
        }
    }

    //Get an alarm sound. Try for an alarm. If none set, try notification,
    //Otherwise, ringtone.
    private Uri getAlarmUri() {
        Uri alert = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alert == null) {
            alert = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (alert == null) {
                alert = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        return alert;
    }


}
