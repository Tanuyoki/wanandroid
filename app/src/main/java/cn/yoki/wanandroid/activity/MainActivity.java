package cn.yoki.wanandroid.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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


        HomeFragment homeFragment = new HomeFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
//        fragmentTransaction.add(R.id.main_fragment, homeFragment);
        fragmentTransaction.commit();

//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.main_frame, HomeFragment.newInstance())
//                .commit();

    }

}
