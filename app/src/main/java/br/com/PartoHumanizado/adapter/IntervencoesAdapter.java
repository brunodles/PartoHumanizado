package br.com.PartoHumanizado.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.model.Intervencao;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 25/11/14.
 */
public class IntervencoesAdapter extends BaseAdapter {

    private static final String TAG = "IntervencoesAdapter";

    List<Intervencao> intevencoes;

    public IntervencoesAdapter(Context context) {
        this.intevencoes = Intervencao.readFromAssets(context);
    }

    @Override
    public int getCount() {
        int size = intevencoes.size();
        Log.d(TAG, "getCount "+size);
        return size;
    }

    @Override
    public Intervencao getItem(int position) {
        return intevencoes.get(position);
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

        viewHolder.update(intevencoes.get(position));

        return convertView;
    }

    public static class ViewHolder {
        View rootView;
        @InjectView(R.id.titulo)
        TextView titulo;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            ButterKnife.inject(this, rootView);
        }

        public void update(Intervencao intervencao) {
            titulo.setText(intervencao.titulo);
        }
    }

}
