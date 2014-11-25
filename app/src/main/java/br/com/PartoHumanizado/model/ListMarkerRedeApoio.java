package br.com.PartoHumanizado.model;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio holanda on 25-Nov-14.
 */
public class ListMarkerRedeApoio {


    public List<RedeApoioMarker> getMarkers(){
        List<RedeApoioMarker> listaMarker =  new ArrayList<RedeApoioMarker>();

        RedeApoioMarker marker = new RedeApoioMarker();
        marker.setLatLng(new LatLng(-5.787791,-35.211162));
        marker.setTitle("Rama - Rede de apoio à maternidade ativa");
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        RedeApoioMarker marker1 = new RedeApoioMarker();
        marker1.setLatLng(new LatLng(-29.462617,-51.963383));
        marker1.setTitle("Grupo Nascer Sorrindo");
        marker1.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        RedeApoioMarker marker2 = new RedeApoioMarker();
        marker2.setLatLng(new LatLng(-8.057997,-34.888848));
        marker2.setTitle("Espaço Ser Mãe");
        marker2.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        RedeApoioMarker marker3 = new RedeApoioMarker();
        marker3.setLatLng(new LatLng(-15.83034,-48.036998));
        marker3.setTitle("Ishtar – Espaço para Gestantes");
        marker3.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        listaMarker.add(marker);
        listaMarker.add(marker1);
        listaMarker.add(marker2);
        listaMarker.add(marker3);

        return listaMarker;


    }



}
