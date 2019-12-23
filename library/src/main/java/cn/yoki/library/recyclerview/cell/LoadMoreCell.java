package cn.yoki.library.recyclerview.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import cn.yoki.library.R;
import cn.yoki.library.recyclerview.Utils;
import cn.yoki.library.recyclerview.base.BaseViewHolder;
import cn.yoki.library.recyclerview.base.SimpleAdapter;

public class LoadMoreCell extends AbsStateCell {

    public static final int DEFAULT_HEIGHT = 80;

    public LoadMoreCell(Object o) {
        super(o);
    }

    @Override
    protected View getDefaultView(Context context) {
        setHeight(Utils.dpToPx(context, DEFAULT_HEIGHT));
        return LayoutInflater.from(context).inflate(R.layout.rv_load_more_layout, null);
    }

    @Override
    public int getItemType() {
        return SimpleAdapter.VIEW_TYPE_LOAD_MORE;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }
}
