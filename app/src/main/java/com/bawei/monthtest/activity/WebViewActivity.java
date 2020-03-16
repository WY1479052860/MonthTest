package com.bawei.monthtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.bawei.monthtest.R;

public class WebViewActivity extends AppCompatActivity {

    private Button bt1,bt2;
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        initView();
        initWebView();
        initURl();

    }
    private void initView() {
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        web = findViewById(R.id.web);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl("javascript:javaCallJs()");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl("javascript:javaCallJsWithArgs('"+ "哈哈哈" + "')");
            }
        });

    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);

        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        web.addJavascriptInterface(new TestInterface(), "testInterface");

    }
    private void initURl() {
        web.loadUrl("file:///android_asset/test.html");
    }
    private class TestInterface{
        public void startCall(){
            Toast.makeText(WebViewActivity.this, "111111", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void showToast(String str){
            Toast.makeText(WebViewActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    }







}
