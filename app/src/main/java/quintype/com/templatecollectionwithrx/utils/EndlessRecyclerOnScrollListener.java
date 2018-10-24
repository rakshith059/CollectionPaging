package quintype.com.templatecollectionwithrx.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Custom Scroll listener for RecyclerView.
 * Based on implementation https://gist.github.com/ssinss/e06f12ef66c51252563e
 */
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = "EndlessScrollListener";
    int firstVisibleItem, visibleItemCount, totalItemCount;
    RecyclerViewPositionHelper mRecyclerViewHelper;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 3; // The minimum amount of items to have below your current scroll position before loading more.
    private int currentPage = 0;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mRecyclerViewHelper.getItemCount();
        firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            // Do something
            currentPage++;

            onLoadMore(currentPage);

            loading = true;
        }
    }

    // Call whenever doing swipe refresh
    public void resetState() {
        this.currentPage = 0;
        this.previousTotal = 0;
        this.loading = true;
    }

    //Start loading
    public abstract void onLoadMore(int currentPage);
}
