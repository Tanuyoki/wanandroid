package cn.yoki.wanandroid.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.yoki.library.widget.recyclerview.adapter.CommonAdapter;
import cn.yoki.library.widget.recyclerview.base.BaseViewHolder;
import cn.yoki.wanandroid.R;

public class HomeAdapter extends CommonAdapter<JSONObject> {

    public HomeAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    public HomeAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(BaseViewHolder holder, JSONObject jsonObject, int position) {
        holder.setText(R.id.adapter_home_title, jsonObject.getString("title"));
        holder.setText(R.id.adapter_home_share_user, jsonObject.getString("shareUser"));
        holder.setText(R.id.adapter_home_time, jsonObject.getString("niceShareDate"));

    }

}
