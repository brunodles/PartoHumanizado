package br.com.PartoHumanizado.fragment.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.adapter.MenuDrawerAdapter;
import br.com.PartoHumanizado.viewholder.ItemMenuDrawerViewHolder;

/**
 * Created by bruno on 22/11/14.
 */
public abstract class BaseFragment extends Fragment implements MenuDrawerAdapter.DrawerItem {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        setRetainInstance(true);
    }

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
