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
import cn.yoki.library.widget.recyclerview.adapter.HeaderAndFooterWrapper;
import cn.yoki.library.widget.recyclerview.listener.OnLoadModeListener;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.adapter.BannerAdapter;
import cn.yoki.wanandroid.adapter.HomeAdapter;
import cn.yoki.wanandroid.base.BaseFragment;
import cn.yoki.wanandroid.utils.Constants;
import cn.yoki.wanandroid.utils.LoadListHelper;

public class HomeFragment extends BaseFragment {

    private ViewPager viewPager;
    private RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.home_vp);
        recyclerView = view.findViewById(R.id.home_relative);

        HttpClient.get(Constants.API.HOME_BANNER, new DisposeDataListener() {
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
                BannerAdapter vp = new BannerAdapter(list);
                viewPager.setAdapter(vp);

            }
        });


        JSONArray jsonArray = data.getJSONObject("data").getJSONArray("datas");
        List<JSONObject> listData = jsonArray.toJavaList(JSONObject.class);

        HomeAdapter homeAdapter = new HomeAdapter(listData);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new OnLoadModeListener() {
            @Override
            public void onLoadMore() {

            }
        });

        View loadingMode = View.inflate(view.getContext(), R.layout.loading_mode, null);
        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(homeAdapter);
        wrapper.addFootView(loadingMode);

        recyclerView.setAdapter(wrapper);



    }

    @Override
    protected void destroyView() {

    }
}
