package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bruno on 22/11/14.
 */
public class DenucieFragment extends WebViewFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        String url = "http://cidadao.mpf.mp.br/formularios/formulario-eletronico";
        loadUrl(url);
        return view;
    }

    @Override
    public String getTitle() {
        return "Denuncie";
    }
}
