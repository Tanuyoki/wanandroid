package cn.yoki.wanandroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cn.yoki.library.utils.OnSwitchClickListener;
import cn.yoki.library.utils.StatusBarUtils;
import cn.yoki.library.utils.SwitchUtils;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.base.BaseActivity;
import cn.yoki.wanandroid.fragment.HomeFragment;

public class MainActivity extends BaseActivity {

    private SwitchUtils switchUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        switchUtils = new SwitchUtils(
                findViewById(R.id.main_tab_home),
                findViewById(R.id.main_tab_square),
                findViewById(R.id.main_tab_system),
                findViewById(R.id.main_tab_project),
                findViewById(R.id.main_tab_me));
        switchUtils.enableSwitch();
        switchUtils.switchView(0);
        switchUtils.setOnSwitchClickListener((view, isOn) -> {

        });

        HomeFragment homeFragment = new HomeFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.main_frame, homeFragment);
        fragmentTransaction.commit();

        StatusBarUtils.setStatusBarColor(R.color.colorPrimary);

    }

}
