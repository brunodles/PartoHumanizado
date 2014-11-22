package br.com.PartoHumanizado.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import br.com.PartoHumanizado.R;
import br.com.PartoHumanizado.fragment.IntervencoesFragment;
import br.com.PartoHumanizado.fragment.MenuDrawerFragment;
import br.com.PartoHumanizado.fragment.ViolenciasFragment;
import br.com.PartoHumanizado.viewholder.MenuDrawerViewHolder;

//import android.support.v7.app.ActionBar;

/**
 * Created by bruno on 21/11/14.
 */
public class MainActivity extends ActionBarActivity implements MenuDrawerFragment.MenuListener {

    MenuDrawerViewHolder menuDrawerViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuDrawerViewHolder = new MenuDrawerViewHolder(this);

        IntervencoesFragment intervencoesFragment = new IntervencoesFragment();
        menuDrawerViewHolder.add(intervencoesFragment);

        ViolenciasFragment violenciasFragment = new ViolenciasFragment();
        menuDrawerViewHolder.add(violenciasFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (menuDrawerViewHolder.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (menuDrawerViewHolder.onBackPressed())
            return;
        super.onBackPressed();
    }

    @Override
    public void onMenuSelect(int position, String title, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        menuDrawerViewHolder.closeDrawer();
    }
}
