package cn.yoki.wanandroid.base;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cn.yoki.library.Toolkit;

public class BaseActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Toolkit.pushActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toolkit.popActivity(this);
    }
}
