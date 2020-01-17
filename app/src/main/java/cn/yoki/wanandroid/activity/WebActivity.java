package cn.yoki.wanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import cn.yoki.library.utils.StatusBarUtils;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.base.BaseActivity;

public class WebActivity extends BaseActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        StatusBarUtils.setStatusBarColor(R.color.colorPrimary);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent == null)
            return;
        String link = intent.getStringExtra("link");

        webView = findViewById(R.id.activity_web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        webView.loadUrl(link);
    }

    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadUrl(null);
            webView.clearHistory();
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }
}
