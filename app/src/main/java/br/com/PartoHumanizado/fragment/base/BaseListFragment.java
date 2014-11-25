package br.com.PartoHumanizado.fragment.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.adapter.MenuDrawerAdapter;
import br.com.PartoHumanizado.viewholder.ItemMenuDrawerViewHolder;
import bruno.android.utils.adapter.AnimatedListAdapter;

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
    public void setListAdapter(ListAdapter adapter) {
        super.setListAdapter(new AnimatedListAdapter(adapter, R.anim.timeline));
    }

    @Override
    public Fragment getFragment() {
        return this;
    }
}