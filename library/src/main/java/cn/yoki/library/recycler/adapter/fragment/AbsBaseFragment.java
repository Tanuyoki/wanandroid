package cn.yoki.library.recycler.adapter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import cn.yoki.library.R;
import cn.yoki.library.recycler.adapter.base.Cell;
import cn.yoki.library.recycler.adapter.base.SimpleAdapter;

public abstract class AbsBaseFragment<T> extends Fragment {

    private RecyclerView mRecyclerView;
    private SimpleAdapter mSimpleAdapter;
    private FrameLayout mContainer;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * RecyclerView 最后可见Item在Adapter中的位置
     */
    private int mLastVisiblePosition = -1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.rv_base_fragment_layout,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = view.findViewById(R.id.base_refresh_layout);
        mContainer = view.findViewById(R.id.toolbar_container);
        mRecyclerView = view.findViewById(R.id.base_fragment_rv);
        mRecyclerView.setLayoutManager(initLayoutManger());
        mSimpleAdapter = initAdapter();
        mRecyclerView.setAdapter(mSimpleAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRefreshing(true);
                onPullRefresh();
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if(layoutManager instanceof LinearLayoutManager){
                    mLastVisiblePosition = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                }else if(layoutManager instanceof GridLayoutManager){
                    mLastVisiblePosition = ((GridLayoutManager)layoutManager).findLastVisibleItemPosition();
                }else if(layoutManager instanceof StaggeredGridLayoutManager){
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    int []lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                    mLastVisiblePosition = findMax(lastPositions);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                View firstView = recyclerView.getChildAt(0);
                int top = firstView.getTop();
                int topEdge = recyclerView.getPaddingTop();
                //判断RecyclerView 的ItemView是否满屏，如果不满一屏，上拉不会触发加载更多
                boolean isFullScreen = top < topEdge;

                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                int itemCount = manager.getItemCount();
                //因为LoadMore View  是Adapter的一个Item,显示LoadMore 的时候，Item数量＋1了，导致 mLastVisibalePosition == itemCount-1
                // 判断两次都成立，因此必须加一个判断条件 !mBaseAdapter.isShowLoadMore()
                if(newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisiblePosition == itemCount-1 && isFullScreen && !mSimpleAdapter.isShowLoadMore()){
                    //最后一个Item了
                    mSimpleAdapter.showLoadMore();
                    onLoadMore();
                }
            }
        });
        View toolbarView = addToolbar();
        if(toolbarView!=null && mContainer!=null
        ){
            mContainer.addView(toolbarView);
        }
        onRecyclerViewInitialized();

    }

    /**
     * hide load more progress
     */
    public void hideLoadMore(){
        if(mSimpleAdapter!=null){
            mSimpleAdapter.hideLoadMore();
        }
    }

    /**
     * 获取组数最大值
     * @param lastPositions
     * @return
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * 设置刷新进度条的颜色
     * see{@link SwipeRefreshLayout#setColorSchemeResources(int...)}
     * @param colorResIds
     */
    public void setColorSchemeResources(@ColorRes int... colorResIds){
        if(mSwipeRefreshLayout!=null){
            mSwipeRefreshLayout.setColorSchemeResources(colorResIds);
        }
    }

    /**
     * 设置刷新进度条的颜色
     * see{@link SwipeRefreshLayout#setColorSchemeColors(int...)}
     * @param colors
     */
    public void setColorSchemeColors(int... colors){
        if(mSwipeRefreshLayout!=null){
            mSwipeRefreshLayout.setColorSchemeColors(colors);
        }
    }

    /**
     * 设置刷新进度条背景色
     *  see{@link SwipeRefreshLayout#setProgressBackgroundColorSchemeResource(int)} (int)}
     * @param colorRes
     */
    public void setProgressBackgroundColorSchemeResource(@ColorRes int colorRes) {
        if(mSwipeRefreshLayout!=null){
            mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(colorRes);
        }
    }

    /**
     * 设置刷新进度条背景色
     *  see{@link SwipeRefreshLayout#setProgressBackgroundColorSchemeColor(int)}
     * @param color
     */
    public void setProgressBackgroundColorSchemeColor(@ColorInt int color) {
        if(mSwipeRefreshLayout!=null){
            mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(color);
        }
    }

    /**
     * Notify the widget that refresh state has changed. Do not call this when
     * refresh is triggered by a swipe gesture.
     *
     * @param refreshing Whether or not the view should show refresh progress.
     */
    public void setRefreshing(boolean refreshing){
        if(mSwipeRefreshLayout== null){
            return;
        }
        mSwipeRefreshLayout.setRefreshing(refreshing);
    }

    /**
     * 子类可以自己指定Adapter,如果不指定默认RVSimpleAdapter
     * @return
     */
    protected SimpleAdapter initAdapter(){
        return new SimpleAdapter();
    }

    /**
     * 子类自己指定RecyclerView的LayoutManager,如果不指定，默认为LinearLayoutManager,VERTICAL 方向
     * @return
     */
    protected RecyclerView.LayoutManager initLayoutManger(){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    /**
     * 添加TitleBar
     * @param
     */
    public View addToolbar(){
        //如果需要Toolbar,子类返回Toolbar View
        return null;
    }

    /**
     *RecyclerView 初始化完毕，可以在这个方法里绑定数据
     */
    public abstract void onRecyclerViewInitialized();

    /**
     * 下拉刷新
     */
    public abstract void onPullRefresh();

    /**
     * 上拉加载更多
     */
    public abstract void onLoadMore();

    /**
     *  根据实体生成对应的Cell
     * @param list  实体列表
     * @return cell列表
     */
    protected abstract List<Cell> getCells(List<T> list);


}
