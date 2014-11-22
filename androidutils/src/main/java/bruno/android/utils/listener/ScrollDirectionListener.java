package bruno.android.utils.listener;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by dev on 14/11/2014.
 */
public class ScrollDirectionListener implements ListView.OnScrollListener {

    private int lastScrollY;
    private int ignorableSize = 15;
    private ListView.OnScrollListener innerListener;
    private DirectionListener directionListener;

    public ScrollDirectionListener(DirectionListener directionListener) {
        this.directionListener = directionListener;
    }

    public ScrollDirectionListener setInnerListener(ListView.OnScrollListener innerListener) {
        this.innerListener = innerListener;
        return this;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (innerListener != null)
            innerListener.onScrollStateChanged(absListView, i);
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int newScrollY = getScroll(absListView);
        int scrollSize = Math.abs(newScrollY - lastScrollY);
        if (scrollSize < ignorableSize)
            return;

        if (newScrollY > lastScrollY)
            scrollDown();
        else if (newScrollY < lastScrollY)
            scrollUp();
        lastScrollY = newScrollY;

        if (innerListener != null)
            innerListener.onScroll(absListView, firstVisibleItem, visibleItemCount, totalItemCount);
    }

    private Dictionary<Integer, Integer> listViewItemHeights = new Hashtable<Integer, Integer>();

    private int getScroll(AbsListView absListView) {
        View c = absListView.getChildAt(0); //this is the first visible row
        if (c == null)
            return 0;
        int scrollY = -c.getTop();
        listViewItemHeights.put(absListView.getFirstVisiblePosition(), c.getHeight());
        for (int i = 0; i < absListView.getFirstVisiblePosition(); ++i) {
            if (listViewItemHeights.get(i) != null) // (this is a sanity check)
                scrollY += listViewItemHeights.get(i); //add all heights of the views that are gone
        }
        return scrollY;
    }

    private void scrollUp() {
        if (directionListener != null)
            directionListener.onScrollUp();
    }

    private void scrollDown() {
        if (directionListener != null)
            directionListener.onScrollDown();
    }

    private int getListViewScrollY(AbsListView absListView) {
        // to be used when all views have da same height
        View topChild = absListView.getChildAt(0);
        return topChild == null
                ? 0
                : absListView.getFirstVisiblePosition() * topChild.getHeight() - topChild.getTop();
    }

    public static interface DirectionListener {
        void onScrollUp();

        void onScrollDown();
    }
}