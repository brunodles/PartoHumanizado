/*
 * Copyright 2014 de [PARTO HUMANIZADO/SERGIO HOLANDA,MARCELA OLIVEIRA E BRUNO LIMA] Este arquivo é parte do programa [PARTO HUMANIZADO]. O [PARTO
 * HUMANIZADO]é um software livre; você pode redistribuí-lo e/ou modificá-lo dentro dos termos da [GNU General Public License OU GNU Affero General Public
 * License] como publicada pela Fundação do Software Livre (FSF); na versão 3 da Licença. Este programa é distribuído na esperança que possa ser útil, mas
 * SEM NENHUMA GARANTIA;
 * sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a licença para maiores detalhes. Você deve ter recebido uma
 * cópia da [GNU General Public License OU GNU Affero General Public License], sob o título "LICENCA.txt", junto com este programa, ,
 * se não, acesse http://www.gnu.org/licenses/
 */

package br.com.PartoHumanizado.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.adapter.MenuDrawerAdapter;
import br.com.PartoHumanizado.fragment.DenucieFragment;
import br.com.PartoHumanizado.fragment.InformacoesFragment;
import br.com.PartoHumanizado.fragment.MapaDasDoulasFragment;
import br.com.PartoHumanizado.fragment.MapaDeRedeDeApoioFragment;
import br.com.PartoHumanizado.fragment.MenuDrawerFragment;
import br.com.PartoHumanizado.fragment.PlanoDePartoFragment;
import br.com.PartoHumanizado.fragment.SobreFragment;
import br.com.PartoHumanizado.fragment.ViolenciasFragment;
import br.com.PartoHumanizado.viewholder.MenuDrawerViewHolder;

//import android.support.v7.app.ActionBar;

/**
 * Created by bruno on 21/11/14.
 */
public class MainActivity extends ActionBarActivity implements MenuDrawerFragment.MenuListener {

    MenuDrawerViewHolder menuDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuDrawer = new MenuDrawerViewHolder(this);

        InformacoesFragment informacoesFragment = new InformacoesFragment();
        showFragment(informacoesFragment);
        menuDrawer.add(informacoesFragment);

//        IntervencoesFragment intervencoesFragment = new IntervencoesFragment();
//        menuDrawer.add(intervencoesFragment);
//
        ViolenciasFragment violenciasFragment = new ViolenciasFragment();
        menuDrawer.add(violenciasFragment);

//        IntervencoesViolenciasFragment intervencoesViolenciasFragment = new IntervencoesViolenciasFragment();
//        menuDrawer.add(intervencoesViolenciasFragment);

        PlanoDePartoFragment planoDePartoFragment = new PlanoDePartoFragment();
        menuDrawer.add(planoDePartoFragment);

        DenucieFragment denucieFragment = new DenucieFragment();
        menuDrawer.add(denucieFragment);

        MapaDasDoulasFragment mapaDasDoulasFragment = new MapaDasDoulasFragment();
        menuDrawer.add(mapaDasDoulasFragment);

        MapaDeRedeDeApoioFragment mapaDeRedeDeApoioFragment = new MapaDeRedeDeApoioFragment();
        menuDrawer.add(mapaDeRedeDeApoioFragment);

        SobreFragment sobreFragment = new SobreFragment();
        menuDrawer.add(sobreFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (menuDrawer.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (menuDrawer.onBackPressed())
            return;
        super.onBackPressed();
    }

    @Override
    public void onMenuSelect(int position, String title, Fragment fragment) {
        showFragment(fragment);
        menuDrawer.closeDrawer();
    }

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        if (fragment instanceof MenuDrawerAdapter.DrawerItem)
            setTitle(((MenuDrawerAdapter.DrawerItem) fragment).getTitle());
    }
}
