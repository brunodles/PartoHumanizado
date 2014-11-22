package bruno.android.utils.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/**
 * Created by dev on 04/11/2014.
 */
public abstract class HeaderAdapter implements ListAdapter {

//    private static final String TAG = "HeaderAdapter";

    private ListAdapter innerAdapter;

    public HeaderAdapter(ListAdapter innerAdapter) {
        this.innerAdapter = innerAdapter;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        innerAdapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        innerAdapter.unregisterDataSetObserver(observer);
    }

    @Override
    public int getCount() {
        return innerAdapter.getCount() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position == 0)
            return position;
//        if (position > inner.getCount())
//            return getFooterItem(position);
        return innerAdapter.getItem(position - 1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.d(TAG, "getView position " + position);
        if (position == 0)
            return getHeaderView(convertView, parent);
        return innerAdapter.getView(position - 1, convertView, parent);
    }

    protected abstract View getHeaderView(View convertView, ViewGroup parent);

    @Override
    public int getItemViewType(int position) {
        int type;
        if (position == 0)
            type = getViewTypeCount()-1;
        else
            type = innerAdapter.getItemViewType(position - 1);
//        Log.d(TAG, String.format("getItemViewType position %s type %s", position, type));
        return type;
    }

    @Override
    public int getViewTypeCount() {
        int count = innerAdapter.getViewTypeCount() + 1;
//        Log.d(TAG, "getViewTypeCount " + count);
        return count;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return innerAdapter.areAllItemsEnabled();
    }

    @Override
    public boolean isEnabled(int position) {
        return innerAdapter.isEnabled(position);
    }
}
