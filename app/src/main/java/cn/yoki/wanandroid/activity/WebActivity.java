package cn.yoki.wanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.base.BaseActivity;

public class WebActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null)
            return;
        String link = intent.getStringExtra("link");

        webView = findViewById(R.id.activity_web_view);
        webView.loadUrl(link);
    }

}
