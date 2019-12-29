package cn.yoki.library.recycler.adapter.base;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import cn.yoki.library.recycler.adapter.Utils;
import cn.yoki.library.recycler.adapter.cell.AbsStateCell;
import cn.yoki.library.recycler.adapter.cell.EmptyCell;
import cn.yoki.library.recycler.adapter.cell.ErrorCell;
import cn.yoki.library.recycler.adapter.cell.LoadMoreCell;
import cn.yoki.library.recycler.adapter.cell.LoadingCell;

public class SimpleAdapter extends BaseAdapter {

    public static final int VIEW_TYPE_ERROR = 1;
    public static final int VIEW_TYPE_EMPTY = 2;
    public static final int VIEW_TYPE_LOADING = 3;
    public static final int VIEW_TYPE_LOAD_MORE = 4;

    private EmptyCell mEmptyCell;
    private ErrorCell mErrorCell;
    private LoadingCell mLoadingCell;
    private LoadMoreCell mLoadMoreCell;

    private boolean mIsShowLoadMore = false;
    private boolean mIsShowError = false;
    private boolean mIsShowLoading = false;
    private boolean mIsShowEmpty = false;

    public SimpleAdapter() {
        mEmptyCell = new EmptyCell(null);
        mErrorCell = new ErrorCell(null);
        mLoadingCell = new LoadingCell(null);
        mLoadMoreCell = new LoadMoreCell(null);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    return isViewTypeCustom(viewType) ? gridLayoutManager.getSpanCount() : 1;
                }
            });

        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        int position = holder.getAdapterPosition();
        int viewType = getItemViewType(position);
        if (isStaggeredGridLayout(holder)) {
            if (isViewTypeCustom(viewType)) {
                StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                lp.setFullSpan(true);
            }
        }
    }

    private boolean isViewTypeCustom(int viewType) {
        return (viewType == VIEW_TYPE_ERROR || viewType == VIEW_TYPE_EMPTY
                || viewType == VIEW_TYPE_LOADING || viewType == VIEW_TYPE_LOAD_MORE);
    }

    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder vh) {
        ViewGroup.LayoutParams lp = vh.itemView.getLayoutParams();
        return lp instanceof StaggeredGridLayoutManager.LayoutParams;
    }

    public void showLoading() {
        clear();
        mIsShowLoading = true;
        add(mLoadingCell);
    }

    public void showLoading(View loadingView) {
        showLoading(loadingView, 0);
    }

    public void showLoading(View loadingView, int viewHeight) {
        if (loadingView == null) {
            showLoading();
            return;
        }
        clear();
        mIsShowLoading = true;
        mLoadingCell.setView(loadingView);
        mLoadingCell.setHeight(viewHeight);
        add(mLoadingCell);
    }

    public void showLoadingKeepCount(int keepCount) {
        showLoadingKeepCount(keepCount, 0);
    }

    public void showLoadingKeepCount(int keepCount, int height) {
        showLoadingKeepCount(keepCount, height, null);
    }

    public void showLoadingKeepCount(int keepCount, int height, View loadingView) {
        if (keepCount < 0 || keepCount > mListData.size()) {
            return;
        }
        remove(keepCount, mListData.size() - keepCount);
        checkNotContainSpecialCell();
        mIsShowLoading = true;
        if (loadingView != null) {
            mLoadingCell.setView(loadingView);
        }
        mLoadingCell.setHeight(height);
        add(mLoadingCell);
    }

    public void hideLoading() {
        mListData.remove(mLoadingCell);
        mIsShowLoading = false;
    }

    public void showError() {
        clear();
        mIsShowError = true;
        add(mErrorCell);
    }

    public void showErrorKeepCount(int keepCount) {
        showErrorKeepCount(keepCount, 0);
    }

    public void showErrorKeepCount(int keepCount, int height) {
        showErrorKeepCount(keepCount, height, null);
    }

    public void showErrorKeepCount(int keepCount, int height, View errorView) {
        if (keepCount < 0 || keepCount > mListData.size()) {
            return;
        }
        remove(keepCount, mListData.size() - keepCount);
        checkNotContainSpecialCell();
        mIsShowError = true;
        if (errorView != null) {
            mErrorCell.setView(errorView);
        }
        mErrorCell.setHeight(height);
        add(mErrorCell);
    }

    public void keepCount(int keepCount, int height, View view, boolean isShow, AbsStateCell cell) {
        if (keepCount < 0 || keepCount > mListData.size()) {
            return;
        }
        remove(keepCount, mListData.size() - keepCount);
        checkNotContainSpecialCell();
        isShow = true;
        if (view != null) {
            cell.setView(view);
        }
        cell.setHeight(height);
        add(cell);
    }

    public void showError(View errorView){
        showError(errorView,0);
    }

    public void showError(View errorView,int viewHeight){
        if(errorView == null){
            showError();
            return;
        }
        clear();
        mIsShowError = true;
        mErrorCell.setHeight(viewHeight);
        mErrorCell.setView(errorView);
        add(mErrorCell);
    }

    public void hideErorr(){
        if(mListData.contains(mErrorCell)){
            remove(mErrorCell);
            mIsShowError = false;
        }
    }

    public void showLoadMore(){
        if(mListData.contains(mLoadMoreCell)){
            return;
        }
        checkNotContainSpecialCell();
        add(mLoadMoreCell);
        mIsShowLoadMore = true;
    }

    public void showLoadMore(View loadMoreView){
        showLoadMore(loadMoreView,0);
    }

    public void showLoadMore(View loadMoreView,int height){
        if(loadMoreView == null){
            return;
        }
        checkNotContainSpecialCell();
        //设置默认高度
        if(height == 0){
            int defaultHeight = Utils.dpToPx(loadMoreView.getContext(),LoadMoreCell.DEFAULT_HEIGHT);
            mLoadMoreCell.setHeight(defaultHeight);
        }else{
            mLoadMoreCell.setHeight(height);
        }
        mLoadMoreCell.setView(loadMoreView);
        mIsShowLoadMore = true;
        add(mLoadMoreCell);
    }

    public void hideLoadMore(){
        if(mListData.contains(mLoadMoreCell)){
            remove(mLoadMoreCell);
            mIsShowLoadMore = false;
        }
    }

    public boolean isShowLoadMore() {
        return mIsShowLoadMore;
    }

    public void showEmptyKeepCount(int keepCount){
        showEmptyKeepCount(keepCount,0);
    }

    public void showEmptyKeepCount(int keepCount,int height){
        showEmptyKeepCount(keepCount,height,null);
    }

    public void showEmptyKeepCount(int keepCount,int height,View view){
        if(keepCount < 0 || keepCount>mListData.size()){
            return;
        }
        remove(keepCount,mListData.size() - keepCount);
        checkNotContainSpecialCell();
        mIsShowEmpty = true;
        if(view !=null){
            mEmptyCell.setView(view);
        }
        mEmptyCell.setHeight(height);
        add(mEmptyCell);
    }

    public void showEmpty(){
        clear();
        mIsShowEmpty = true;
        add(mEmptyCell);
    }

    public void showEmpty(View emptyView,int viewHeight){
        if(emptyView == null){
            showEmpty();
            return;
        }
        clear();
        mIsShowEmpty = true;
        mEmptyCell.setView(emptyView);
        mEmptyCell.setHeight(viewHeight);
        add(mEmptyCell);
    }

    public void showEmpty(View emptyView){
        showEmpty(emptyView,0);
    }

    public void hideEmpty(){
        if(mListData.contains(mEmptyCell)){
            remove(mEmptyCell);
            mIsShowEmpty = false;
        }
    }

    private void checkNotContainSpecialCell(){
        mListData.remove(mEmptyCell);
        mListData.remove(mErrorCell);
        mListData.remove(mLoadingCell);
        mListData.remove(mLoadMoreCell);
    }

    @Override
    public void clear() {
        mIsShowError = false;
        mIsShowLoading = false;
        mIsShowEmpty = false;
        mIsShowLoadMore = false;
        super.clear();
    }

    public boolean isShowEmpty() {
        return mIsShowEmpty;
    }

    public boolean isShowLoading() {
        return mIsShowLoading;
    }

    public boolean isShowError() {
        return mIsShowError;
    }


}
