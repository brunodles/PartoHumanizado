package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import br.com.PartoHumanizado.fragment.base.MapsFragment;

/**
 * Created by bruno on 22/11/14.
 */
public class MapaDasDoulasFragment extends MapsFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        List<MarkerOptions> listaMarker = setMarker();
        addMarkers(listaMarker);
        animateCamera(new LatLng(-5.787705, -35.21113), 10);
        return view;
    }

    private List<MarkerOptions> setMarker() {
        List<MarkerOptions> listaMarker = new ArrayList<MarkerOptions>();
        MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(-5.787791, -35.211162))
                .title("Rama - Rede de apoio à maternidade ativa")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        listaMarker.add(marker);

        return listaMarker;
    }

    @Override
    public String getTitle() {
        return "Mapa das Doulas";
    }
}
