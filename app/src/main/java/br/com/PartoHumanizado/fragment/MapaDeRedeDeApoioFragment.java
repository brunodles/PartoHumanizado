package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bruno on 22/11/14.
 */
public class MapaDeRedeDeApoioFragment extends WebViewFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        loadUrl("https://www.google.com/maps/d/viewer?mid=zIvkIuv-L1Pc.k5JnqQJn8CpQ");
        return view;
    }

    @Override
    public String getTitle() {
        return "Mapa de Rede de Apoio";
    }
}
