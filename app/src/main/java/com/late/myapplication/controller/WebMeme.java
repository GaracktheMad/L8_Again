package com.late.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.late.myapplication.R;

public class WebMeme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_meme);
        WebView webView = findViewById(R.id.WebView);
        webView.loadUrl("http://lmgtfy.com/?iie=1&q=How+to+not+be+late");
        WebView myWebView = (WebView) findViewById(R.id.WebView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Button endBtn = findViewById(R.id.endBtn);
        endBtn.setOnClickListener(v -> startActivity(new Intent(WebMeme.this, AlarmPickerActivity.class)));
    }
}
