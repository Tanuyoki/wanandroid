package cn.yoki.wanandroid.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.yoki.library.utils.FragmentHelper;
import cn.yoki.library.utils.StatusBarUtils;
import cn.yoki.library.utils.SwitchUtils;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.base.BaseActivity;
import cn.yoki.wanandroid.fragment.HomeFragment;
import cn.yoki.wanandroid.fragment.MainFragmentPagerAdapter;
import cn.yoki.wanandroid.fragment.MeFragment;
import cn.yoki.wanandroid.fragment.ProjectFragment;
import cn.yoki.wanandroid.fragment.SquareFragment;
import cn.yoki.wanandroid.fragment.TreeFragment;

public class MainActivity extends BaseActivity {

    private SwitchUtils switchUtils;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtils.setStatusBarColor(R.color.colorPrimary);
        initView();
    }

    private void initView() {
        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new SquareFragment());
        list.add(new TreeFragment());
        list.add(new ProjectFragment());
        list.add(new MeFragment());

        switchUtils = new SwitchUtils(
                findViewById(R.id.main_tab_home),
                findViewById(R.id.main_tab_square),
                findViewById(R.id.main_tab_system),
                findViewById(R.id.main_tab_project),
                findViewById(R.id.main_tab_me));
        switchUtils.enableSwitch();
        switchUtils.switchView(0);
        switchUtils.setOnSwitchClickListener((view, isOn, position) -> {
            if (isOn)
                viewPager.setCurrentItem(position);
        });

        viewPager = findViewById(R.id.main_container);
        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), 0, list));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchUtils.switchView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }




}
