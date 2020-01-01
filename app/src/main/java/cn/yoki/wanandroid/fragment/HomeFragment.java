package cn.yoki.wanandroid.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.yoki.library.okhttp.HttpClient;
import cn.yoki.library.okhttp.listener.DisposeDataListener;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.adapter.BannerAdapter;
import cn.yoki.wanandroid.base.BaseFragment;
import cn.yoki.wanandroid.utils.Constant;

public class HomeFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        HttpClient.get(Constant.API.HOME_BANNER, new DisposeDataListener() {
            @Override
            public void onSuccess(Object object) {
                JSONObject jsonObject = JSONObject.parseObject(String.valueOf(object));
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                List<View> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    String imagePath = jsonArray.getJSONObject(i).getString("imagePath");
                    View bannerView = View.inflate(mActivity, R.layout.adapter_banner,null);
                    Glide.with(mActivity)
                            .load(imagePath)
                            .into((ImageView) bannerView.findViewById(R.id.banner_iv));
                    list.add(bannerView);
                }
                ViewPager viewPager = view.findViewById(R.id.home_vp);
                BannerAdapter vp = new BannerAdapter(list);
                viewPager.setAdapter(vp);

            }
        });




    }

    @Override
    protected void destroyView() {

    }
}
