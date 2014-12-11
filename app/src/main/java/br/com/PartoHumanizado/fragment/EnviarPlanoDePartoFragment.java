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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by bruno on 06/12/14.
 */
public class EnviarPlanoDePartoFragment extends BaseFragment {

    @InjectView(R.id.texto)
    TextView texto;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enviar_plano_de_parto, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateText();
    }

    private void updateText() {

        StringBuilder builder = new StringBuilder();
        builder.append(getText("Trabalho de Parto", PlanoDePartoFragment.PREFERENCES_FILE_TRABALHO_DE_PARTO, R.array.trabalhoDeParto))
                .append(getText("Durante o Parto", PlanoDePartoFragment.PREFERENCES_FILE_PARTO, R.array.duranteParto))
                .append(getText("Pós-Parto", PlanoDePartoFragment.PREFERENCES_FILE_POS_PARTO, R.array.posParto))
                .append(getText("Cuidados com o Bebê", PlanoDePartoFragment.PREFERENCES_FILE_CUIDADOS_COM_BEBE, R.array.cuidadosComBebe))
                .append(getText("Caso de Cesárea", PlanoDePartoFragment.PREFERENCES_FILE_CASO_CESAREA, R.array.casoCesarea))
        ;

        texto.setText(Html.fromHtml(builder.toString()));
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        updateText();
    }

    private StringBuilder getText(String tittle, String preferencesFileName, @ArrayRes int stringArrayId) {
        FragmentActivity activity = getActivity();

        String[] stringArray = activity.getResources().getStringArray(stringArrayId);
        SharedPreferences preferences = activity.getSharedPreferences(preferencesFileName, Context.MODE_PRIVATE);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("<br/><b>")
                .append(tittle)
                .append("</b><br/>");

        for (int i = 0; i < stringArray.length; i++)
            if (preferences.getBoolean(String.valueOf(i), false))
                stringBuilder.append(stringArray[i])
                        .append("<br/>");

        return stringBuilder;
    }

    @Override
    public String getTitle() {
        return "Resultado";
    }

    @Override
    public void setUserVisibleHint(boolean visible) {
        super.setUserVisibleHint(visible);
        if (visible && isResumed()) {
            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume
            onResume();
        }
    }

    @OnClick(R.id.enviar)
    public void enviar() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
//        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, "");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Plano de Parto");
        intent.putExtra(Intent.EXTRA_TEXT, texto.getText().toString());

        startActivity(Intent.createChooser(intent, "Enviar email"));
//        startActivity(intent);
    }
}
