package cn.yoki.library.recyclerview.cell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.yoki.library.R;
import cn.yoki.library.recyclerview.base.BaseCell;
import cn.yoki.library.recyclerview.base.BaseViewHolder;

public abstract class AbsStateCell extends BaseCell {

    private View mView;
    private int mHeight;

    public AbsStateCell(Object o) {
        super(o);
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public void setView(View view) {
        this.mView = view;
    }

    @Override
    public void releaseResource() {
        mView = null;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_state_layout, null);
        if (mView == null) {
            mView = getDefaultView(parent.getContext());
        } else {
            LinearLayout container = (LinearLayout) view.findViewById(R.id.rv_cell_state_root_layout);
            container.removeAllViews();
            container.addView(mView);
        }
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (mHeight > 0) {
            lp.height = mHeight;
        }
        view.setLayoutParams(lp);
        return new BaseViewHolder(view);
    }

    protected abstract View getDefaultView(Context context);

}
