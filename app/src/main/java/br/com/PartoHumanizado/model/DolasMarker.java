package br.com.PartoHumanizado.model;

import android.content.Context;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import br.com.PartoHumanizado.util.ModelCsvAssetReader;

/**
 * Created by sergio holanda on 25-Nov-14.
 */
public class DolasMarker {

    public static List<DolasMarker> readFromAssets(Context context) {
        CsvAssetReader csvAssetReader = new CsvAssetReader(context);
        csvAssetReader.read();
        return csvAssetReader.getObjects();
    }

    private LatLng latLng;

    private String title;

    private String endereco;

    private String telefone;

    private String site;

    private BitmapDescriptor icon;

    private MarkerOptions markerOptions;

    public String getSite() {
        return site;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BitmapDescriptor getIcon() {
        return icon;
    }

    public void setIcon(BitmapDescriptor icon) {
        this.icon = icon;
    }

    private static final class CsvAssetReader extends ModelCsvAssetReader<DolasMarker> {

        public CsvAssetReader(Context context) {
            super(context, "doulas.csv");
            setSeparatorRegex(";");
        }

        @Override
        protected DolasMarker getObject(String[] split) {
            DolasMarker redeApoioMarker = new DolasMarker();
            redeApoioMarker.latLng = new LatLng(formatDouble(split[1]),formatDouble(split[2]));
            redeApoioMarker.title = split[0];
            redeApoioMarker.endereco = split[3];
            redeApoioMarker.telefone=split[4];
            redeApoioMarker.site = split[5];
            return redeApoioMarker;
        }
    }

    private static double formatDouble(String numberString){
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number = null;
        try {
            number = format.parse(numberString);
            double d = number.doubleValue();
            return d;
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return 0;
    }
}
