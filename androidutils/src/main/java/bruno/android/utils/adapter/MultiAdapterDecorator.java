package bruno.android.utils.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by bruno on 16/11/14.
 */
public class MultiAdapterDecorator implements ListAdapter {

    protected List<ListAdapter> adapterList;

    public MultiAdapterDecorator(List<ListAdapter> adapterList) {
        this.adapterList = adapterList;
    }

    @Override
    public boolean areAllItemsEnabled() {
        for (ListAdapter adapter : adapterList)
            if (!adapter.areAllItemsEnabled())
                return false;
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return findAdapterAtPosition(position).isEnabled();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        for (ListAdapter adapter : adapterList)
            adapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        for (ListAdapter adapter : adapterList)
            adapter.unregisterDataSetObserver(observer);
    }

    @Override
    public int getCount() {
        int count = 0;
        for (ListAdapter adapter : adapterList)
            count += adapter.getCount();
        return count;
    }

    @Override
    public Object getItem(int position) {
        return findAdapterAtPosition(position).getItem();
    }

    @Override
    public long getItemId(int position) {
        return findAdapterAtPosition(position).getItemId();
    }

    @Override
    public boolean hasStableIds() {
        for (ListAdapter adapter : adapterList)
            if (!adapter.hasStableIds())
                return false;
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return findAdapterAtPosition(position).getView(convertView, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return findAdapterAtPosition(position).getItemViewType();
    }

    @Override
    public int getViewTypeCount() {
        int count = 0;
        for (ListAdapter adapter : adapterList)
            count += adapter.getViewTypeCount();
        return count;
    }

    @Override
    public boolean isEmpty() {
        for (ListAdapter adapter : adapterList)
            if (!adapter.isEmpty())
                return false;
        return true;
    }

    private FindResult findAdapterAtPosition(int position) {
        return new FindResult(adapterList, position);
    }

    private static class FindResult {
        ListAdapter adapter;
        int previousAdaptersCount;
        int wantedPosition;

        private FindResult(List<ListAdapter> adapterList, int position) {
            this.wantedPosition = position;
            int count = 0;
            for (ListAdapter adapter : adapterList) {
                int nextCount = count + adapter.getCount();
                if (nextCount > position) {
                    this.adapter = adapter;
                    this.previousAdaptersCount = count;
                    return;
                }
                count = nextCount;
            }
            throw new RuntimeException("Can't find adapter at position " + position);
        }

        boolean isEnabled() {
            return adapter.isEnabled(position());
        }

        private int position() {
            return wantedPosition - previousAdaptersCount;
        }

        public int getItemViewType() {
            return adapter.getItemViewType(position());
        }

        public long getItemId() {
            int position = position();
            long result = adapter.getItemId(position);
            if (result != position)
                return result;
            return wantedPosition;
        }

        public Object getItem() {
            return adapter.getItem(position());
        }

        public View getView(View convertView, ViewGroup parent) {
            return adapter.getView(position(), convertView, parent);
        }
    }

}
