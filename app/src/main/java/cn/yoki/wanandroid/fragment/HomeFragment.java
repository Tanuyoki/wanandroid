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
import java.util.Arrays;
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
import cn.yoki.wanandroid.utils.HomeCell;

public class HomeFragment extends AbsBaseFragment {

    @Override
    public void onRecyclerViewInitialized() {

        HttpClient.get(Constant.API.HOME_BANNER, new DisposeDataListener() {
            @Override
            public void onSuccess(Object object) {
                JSONObject jsonObject = JSONObject.parseObject(String.valueOf(object));
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    String imagePath = jsonArray.getJSONObject(i).getString("imagePath");
                    list.add(imagePath);
                }
                mBaseAdapter.addAll(getCells(list));
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
        List<Cell> cells = new ArrayList<>();
        cells.add(new HomeCell(list));
        return cells;
    }

}
