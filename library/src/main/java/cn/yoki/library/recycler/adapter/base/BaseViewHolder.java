package cn.yoki.library.recycler.adapter.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mListView;
    private View mItemView;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mItemView = itemView;
        mListView = new SparseArray<>();
    }

    private <V extends View> V retrieveView(int resId) {
        View view = mListView.get(resId);
        if (view == null) {
            view = mItemView.findViewById(resId);
            mListView.put(resId, view);
        }
        return (V) view;
    }

    public View getView(int resId) {
        return retrieveView(resId);
    }

    public void setText(int resId, CharSequence text) {
        TextView textView = retrieveView(resId);
        textView.setText(text);
    }



}