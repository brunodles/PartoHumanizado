package br.com.PartoHumanizado.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.adapter.MenuDrawerAdapter;

/**
 * Created by bruno on 22/11/14.
 */
public class MenuDrawerFragment extends ListFragment {

    public static final String PREFERENCES_NAME = "menuDrawer";
    public static final String KEY_LAST_SELECTED = "lastSelected";
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
        View view = inflater.inflate(R.layout.fragment_navigation_drawer, null);
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        setListAdapter(menuDrawerAdapter);
        showLastSelectedFragment(getActivity());
    }

    private void showLastSelectedFragment(Context context) {
        if (menuListener == null)
            return;
        int position = getPreferences(context).getInt(KEY_LAST_SELECTED, 0);
        MenuDrawerAdapter.DrawerItem item = menuDrawerAdapter.getItem(position);
        menuListener.onMenuSelect(position, item.getTitle(), item.getFragment());
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
        Context context = l.getContext();
        saveLastSelected(position, context);
    }

    private void saveLastSelected(int position, Context context) {
        SharedPreferences preferences = getPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(KEY_LAST_SELECTED, position);
        edit.commit();
    }

    private SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static interface MenuListener {
        void onMenuSelect(int position, String title, Fragment fragment);
    }
}
