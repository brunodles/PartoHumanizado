/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

package br.com.PartoHumanizado.fragment.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.util.CsvAssetReader;
import bruno.android.utils.gps.GpsClient;
import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by sergio holanda on 22-Nov-14.
 */
public abstract class MapsFragment extends BaseFragment {

    private GoogleMap googleMap;
    private final String TAG = "PARTO-HUMANIZADO";
    @InjectView(R.id.mapview)
    MapView mapView;
    @InjectView(R.id.et_info_mapa)
    TextView textInfo;
    @InjectView(R.id.et_telefone_mapa)
    TextView textTelefone;
    @InjectView(R.id.view_info)
    RelativeLayout viewInfo;
    @InjectView(R.id.et_endereco)
    TextView textEndereco;
    @InjectView(R.id.view_background)
    RelativeLayout viewBackground;
    @InjectView(R.id.button_call_map)
    CircularImageView buttonCall;
    private String numeroTelefone;
    private String separatorRegex = "/";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map_rede_apoio, container, false);
        ButterKnife.inject(this,rootView);
        mapView.onCreate(savedInstanceState);
        viewBackground.setOnClickListener(hideViewOnclick);
        buttonCall.setOnClickListener(onClickListenerCall);
        buildMap();

        return rootView;
    }

    private void buildMap() {

        googleMap = mapView.getMap();
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(markerClickListener);


        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            Log.d(TAG, "Erro build map : " + e.getMessage());
        }

    }

    public void animateCamera(LatLng latLng, int zoom) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        googleMap.animateCamera(cameraUpdate);

    }

    public void animateMarker(LatLng latLng, int zoom, final Marker marker) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, zoom);
        googleMap.animateCamera(cameraUpdate, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                openInfo(marker);
            }

            @Override
            public void onCancel() {

            }
        });

    }

    public void addMarkers(List<MarkerOptions> markers) {


        for (MarkerOptions marker : markers) {
            googleMap.addMarker(marker);
        }

    }

    private GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            animateMarker(marker.getPosition(),13,marker);
            return true;
        }
    };
    public void addInfo(String info,String telefone){

        numeroTelefone = telefone;
        textInfo.setText(info);
        textEndereco.setText(CsvAssetReader.splitString(telefone,"//")[1]);
        textTelefone.setText(CsvAssetReader.splitString(telefone,"//")[0]);

    }


    private void openInfo(Marker marker){

        Animation animationIn = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_up);
        viewBackground.setVisibility(View.VISIBLE);
        viewInfo.setVisibility(View.VISIBLE);
        viewInfo.setAnimation(animationIn);
        addInfo(marker.getTitle(),marker.getSnippet());
    }

    private void hideInfo(){
       animateMap();
    }
    private void animateMap(){
        GpsClient gpsCliente = new GpsClient(getActivity());
        if(gpsCliente.canGetLocation()){
            try {
                Animation animationOut = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_down);
                viewInfo.setVisibility(View.GONE);
                viewBackground.setVisibility(View.GONE);
                viewInfo.setAnimation(animationOut);

                animateCamera(new LatLng(gpsCliente.getLatitude(), gpsCliente.getLongitude()),7);
            } catch (Exception e) {
                Log.e(TAG, "ERRO GEOLOCALIZAÇÃO FOURSQUARE "+e.getMessage());

            }
        }else{
            Animation animationOut = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_down);
            viewInfo.setVisibility(View.GONE);
            viewBackground.setVisibility(View.GONE);
            viewInfo.setAnimation(animationOut);

            animateCamera(new LatLng(15.7217621,-47.9382362),7);
            gpsCliente.showSettingsAlert();
        }
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
    private View.OnClickListener hideViewOnclick =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hideInfo();
        }
    };

    private View.OnClickListener onClickListenerCall =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            calll();
        }
    };

    private void calll(){
        Log.d(TAG,"call "+numeroTelefone);
        String[] strings = CsvAssetReader.splitString(numeroTelefone, separatorRegex);
        if(strings[0]!=null){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+strings[0]));
            startActivity(intent);
        }

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public String getTitle() {
        return "Mapa";
    }


}
