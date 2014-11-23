package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bruno on 22/11/14.
 */
public class MapaDeRedeDeApoioFragment extends MapsFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }


    @Override
    public String getTitle() {
        return "Mapa Rede de Apoio";
    }


}
