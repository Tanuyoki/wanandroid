package cn.yoki.wanandroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import cn.yoki.library.utils.OnSwitchClickListener;
import cn.yoki.library.utils.SwitchUtils;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.base.BaseActivity;
import cn.yoki.wanandroid.base.BaseFragment;
import cn.yoki.wanandroid.fragment.HomeFragment;

public class MainActivity extends BaseActivity {

    private FrameLayout frameLayout;
    private SwitchUtils switchUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        frameLayout = findViewById(R.id.main_frame);

        switchUtils = new SwitchUtils(
                findViewById(R.id.main_tab_home),
                findViewById(R.id.main_tab_square),
                findViewById(R.id.main_tab_system),
                findViewById(R.id.main_tab_project),
                findViewById(R.id.main_tab_me));
        switchUtils.enableSwitch();

//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.main_frame, HomeFragment.newInstance())
//                .commit();

    }

}
