package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import br.com.PartoHumanizado.fragment.base.TextFragment;
import bruno.android.utils.adapter.FragmentPageAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 26/11/14.
 */
public class InformacoesFragment extends BaseFragment {

    private static final String TAG = "InformacoesFragment";

    @InjectView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, null);
        ButterKnife.inject(this, view);


        FragmentPageAdapter pageAdapter = new FragmentPageAdapter(getChildFragmentManager());

        addFragment(pageAdapter, TextFragment.create("partoHumanizado.txt", "Parto Humanizado"));
        addFragment(pageAdapter, TextFragment.create("violenciaObstetrica.txt", "Violência Obstétrica"));
        addFragment(pageAdapter, TextFragment.create("doulas.txt", "Doulas"));
        addFragment(pageAdapter, TextFragment.create("planoDeParto.txt", "Plano de Parto"));

        viewPager.setAdapter(pageAdapter);

        return view;
    }

    private void addFragment(FragmentPageAdapter pageAdapter, TextFragment fragment) {
        pageAdapter.addFragment(fragment, fragment.getTitle());
    }

    @Override
    public String getTitle() {
        return "Saiba Mais";
    }
}
