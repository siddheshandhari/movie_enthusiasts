package com.example.filmenthusiasts.activities.website_webview;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.filmenthusiasts.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        WebView webView = findViewById(R.id.webView_website);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.zocdoc.com/");
    }
}
