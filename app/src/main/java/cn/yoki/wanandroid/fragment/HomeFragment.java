package cn.yoki.wanandroid.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.yoki.library.okhttp.HttpClient;
import cn.yoki.library.okhttp.listener.DisposeDataListener;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.adapter.BannerViewPager;
import cn.yoki.wanandroid.base.BaseFragment;
import okhttp3.OkHttpClient;

public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        HttpClient.get("https://www.wanandroid.com/banner/json", new DisposeDataListener() {
            @Override
            public void onSuccess(Object object) {
                JSONObject jsonObject = JSONObject.parseObject(String.valueOf(object));
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                List<View> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    String imagePath = jsonArray.getJSONObject(i).getString("imagePath");

                    View view = View.inflate(mActivity, R.layout.adapter_banner, null);
                    Glide.with(mActivity)
                            .load(imagePath)
                            .into((ImageView) view.findViewById(R.id.banner_iv));
                    list.add(view);
                }
                ViewPager viewPager = view.findViewById(R.id.home_vp);
                BannerViewPager bannerViewPager = new BannerViewPager(list);

                viewPager.setAdapter(bannerViewPager);

            }
        });


    }

    @Override
    protected void destroyView() {

    }

}
