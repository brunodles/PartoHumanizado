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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.MapsFragment;
import br.com.PartoHumanizado.model.ListMarkerRedeApoio;
import br.com.PartoHumanizado.model.RedeApoioMarker;
import bruno.android.utils.gps.GpsClient;


/**
 * Created by bruno on 22/11/14.
 */
public class MapaDeRedeDeApoioFragment extends MapsFragment {

    private final String TAG = "PARTO-HUMANIZADO";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<MarkerOptions> listaMarker = setMarker();
        addMarkers(listaMarker);
        animateMap();

    }

    private void animateMap(){
        GpsClient gpsCliente = new GpsClient(getActivity());
        if(gpsCliente.canGetLocation()){
            try {
                animateCamera(new LatLng(gpsCliente.getLatitude(), gpsCliente.getLongitude()),7);
            } catch (Exception e) {
                Log.e(TAG, "ERRO GEOLOCALIZAÇÃO FOURSQUARE "+e.getMessage());

            }
        }else{
            gpsCliente.showSettingsAlert();
        }

    }
    private LatLng getLatLng() {

        return null;


    }

    private List<MarkerOptions> setMarker() {

        List<RedeApoioMarker> listaMarker;
        MarkerOptions markerOptions;
        List<MarkerOptions> listaMarkers = new ArrayList<MarkerOptions>();


        listaMarker = RedeApoioMarker.readFromAssets(getActivity());


        for (RedeApoioMarker redeApoioMarker : listaMarker) {
            markerOptions = new MarkerOptions()
                    .title(redeApoioMarker.getTitle())
                    .position(redeApoioMarker.getLatLng())
                    .snippet(redeApoioMarker.getTelefone()+"//"+redeApoioMarker.getEndereco())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));

            listaMarkers.add(markerOptions);
          //  addInfo(redeApoioMarker.getTitle(),redeApoioMarker.getTelefone());

        }

        return listaMarkers;


    }

    @Override
    public String getTitle() {
        return "Mapa Rede de Apoio";
    }


}
