package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        //Creating WebView inside our application
        Intent intent = getIntent();
        if(intent != null){
            String url = intent.getStringExtra("url");
            webView = findViewById(R.id.webView);
            //Setting url of WebView
            webView.loadUrl(url);
            //Load website in current application
            webView.setWebViewClient(new WebViewClient());
            //Enable JavaScript
            webView.getSettings().setJavaScriptEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        //Handle User interactions with WebView to not exit whole activity but to navigate to previous page of that activity
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}