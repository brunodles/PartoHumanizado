package br.com.PartoHumanizado.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import br.com.PartoHumanizado.R;

/**
 * Created by bruno on 25/11/14.
 */
public class IntervencoesAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = parent.inflate(parent.getContext(), R.layout.item_intervencao, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public static class ViewHolder {
        View rootView;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
        }
    }

}
