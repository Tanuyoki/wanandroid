package cn.yoki.wanandroid.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.yoki.library.okhttp.HttpClient;
import cn.yoki.library.okhttp.listener.DisposeDataListener;
import cn.yoki.library.recycler.adapter.base.BaseCell;
import cn.yoki.library.recycler.adapter.base.BaseViewHolder;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.adapter.BannerViewPager;

public class HomeCell extends BaseCell<List<String>> {

    public static final int TYPE = 0;

    public HomeCell(List<String> jsonObjects) {
        super(jsonObjects);

    }

    @Override
    public int getItemType() {
        return TYPE;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.fragment_home, null);
//        View itemView = View.inflate(parent.getContext(), R.layout.test, null);
        BaseViewHolder viewHolder = new BaseViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        List<View> list = new ArrayList<>();
        for (String imagePath : mData) {
            View view = View.inflate(holder.itemView.getContext(), R.layout.adapter_banner, null);
            Glide.with(holder.itemView.getContext())
                    .load(imagePath)
                    .into((ImageView) view.findViewById(R.id.banner_iv));
            list.add(view);
        }
        ViewPager viewPager = (ViewPager) holder.getView(R.id.home_vp);
        BannerViewPager bannerViewPager = new BannerViewPager(list);
        viewPager.setAdapter(bannerViewPager);




    }


}
