package bruno.android.kickstart.adapter;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bruno on 22/11/14.
 */
public class MenuDrawerAdapter extends BaseAdapter {

    List<DrawerItem> itens = new ArrayList<DrawerItem>();

    public MenuDrawerAdapter() {
    }

    public MenuDrawerAdapter(List<DrawerItem> itens) {
        this.itens = itens;
    }

    public boolean add(DrawerItem item) {
        boolean add = itens.add(item);
        notifyDataSetChanged();
        return add;
    }

    public boolean remove(DrawerItem item) {
        return itens.remove(item);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public DrawerItem getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return itens.get(position).getMenuView(position, convertView, parent);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    public static interface DrawerItem {
        String getTitle();

        View getMenuView(int position, View convertView, ViewGroup parent);

        Fragment getFragment();
    }
}
