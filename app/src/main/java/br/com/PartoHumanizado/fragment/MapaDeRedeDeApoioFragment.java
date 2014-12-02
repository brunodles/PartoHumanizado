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
        List<MarkerOptions> listaMarker = setMarker();
        addMarkers(listaMarker);
        animateCamera(new LatLng(-15.8402169, -47.9065729),7);
        try {
            Log.d(TAG, "location " + getLatLng());
        } catch (Exception e) {

        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private LatLng getLatLng() {
        GpsClient gpsClient = new GpsClient(getActivity());
        if (gpsClient.canGetLocation()) {
            return new LatLng(gpsClient.getLatitude(), gpsClient.getLongitude());
        } else {
            gpsClient.showSettingsAlert();
            return null;
        }
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
                    .snippet(redeApoioMarker.getTelefone())
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
