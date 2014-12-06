/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

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
