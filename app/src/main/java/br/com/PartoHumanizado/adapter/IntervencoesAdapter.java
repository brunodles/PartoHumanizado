/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

package br.com.PartoHumanizado.adapter;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        @InjectView(R.id.justificativa)
        TextView justificativa;
        @InjectView(R.id.porQueEDesnecessaria)
        TextView porQueEDesnecessaria;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            ButterKnife.inject(this, rootView);
        }

        public void update(Intervencao intervencao) {
            titulo.setText(intervencao.getTitulo());
            justificativa.setText(Html.fromHtml(
                    "<b>Por quê ainda é Feita: </b>" + intervencao.getJustificativa()));
            porQueEDesnecessaria.setText(Html.fromHtml(
                    "<b>Por quê é desnecessária: </b>" + intervencao.getPorQueEDesnecessaria()));
        }
    }

}
