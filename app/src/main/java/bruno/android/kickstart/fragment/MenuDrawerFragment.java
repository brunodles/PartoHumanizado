package bruno.android.kickstart.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import bruno.android.kickstart.adapter.MenuDrawerAdapter;

/**
 * Created by bruno on 22/11/14.
 */
public class MenuDrawerFragment extends ListFragment {

    private MenuDrawerAdapter menuDrawerAdapter = new MenuDrawerAdapter();
    MenuListener menuListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        setListAdapter(menuDrawerAdapter);
        super.onStart();
    }

    public MenuDrawerAdapter getMenuDrawerAdapter() {
        return menuDrawerAdapter;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MenuDrawerAdapter.DrawerItem item = menuDrawerAdapter.getItem(position);
        menuListener.onSelect(position, item.getTitle(), item.getFragment());
    }

    public static interface MenuListener {
        void onSelect(int position, String title, Fragment fragment);
    }
}
