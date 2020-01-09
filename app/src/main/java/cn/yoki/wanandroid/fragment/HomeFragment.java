package cn.yoki.wanandroid.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.yoki.library.http.okhttp.HttpClient;
import cn.yoki.library.http.okhttp.listener.DisposeDataListener;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.adapter.BannerAdapter;
import cn.yoki.wanandroid.adapter.HomeAdapter;
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
            public void onSuccess(JSONObject data) {
                JSONArray jsonArray = data.getJSONArray("data");
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

        HttpClient.get(Constant.API.HOME_ARTICLE_LIST, new DisposeDataListener() {
            @Override
            public void onSuccess(JSONObject data) {
                JSONArray jsonArray = data.getJSONObject("data").getJSONArray("datas");
                List<JSONObject> listData = jsonArray.toJavaList(JSONObject.class);

                RecyclerView recyclerView = view.findViewById(R.id.home_relative);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerView.setAdapter(new HomeAdapter(listData));
                recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));


            }
        });

    }

    @Override
    protected void destroyView() {

    }
}
