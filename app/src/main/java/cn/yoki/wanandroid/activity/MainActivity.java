package cn.yoki.wanandroid.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.yoki.library.recyclerview.base.BaseAdapter;
import cn.yoki.library.recyclerview.view.ListRecyclerView;
import cn.yoki.library.utils.FragmentHelper;
import cn.yoki.library.utils.StatusBarUtils;
import cn.yoki.library.utils.SwitchUtils;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.base.BaseActivity;
import cn.yoki.wanandroid.fragment.HomeFragment;
import cn.yoki.wanandroid.fragment.MeFragment;
import cn.yoki.wanandroid.fragment.ProjectFragment;
import cn.yoki.wanandroid.fragment.SquareFragment;
import cn.yoki.wanandroid.fragment.TreeFragment;

public class MainActivity extends BaseActivity {

    private SwitchUtils switchUtils;
    private FragmentHelper fragmentHelper;

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
        switchUtils.setOnSwitchClickListener((view, isOn, index) -> {
            if (isOn)
                fragmentHelper.showFragment(index);
        });

        List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new SquareFragment());
        list.add(new TreeFragment());
        list.add(new ProjectFragment());
        list.add(new MeFragment());
        fragmentHelper = new FragmentHelper(
                this, R.id.main_frame, getSupportFragmentManager(),  list);
        fragmentHelper.showFragment(0);

        StatusBarUtils.setStatusBarColor(R.color.colorPrimary);

        ListRecyclerView listView = findViewById(R.id.home_relative);
        listView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        listView.setAdapter(new BaseAdapter());
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

    }




}
