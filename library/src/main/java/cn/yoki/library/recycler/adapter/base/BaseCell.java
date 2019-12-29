package cn.yoki.library.recycler.adapter.base;

public abstract class BaseCell<T> implements Cell {

    public T mData;

    public BaseCell(T t) {
        this.mData = t;
    }

    @Override
    public void releaseResource() {
        // do nothing
    }

}
