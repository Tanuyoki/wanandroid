package cn.yoki.wanandroid.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.yoki.library.widget.recyclerview.adapter.MultiItemTypeAdapter;
import cn.yoki.library.widget.recyclerview.base.BaseViewHolder;
import cn.yoki.library.widget.recyclerview.base.ItemViewDelegate;
import cn.yoki.wanandroid.R;

public class NewHomeAdapter extends MultiItemTypeAdapter {

    private NewHomeAdapter(Context context) {
        super(context);
    }

    public NewHomeAdapter(Context context, List datas) {
        super(context, datas);
        addItemViewDelegate(new BannerDelegate());
        addItemViewDelegate(new HomeListDelegate());
    }

    public class BannerDelegate implements ItemViewDelegate<JSONObject> {
        @Override
        public int getItemViewLayoutId() {
            return R.layout.adapter_banner_container;
        }

        @Override
        public boolean isForViewType(JSONObject item, int position) {
            return position == 0;
        }

        @Override
        public void convert(BaseViewHolder holder, JSONObject jsonObject, int position) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            List<View> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String imagePath = jsonArray.getJSONObject(i).getString("imagePath");
                View bannerView = View.inflate(mContext, R.layout.adapter_banner,null);
                Glide.with(mContext)
                        .load(imagePath)
                        .into((ImageView) bannerView.findViewById(R.id.banner_iv));
                list.add(bannerView);
            }
            ViewPager viewPager = holder.getView(R.id.home_vp);
            BannerAdapter vp = new BannerAdapter(list);
            viewPager.setAdapter(vp);
        }
    }

    public class HomeListDelegate implements ItemViewDelegate<JSONObject> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.adapter_home;
        }

        @Override
        public boolean isForViewType(JSONObject item, int position) {
            return position != 0;
        }

        @Override
        public void convert(BaseViewHolder holder, JSONObject jsonObject, int position) {
            holder.setText(R.id.adapter_home_title, jsonObject.getString("title"));
//            holder.tvShareUser.setText(listData.get(position).getString("shareUser"));
//            holder.tvTime.setText(listData.get(position).getString("niceShareDate"));

        }
    }


}
