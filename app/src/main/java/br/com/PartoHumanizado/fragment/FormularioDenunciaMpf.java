package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.PartoHumanizado.fragment.base.WebViewFragment;

/**
 * Created by sergio holanda on 04-Dec-14.
 */
public class FormularioDenunciaMpf extends WebViewFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
                loadUrl("http://cidadao.mpf.mp.br/formularios/formulario-eletronico");
                return view;
    }

    @Override
    public String getTitle() {
        return "Denúnicia MPF";
    }
}
