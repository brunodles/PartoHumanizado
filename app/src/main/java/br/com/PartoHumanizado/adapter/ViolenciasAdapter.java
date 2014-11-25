package br.com.PartoHumanizado.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.model.Intervencao;
import br.com.PartoHumanizado.model.Violencia;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 25/11/14.
 */
public class ViolenciasAdapter extends BaseAdapter {

    private static final String TAG = "IntervencoesAdapter";

    List<Violencia> objecs;

    public ViolenciasAdapter(Context context) {
        this.objecs = Violencia.readFromAssets(context);
    }

    @Override
    public int getCount() {
        int size = objecs.size();
        return size;
    }

    @Override
    public Violencia getItem(int position) {
        return objecs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = parent.inflate(parent.getContext(), R.layout.item_violencia, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.update(objecs.get(position));

        return convertView;
    }

    public static class ViewHolder {
        View rootView;
        @InjectView(R.id.titulo)
        TextView titulo;
        @InjectView(R.id.texto)
        TextView texto;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            ButterKnife.inject(this, rootView);
        }

        public void update(Violencia violencia) {
            titulo.setText(violencia.getTitulo());
            texto.setText(violencia.getTextsAsString());
        }
    }

}
