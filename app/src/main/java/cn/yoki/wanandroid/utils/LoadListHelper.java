package cn.yoki.wanandroid.utils;

import com.alibaba.fastjson.JSONObject;

import cn.yoki.library.http.okhttp.HttpClient;
import cn.yoki.library.http.okhttp.listener.DisposeDataListener;

public class LoadListHelper {

    private int page = 0;
    private String loadUrl;

    private OnLoadSuccessListener onLoadSuccessListener;

    public LoadListHelper(String url, OnLoadSuccessListener listener) {
        loadUrl = url + page + Constants.API.LIST_TAIL;
        onLoadSuccessListener = listener;
        loadList();
    }

    public void loadList() {
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

    public interface OnLoadSuccessListener {
        void loadSuccess(JSONObject data);
    }

}
