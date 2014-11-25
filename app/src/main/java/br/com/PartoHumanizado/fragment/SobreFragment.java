package br.com.PartoHumanizado.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.fragment.base.BaseFragment;

/**
 * Created by bruno on 22/11/14.
 */
public class SobreFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = new View(container.getContext());
        view.setBackgroundColor(Color.parseColor("#0000ff"));
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getTitle() {
        return "Sobre";
    }
}
