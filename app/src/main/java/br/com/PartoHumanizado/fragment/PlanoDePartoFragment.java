/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import br.com.PartoHumanizado.fragment.base.ResStringArrayListFragment;
import br.com.PartoHumanizado.fragment.base.TextFragment;
import bruno.android.utils.adapter.FragmentPageAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 04/12/14.
 */
public class PlanoDePartoFragment extends BaseFragment {

    @InjectView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, null);
        ButterKnife.inject(this, view);

        FragmentPageAdapter pageAdapter = new FragmentPageAdapter(getChildFragmentManager());

//        TextFragment sobreFragment = TextFragment.create("monteSeuPlanoDeParto.txt", "Monte seu plano de parto");
//        pageAdapter.addFragment(sobreFragment, sobreFragment.getTitle());

        addFragment(pageAdapter, new TrabalhoDePartoFragment());
        addFragment(pageAdapter, new DuranteParto());
        addFragment(pageAdapter, new PosParto());
        addFragment(pageAdapter, new CuidadosComBebe());
        addFragment(pageAdapter, new CasoCesarea());

        viewPager.setAdapter(pageAdapter);

        return view;
    }

    private void addFragment(FragmentPageAdapter pageAdapter, ResStringArrayListFragment fragment) {
        pageAdapter.addFragment(fragment, fragment.getTitle());
    }

    @Override
    public String getTitle() {
        return "Plano de Parto";
    }


    public static class TrabalhoDePartoFragment extends ResStringArrayListFragment {

        public TrabalhoDePartoFragment() {
            super("Trabalho de Parto", R.array.trabalhoDeParto);
        }
    }

    public static class DuranteParto extends ResStringArrayListFragment {

        public DuranteParto() {
            super("Durante o Parto", R.array.duranteParto);
        }
    }

    public static class PosParto extends ResStringArrayListFragment {

        public PosParto() {
            super("Pós-Parto", R.array.posParto);
        }
    }

    public static class CuidadosComBebe extends ResStringArrayListFragment {

        public CuidadosComBebe() {
            super("Cuidados com o Bebê", R.array.cuidadosComBebe);
        }
    }

    public static class CasoCesarea extends ResStringArrayListFragment {

        public CasoCesarea() {
            super("Caso de Cesárea", R.array.casoCesarea);
        }
    }
}
