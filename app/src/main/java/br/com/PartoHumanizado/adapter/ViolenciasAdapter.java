package br.com.PartoHumanizado.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.PartoHumanizado.R;
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

    public class ViewHolder {
        View rootView;

        @InjectView(R.id.container)
        LinearLayout container;
        @InjectView(R.id.titulo)
        TextView titulo;
        @InjectView(R.id.texto)
        TextView texto;
        Violencia violencia;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            ButterKnife.inject(this, rootView);
        }

        public void update(Violencia violencia) {
            this.violencia = violencia;
            titulo.setText(violencia.getTitulo());
            texto.setText(violencia.getTextsAsString());
        }

        private float getAsDp(int dpValue) {
            Resources r = rootView.getContext().getResources();
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, r.getDisplayMetrics());
        }
    }

}
