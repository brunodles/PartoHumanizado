package br.com.PartoHumanizado.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bruno on 22/11/14.
 */
public class ItemMenuDrawerViewHolder {

    public View view;

    private ItemMenuDrawerViewHolder(View view) {
        this.view = view;
    }

    public static ItemMenuDrawerViewHolder getView(View convertView, ViewGroup parent) {
        ItemMenuDrawerViewHolder holder;
        if (convertView == null) {
            convertView = newView(parent);
            holder = new ItemMenuDrawerViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ItemMenuDrawerViewHolder) convertView.getTag();
        }

        return holder;
    }

    public void setTittle(String tittle) {
        ((TextView) view).setText(tittle);
    }

    private static View newView(ViewGroup parent) {
        TextView view = new TextView(parent.getContext());
        view.setPadding(30, 30, 30, 30);
        return view;
    }
}
