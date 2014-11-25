package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.adapter.ViolenciasAdapter;
import br.com.PartoHumanizado.fragment.base.BaseListFragment;

/**
 * Created by bruno on 22/11/14.
 */
public class ViolenciasFragment extends BaseListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intervencoes, null);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setListAdapter(new ViolenciasAdapter(getActivity()));
    }

    @Override
    public String getTitle() {
        return "Violências";
    }
}
