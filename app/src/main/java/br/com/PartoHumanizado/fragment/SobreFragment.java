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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.PartoHumanizado.BuildConfig;
import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by bruno on 22/11/14.
 */
public class SobreFragment extends BaseFragment {

    @InjectView(R.id.version)
    TextView version;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobre, null);
        ButterKnife.inject(this, view);
        version.setText(String.format("Versão %s", BuildConfig.VERSION_NAME));
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getTitle() {
        return "Sobre";
    }
}
