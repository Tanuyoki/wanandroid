package cn.yoki.library.recycler.adapter.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import cn.yoki.library.R;
import cn.yoki.library.recycler.adapter.base.BaseViewHolder;
import cn.yoki.library.recycler.adapter.base.SimpleAdapter;

public class LoadingCell extends AbsStateCell {

    public LoadingCell(Object o) {
        super(o);
    }

    @Override
    protected View getDefaultView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.rv_loading_layout, null);
    }

    @Override
    public int getItemType() {
        return SimpleAdapter.VIEW_TYPE_LOADING;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }
}
