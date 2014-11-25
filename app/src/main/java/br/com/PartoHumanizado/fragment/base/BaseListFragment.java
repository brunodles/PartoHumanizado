package br.com.PartoHumanizado.fragment.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.adapter.MenuDrawerAdapter;
import br.com.PartoHumanizado.viewholder.ItemMenuDrawerViewHolder;

/**
 * Created by bruno on 25/11/14.
 */
public abstract class BaseListFragment extends ListFragment implements MenuDrawerAdapter.DrawerItem {

    @Override
    public View getMenuView(int position, View convertView, ViewGroup parent) {
        ItemMenuDrawerViewHolder holder = ItemMenuDrawerViewHolder.getView(convertView, parent);
        holder.setTittle(getTitle());

        return holder.view;
    }


    @Override
    public Fragment getFragment() {
        return this;
    }
}