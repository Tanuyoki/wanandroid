package cn.yoki.library.recyclerview.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import cn.yoki.library.R;
import cn.yoki.library.recyclerview.base.BaseViewHolder;
import cn.yoki.library.recyclerview.base.SimpleAdapter;

public class EmptyCell extends AbsStateCell{

    public EmptyCell(Object o) {
        super(o);
    }

    @Override
    protected View getDefaultView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.rv_empty_layout, null);
    }

    @Override
    public int getItemType() {
        return SimpleAdapter.VIEW_TYPE_EMPTY;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }


}
