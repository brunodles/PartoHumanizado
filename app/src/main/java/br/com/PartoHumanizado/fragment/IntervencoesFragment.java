package br.com.PartoHumanizado.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.adapter.MenuDrawerAdapter;
import br.com.PartoHumanizado.viewholder.ItemMenuDrawerViewHolder;

/**
 * Created by bruno on 22/11/14.
 */
public class IntervencoesFragment extends Fragment implements MenuDrawerAdapter.DrawerItem {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = new View(container.getContext());
        view.setBackgroundColor(Color.parseColor("#ff0000"));
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getTitle() {
        return "Intervenções";
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
