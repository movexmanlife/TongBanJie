package com.robot.tongbanjie.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerOnScrollListener.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = false; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 2; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private RecyclerView recyclerView;

    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager, RecyclerView recyclerView) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.recyclerView = recyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        /**
         * java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling
         * 由于这里hideLoadingMore()引起onScrolled()调用，而onScrolled()引起showLoadingMore()调用，出现上述的错误。
         */
        if (recyclerView.isComputingLayout()) {
            return;
        }

        if (dy < 0) return;

        // check for scroll down
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        synchronized (this) {
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached, Do something
                current_page++;
                onLoadMore(current_page);
                loading = true;
            }
        }
    }


    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public abstract void onLoadMore(int current_page);
}