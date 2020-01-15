package cn.yoki.wanandroid.utils;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSONObject;

import cn.yoki.library.http.okhttp.HttpClient;
import cn.yoki.library.http.okhttp.listener.DisposeDataListener;

public class LoadListHelper {

    private int page = 0;
    private String url;
    private String loadUrl;
    private SwipeRefreshLayout swipeRefreshLayout;

    private OnLoadSuccessListener onLoadSuccessListener;

    public LoadListHelper(String url) {
        this.url = url;
        loadList();
    }

    public void addSwipeRefreshHandle(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    public void setOnLoadSuccessListener(OnLoadSuccessListener listener) {
        onLoadSuccessListener = listener;
    }

    public void loadList() {
        loadUrl = url + page + Constants.API.LIST_TAIL;
        HttpClient.get(loadUrl, new DisposeDataListener() {
            @Override
            public void onSuccess(JSONObject data) {
                if (onLoadSuccessListener != null) {
                    onLoadSuccessListener.loadSuccess(data);
                    page ++;
                }
            }
        });
    }

    public void resetList() {
        page = 0;
    }

    public interface OnLoadSuccessListener {
        void loadSuccess(JSONObject data);
    }

}
