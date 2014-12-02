package br.com.PartoHumanizado.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.ParseObject;

import java.lang.reflect.Array;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import br.com.PartoHumanizado.model.Relato;
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

    private final String TAG =  "PARTO-HUMANIZADO";
    private boolean itens[];
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ranking_denuncia,container,false);
        ButterKnife.inject(this, view);
        updateUI();
        return view;
    }

    private void updateUI(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.type_violency, R.layout.spinner_custom);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinerTypeViolency.setAdapter(adapter);
        editTextIntervencoes.setOnClickListener(onClickIntervention);
        btSaveRelato.setOnClickListener(onclickSave);
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

    @Override
    public String getTitle() {
        return "Denuncie";
    }
}
