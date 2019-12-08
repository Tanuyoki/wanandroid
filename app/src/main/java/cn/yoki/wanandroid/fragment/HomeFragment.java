package cn.yoki.wanandroid.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.adapter.BannerViewPager;
import cn.yoki.wanandroid.base.BaseFragment;

public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        ViewPager viewPager = view.findViewById(R.id.home_vp);

        View view1 = View.inflate(mActivity, R.layout.adapter_banner, null);
        View view2 = View.inflate(mActivity, R.layout.adapter_banner, null);
        View view3 = View.inflate(mActivity, R.layout.adapter_banner, null);
        List<View> list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        BannerViewPager bannerViewPager = new BannerViewPager(list);

        viewPager.setAdapter(bannerViewPager);
    }

    @Override
    protected void destroyView() {

    }
}
