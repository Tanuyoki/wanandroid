package cn.yoki.wanandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import cn.yoki.wanandroid.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<JSONObject> listData;

    public HomeAdapter(List<JSONObject> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home,  parent, false);
        HomeViewHolder vh = new HomeViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.tvTitle.setText(listData.get(position).getString("title"));
        holder.tvShareUser.setText(listData.get(position).getString("shareUser"));
        holder.tvTime.setText(listData.get(position).getString("niceShareDate"));

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvShareUser;
        private TextView tvTime;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.adapter_home_title);
            tvShareUser = itemView.findViewById(R.id.adapter_home_share_user);
            tvTime = itemView.findViewById(R.id.adapter_home_time);
        }

    }

}
