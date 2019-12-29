package cn.yoki.library.recycler.adapter.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<V extends BaseCell> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<V> mListData;

    public BaseAdapter() {
        mListData = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        for (int i = 0; i < getItemCount(); i++) {
            if (viewType == mListData.get(i).getItemType()) {
                return mListData.get(i).onCreateViewHolder(parent, viewType);
            }
        }

        throw new RuntimeException("ViewType no working");
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        mListData.get(position).onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    public void setData(List<V> data) {
        addAll(data);
        notifyDataSetChanged();
    }

    public List<V> getData() {
        return mListData;
    }

    public void add(V cell) {
        mListData.add(cell);
        int index = mListData.indexOf(cell);
        notifyItemChanged(index);
    }

    public void add(int index, V cell) {
        mListData.add(index, cell);
        notifyItemChanged(index);
    }

    public void remove(V cell) {
        int indexOfCell = mListData.indexOf(cell);
        remove(indexOfCell);
    }

    public void remove(int index) {
        mListData.remove(index);
        notifyItemRemoved(index);
    }

    public void remove(int start, int count) {
        if ((start + count) > mListData.size()) {
            return;
        }
        int size = getItemCount();
        for (int i = start; i < size; i++) {
            mListData.remove(i);
        }

        notifyItemRangeChanged(start, count);
    }

    public void addAll(List<V> cells) {
        if (cells == null || cells.size() == 0) {
            return;
        }
        mListData.addAll(cells);
        notifyItemRangeChanged(mListData.size() - cells.size(), mListData.size());
    }

    public void addAll(int index, List<V> cells) {
        if (cells == null || cells.size() == 0) {
            return;
        }
        mListData.addAll(index, cells);
        notifyItemRangeChanged(index, index + cells.size());
    }

    public void clear() {
        mListData.clear();
        notifyDataSetChanged();
    }


}
