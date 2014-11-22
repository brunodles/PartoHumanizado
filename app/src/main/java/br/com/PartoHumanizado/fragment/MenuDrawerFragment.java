package br.com.PartoHumanizado.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.PartoHumanizado.adapter.MenuDrawerAdapter;

/**
 * Created by bruno on 22/11/14.
 */
public class MenuDrawerFragment extends ListFragment {

    private MenuDrawerAdapter menuDrawerAdapter = new MenuDrawerAdapter();
    MenuListener menuListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            menuListener = (MenuListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement MenuListener.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        menuListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(bruno.android.PartoHumanizado.R.layout.fragment_navigation_drawer, null);
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
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
        getListView().setItemChecked(position, true);
        if (menuListener != null) {
            MenuDrawerAdapter.DrawerItem item = menuDrawerAdapter.getItem(position);
            menuListener.onMenuSelect(position, item.getTitle(), item.getFragment());
        }
    }

    public static interface MenuListener {
        void onMenuSelect(int position, String title, Fragment fragment);
    }
}
