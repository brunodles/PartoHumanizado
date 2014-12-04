package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import bruno.android.utils.adapter.FragmentPageAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 26/11/14.
 */
public class IntervencoesViolenciasFragment extends BaseFragment {

    @InjectView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, null);
        ButterKnife.inject(this, view);

        FragmentPageAdapter pageAdapter = new FragmentPageAdapter(getChildFragmentManager());

        IntervencoesFragment intervencoes = new IntervencoesFragment();
        pageAdapter.addFragment(intervencoes, intervencoes.getTitle());

        ViolenciasFragment violenciasFragment = new ViolenciasFragment();
        pageAdapter.addFragment(violenciasFragment, violenciasFragment.getTitle());

        viewPager.setAdapter(pageAdapter);

        return view;
    }

    @Override
    public String getTitle() {
        return "Intevenções e Violências";
    }
}
