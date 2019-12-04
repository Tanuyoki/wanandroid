package cn.yoki.wanandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        frameLayout = findViewById(R.id.main_frame);




    }

}
