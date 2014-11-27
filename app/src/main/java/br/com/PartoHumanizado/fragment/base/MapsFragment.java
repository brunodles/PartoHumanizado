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
    @InjectView(R.id.view_background)
    RelativeLayout viewBackground;
    @InjectView(R.id.button_call_map)
    Button buttonCall;
    private String numeroTelefone;
    private String separatorRegex = "/";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map_rede_apoio, container, false);
        ButterKnife.inject(this,rootView);
     //   mapView = (MapView) rootView.findViewById(R.id.mapview);
      //  viewInfo = (RelativeLayout)rootView.findViewById(R.id.view_info);
      //  viewBackground = (RelativeLayout)rootView.findViewById(R.id.view_background);
     //   textInfo = (TextView) rootView.findViewById(R.id.et_info_mapa);
       // textTelefone = (TextView)rootView.findViewById();
     //   buttonCall =(Button)rootView.findViewById(R.id.button_call_map);
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

    public void addMarkers(List<MarkerOptions> markers) {


        for (MarkerOptions marker : markers) {
            googleMap.addMarker(marker);
        }

    }

    private GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            openInfo(marker);
            return true;
        }
    };
    public void addInfo(String info,String telefone){
        Log.d("PARTO-HUMANIZADO","TITLE "+info + " telefone "+telefone);
        numeroTelefone = telefone;
        textInfo.setText(info);
        textTelefone.setText(telefone);

    }


    private void openInfo(Marker marker){
        Animation animationIn = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in_up);
        viewBackground.setVisibility(View.VISIBLE);
        viewInfo.setVisibility(View.VISIBLE);
        viewInfo.setAnimation(animationIn);
        addInfo(marker.getTitle(),marker.getSnippet());
    }

    private void hideInfo(){
        Animation animationOut = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out_down);
        viewInfo.setVisibility(View.GONE);
        viewBackground.setVisibility(View.GONE);
        viewInfo.setAnimation(animationOut);
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
