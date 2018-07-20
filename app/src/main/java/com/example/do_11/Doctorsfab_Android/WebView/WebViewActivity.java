package com.example.do_11.Doctorsfab_Android.WebView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.do_11.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView1;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        //main

        webView1 = (WebView) findViewById(R.id.webview1);
        webView1.setWebViewClient(new WebViewClient());
        webSettings = webView1.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setDatabaseEnabled(true);
//
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setAllowFileAccess(true);

        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/databases");
        webSettings.setAppCacheMaxSize(1024 * 1024 * 16);
        webSettings.setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView1.loadUrl("http://v3.dropcare.co.kr");

        //

    }
}