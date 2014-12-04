package br.com.PartoHumanizado.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.ParseObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import br.com.PartoHumanizado.model.Defensoria;
import br.com.PartoHumanizado.model.ListMarkerRedeApoio;
import br.com.PartoHumanizado.model.Relato;
import br.com.PartoHumanizado.model.UsuarioPreferences;
import br.com.PartoHumanizado.util.CsvAssetReader;
import bruno.android.utils.gps.GpsClient;
import butterknife.ButterKnife;
import butterknife.InjectView;

import br.com.PartoHumanizado.fragment.base.WebViewFragment;

/**
 * Created by bruno on 22/11/14.
 */
public class DenucieFragment extends BaseFragment {

  //  @InjectView(R.id.spinner_type_violency)
  //  Spinner spinerTypeViolency;
    @InjectView((R.id.et_intervencao))
    EditText editTextIntervencoes;
    @InjectView(R.id.et_nome_medico)
    EditText etNomeMedico;
    @InjectView(R.id.et_nome_vitima)
    EditText etNomeVitima;
    @InjectView(R.id.et_crm_medico)
    EditText etCrmMedico;
    @InjectView(R.id.et_emai_vitima)
    EditText etEmailVitima;
    @InjectView(R.id.button_save_relato)
    Button btSaveRelato;
    @InjectView(R.id.et_defensoria)
    TextView textDefensoria;
    @InjectView(R.id.et_call_denuncia)
    TextView etCallDenuncia;
    @InjectView(R.id.et_mpf)
    TextView textMinisterioPublico;

    private final String TAG =  "PARTO-HUMANIZADO";
    private boolean itens[];
    private String separatorRegex = "/";
    private String numeroTelefone;
    private Defensoria defensoria;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_denuncia,container,false);
        ButterKnife.inject(this, view);

        updateUI();
        return view;
    }

    private void updateUI(){

        editTextIntervencoes.setOnClickListener(onClickIntervention);
        btSaveRelato.setOnClickListener(onclickSave);
        etCallDenuncia.setOnClickListener(onClickListenerCall);
        textDefensoria.setOnClickListener(onClickOpenInfoDefensoria);
        textMinisterioPublico.setOnClickListener(onClickOpenWebView);



    }

    @Override
    public void onResume() {
        super.onResume();
       saveUf();
    }

    private void showFragment() {
        DefensoriaFragment defensoriaFragment = new DefensoriaFragment();
        defensoriaFragment.setDefensoria(getDefensoria());
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, defensoriaFragment)
                .commit();
    }

    private View.OnClickListener onClickIntervention = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            buildDialogState();

        }

    };

    private void buildDialogState(){
        itens= new boolean[getResources().getStringArray(R.array.type_intervention).length];
        final AlertDialog.Builder buildAlert = new AlertDialog.Builder(getActivity());
        buildAlert.setTitle("Marque o tipo de violência sofrida!");
        buildAlert.setPositiveButton("ok",onClickPositive);
        buildAlert.setMultiChoiceItems(R.array.type_intervention, itens, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

            }
        });
                AlertDialog alert = buildAlert.create();
                alert.show();
    }

    public Defensoria getDefensoria() {
        return defensoria;
    }

    public void setDefensoria(Defensoria defensoria) {
        this.defensoria = defensoria;
    }

    private DialogInterface.OnClickListener onClickPositive = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int witch) {
            String array[] = getResources().getStringArray(R.array.type_intervention);
            String selected = "";
            for (int i = 0; i < getResources().getStringArray(R.array.type_intervention).length; i++) {
                if (itens[i]) {
                   Log.d(TAG,"Selected itens "+array[i]);

                    if(array.length>1){
                        selected += array[i]+ " , ";
                    }else{
                        selected = array[i];
                    }

                    itens[i]=false;
                }
            }
            editTextIntervencoes.setText(selected);
        }
    };

    private View.OnClickListener onclickSave = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            saveDenuncia();
        }
    };
    private void saveDenuncia(){
        Relato relato = new Relato();
        relato.setNomeVitima(etNomeVitima.getText().toString());
        relato.saveInBackground();
    }

   private void addDefensoria(){
       List<Defensoria> lista = new ArrayList<Defensoria>();

       lista = Defensoria.readFromAssets(getActivity());
        UsuarioPreferences usuarioPreferences = new UsuarioPreferences(getActivity());

       for(Defensoria defensoria : lista){
           if(defensoria.getUf().equals(usuarioPreferences.getUf())){
              textDefensoria.setText(defensoria.getNome());
              numeroTelefone = defensoria.getTelefone();
              setDefensoria(defensoria);

           }
       }
   }
    private void saveUf(){

        GpsClient gpsClient = new GpsClient(getActivity());
        UsuarioPreferences usuarioPreferences = new UsuarioPreferences(getActivity());
        if(usuarioPreferences.getUf().isEmpty()){
            usuarioPreferences.setUf(gpsClient.getAddress().getAdminArea().substring(0,2).toUpperCase());
            addDefensoria();
        }else{
            addDefensoria();
        }



    }

    private View.OnClickListener onClickOpenWebView =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FormularioDenunciaMpf defensoriaFragment = new FormularioDenunciaMpf();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, defensoriaFragment)
                    .commit();
        }
    };

    private View.OnClickListener onClickOpenInfoDefensoria = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showFragment();
        }
    };
    private View.OnClickListener onClickListenerCall = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            calll();
        }
    };
    private void calll(){

        String[] strings = CsvAssetReader.splitString(numeroTelefone, separatorRegex);
        if(strings[0]!=null){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + strings[0]));
            startActivity(intent);
        }

    }
    @Override
    public String getTitle() {
        return "Denuncie";
    }
}
