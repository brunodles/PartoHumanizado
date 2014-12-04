package br.com.PartoHumanizado.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import br.com.PartoHumanizado.model.Defensoria;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sergio holanda on 03-Dec-14.
 */
public class DefensoriaFragment extends BaseFragment {

    private Defensoria defensoria;



    @InjectView(R.id.et_nome_defensoria)
    TextView nomeDefensoria;
    @InjectView(R.id.et_nome_defensor_geral)
    TextView nomeDefensor;
    @InjectView(R.id.et_endereco_defensoria)
    TextView enderecoDefensoria;
    @InjectView(R.id.et_cep_defensoria)
    TextView cepDefensoria;
    @InjectView(R.id.et_email_defensoria)
    TextView emailDefensoria;
    @InjectView(R.id.et_site_defensoria)
    TextView siteDefensoria;
    @InjectView(R.id.et_telefone_defensoria)
    TextView telefoneDefensoria;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_defensoria,null);
        ButterKnife.inject(this,view);
      //  inflateView();
        return view;
    }

    private void inflateView(){

    }

    @Override
    public void onResume() {
        super.onResume();
        updtaeView();
    }

    public void setDefensoria(Defensoria defensoria){
        this.defensoria = defensoria;
    }

    private void updtaeView(){

        nomeDefensoria.setText(this.defensoria.getNome());
        nomeDefensor.setText(this.defensoria.getDefensorGeral());
        telefoneDefensoria.setText(this.defensoria.getTelefone());
        enderecoDefensoria.setText(this.defensoria.getEndereco());
        cepDefensoria.setText(this.defensoria.getCep());
        emailDefensoria.setText(this.defensoria.getEmail());
        siteDefensoria.setText(this.defensoria.getSite());

    }
    @Override
    public String getTitle() {
        return "Defensoria";
    }
}
