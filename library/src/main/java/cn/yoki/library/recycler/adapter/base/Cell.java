package cn.yoki.library.recycler.adapter.base;

import android.view.ViewGroup;

public interface Cell {

    public void releaseResource();

    public int getItemType();

    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    public void onBindViewHolder(BaseViewHolder holder, int position);

}
