package cn.yoki.wanandroid.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.yoki.library.okhttp.HttpClient;
import cn.yoki.library.okhttp.listener.DisposeDataListener;
import cn.yoki.library.recycler.ListRecyclerView;
import cn.yoki.library.recycler.adapter.base.Cell;
import cn.yoki.library.recycler.adapter.fragment.AbsBaseFragment;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.adapter.BannerViewPager;
import cn.yoki.wanandroid.base.BaseFragment;
import cn.yoki.wanandroid.utils.Constant;

public class HomeFragment extends AbsBaseFragment {

    @Override
    public void onRecyclerViewInitialized() {

        HttpClient.get(Constant.API.HOME_BANNER, new DisposeDataListener() {
            @Override
            public void onSuccess(Object object) {
                JSONObject jsonObject = JSONObject.parseObject(String.valueOf(object));
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                List<View> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    String imagePath = jsonArray.getJSONObject(i).getString("imagePath");

                    View view = View.inflate(getContext(), R.layout.adapter_banner, null);
                    Glide.with(getContext())
                            .load(imagePath)
                            .into((ImageView) view.findViewById(R.id.banner_iv));
                    list.add(view);
                }
                ViewPager viewPager = getView().findViewById(R.id.home_vp);
                BannerViewPager bannerViewPager = new BannerViewPager(list);
                viewPager.setAdapter(bannerViewPager);

            }
        });

    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    protected List<Cell> getCells(List list) {
        return null;
    }
}
