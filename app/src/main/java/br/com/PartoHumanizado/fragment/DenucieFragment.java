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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import br.com.PartoHumanizado.model.Defensoria;
import br.com.PartoHumanizado.model.Relato;
import br.com.PartoHumanizado.model.UsuarioPreferences;
import bruno.android.utils.gps.GpsClient;
import butterknife.ButterKnife;
import butterknife.InjectView;

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
    @InjectView(R.id.button_click_call)
    CircularImageView buttonCallDenuncia;
    @InjectView(R.id.button_defensoria)
    CircularImageView buttonDefensoria;
    @InjectView(R.id.button_ministerio)
    CircularImageView buttonMinisterioPublico;
    @InjectView(R.id.et_nome_casa_saude)
    EditText etCasaSaude;
    @InjectView(R.id.more_call)
    RippleView rippeCall;

    private final String TAG =  "PARTO-HUMANIZADO";
    private boolean itens[];
    private String separatorRegex = "/";
    private String numeroTelefone;
    private Defensoria defensoria;
    private android.app.ProgressDialog progressDialog;
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
      //  buttonCallDenuncia.setOnClickListener(onClickListenerCall);
        buttonDefensoria.setOnClickListener(onClickOpenInfoDefensoria);
        buttonMinisterioPublico.setOnClickListener(onClickOpenWebView);
       // rippeCall.setOnClickListener(onClickListenerCall);



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
        openProgress();
        Relato relato = new Relato();
        relato.setNomeVitima(etNomeVitima.getText().toString());
        relato.setCrmMedico(etCrmMedico.getText().toString());
        relato.setEmail(etEmailVitima.getText().toString());
        relato.setIntituicao(etCasaSaude.getText().toString());
        relato.setNomeMedico(etNomeMedico.getText().toString());
        relato.setViolencica(editTextIntervencoes.getText().toString());
        relato.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    closeProgress();
                    resetForm();
                }
            }
        });
    }

    private void resetForm(){
       etNomeVitima.setText("");
       etCrmMedico.setText("");
       etEmailVitima.setText("");
       etCasaSaude.setText("");
       etNomeMedico.setText("");
       editTextIntervencoes.setText("");
    }
   private void addDefensoria(){
       List<Defensoria> lista = new ArrayList<Defensoria>();

       lista = Defensoria.readFromAssets(getActivity());
        UsuarioPreferences usuarioPreferences = new UsuarioPreferences(getActivity());

       for(Defensoria defensoria : lista){
           if(defensoria.getUf().equals(usuarioPreferences.getUf())){
              //textDefensoria.setText(defensoria.getNome());
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


            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "180"));
            startActivity(intent);


    }
    @Override
    public String getTitle() {
        return "Denuncie";
    }


    private void openProgress(){
        progressDialog = new android.app.ProgressDialog(getActivity());
        progressDialog.setMessage("Salvando seu dados, aguarde...");
        progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    private void closeProgress(){
        if(progressDialog!=null){
            progressDialog.hide();
        }
    }
}
