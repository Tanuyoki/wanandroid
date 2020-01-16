package cn.yoki.wanandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.yoki.library.Toolkit;
import cn.yoki.library.http.okhttp.HttpClient;
import cn.yoki.library.http.okhttp.listener.DisposeDataListener;
import cn.yoki.library.widget.recyclerview.adapter.CommonAdapter;
import cn.yoki.library.widget.recyclerview.adapter.HeaderAndFooterWrapper;
import cn.yoki.library.widget.recyclerview.adapter.MultiItemTypeAdapter;
import cn.yoki.library.widget.recyclerview.listener.OnLoadModeListener;
import cn.yoki.wanandroid.R;
import cn.yoki.wanandroid.activity.WebActivity;
import cn.yoki.wanandroid.adapter.HomeAdapter;

public class ListPageHelper implements MultiItemTypeAdapter.OnItemClickListener<JSONObject> {

    private int page = 0;
    private String url;
    private String loadUrl;

    private Context context;
    private RecyclerView recyclerView;
    private CommonAdapter contentAdapter;
    private HeaderAndFooterWrapper wrapperAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private OnListPageListener onListPageListener;

    public ListPageHelper(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    public ListPageHelper initRecyclerView(RecyclerView recyclerView, CommonAdapter contentAdapter) {
        initRecyclerView(recyclerView, contentAdapter, true);
        return this;
    }

    public ListPageHelper initRecyclerView(RecyclerView recyclerView, CommonAdapter contentAdapter, boolean isAddLoadingMode) {
        this.recyclerView = recyclerView;
        this.contentAdapter = contentAdapter;
        this.contentAdapter.setOnItemClickListener(this);
        if (isAddLoadingMode)
            addLoadingMode();
        if (recyclerView.getLayoutManager() == null)
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        if (recyclerView.getItemDecorationCount() < 1)
            recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(wrapperAdapter == null ? contentAdapter : wrapperAdapter);
        recyclerView.addOnScrollListener(new OnLoadModeListener() {
            @Override
            public void onLoadMore() {
                loadList();
            }
        });
        return this;
    }

    private void addLoadingMode() {
        wrapperAdapter = new HeaderAndFooterWrapper(contentAdapter);
        ViewGroup rootViewGroup = null;
        if (recyclerView.getRootView() instanceof ViewGroup) {
            rootViewGroup = (ViewGroup) recyclerView.getRootView();
        }
        View loadingMode = LayoutInflater.from(context).inflate(R.layout.loading_mode, rootViewGroup, false);
        wrapperAdapter.addFootView(loadingMode);
    }

    public ListPageHelper addSwipeRefreshHandle(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                resetList();
                loadList();
            }
        });
        return this;
    }

    public ListPageHelper loadList() {
        loadUrl = url + page + Constants.API.LIST_TAIL;
        HttpClient.get(loadUrl, new DisposeDataListener() {
            @Override
            public void onSuccess(JSONObject data) {
                JSONArray jsonArray = data.getJSONObject("data").getJSONArray("datas");
                contentAdapter.addDataAll(jsonArray);
                contentAdapter.notifyDataSetChanged();
                if (wrapperAdapter != null) {
                    wrapperAdapter.notifyDataSetChanged();
                }
                if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                page++;
            }
        });
        return this;
    }

    public ListPageHelper resetList() {
        page = 0;
        if (recyclerView != null && contentAdapter != null) {
            contentAdapter.clearData();
        }
        if (wrapperAdapter != null) {
            wrapperAdapter.notifyDataSetChanged();
        }
        return this;
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, JSONObject data, int position) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("link", data.getString("link"));
        context.startActivity(intent);

    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, JSONObject data, int position) {
        return false;
    }

    public ListPageHelper setOnListPageListener(OnListPageListener onListPageListener) {
        this.onListPageListener = onListPageListener;
        return this;
    }

    public interface OnListPageListener {
        void onItemClick(JSONObject data);
    }

}
