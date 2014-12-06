/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

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