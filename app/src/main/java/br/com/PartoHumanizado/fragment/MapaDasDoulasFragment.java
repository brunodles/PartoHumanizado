package br.com.PartoHumanizado.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.MapsFragment;
import br.com.PartoHumanizado.model.DolasMarker;
import br.com.PartoHumanizado.model.RedeApoioMarker;
import bruno.android.utils.gps.GpsClient;

/**
 * Created by bruno on 22/11/14.
 */
public class MapaDasDoulasFragment extends MapsFragment {

    private final String TAG = "PARTO-HUMANIZADO";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        //List<MarkerOptions> listaMarker = setMarker();
        //addMarkers(listaMarker);
        //animateCamera(new LatLng(-5.787705, -35.21113), 10);
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
                Log.e(TAG, "ERRO GEOLOCALIZAÇÃO FOURSQUARE " + e.getMessage());

            }
        }else{
            gpsCliente.showSettingsAlert();
        }

    }

    private List<MarkerOptions> setMarker() {

        List<DolasMarker> listaMarker;
        MarkerOptions markerOptions;
        List<MarkerOptions> listaMarkers = new ArrayList<MarkerOptions>();


        listaMarker = DolasMarker.readFromAssets(getActivity());


        for (DolasMarker redeApoioMarker : listaMarker) {
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
        return "Mapa das Doulas";
    }
}
