package bruno.android.kickstart.activity;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bruno.android.PartoHumanizado.R;
import bruno.android.kickstart.adapter.MenuDrawerAdapter;
import bruno.android.kickstart.fragment.MenuDrawerFragment;
import bruno.android.kickstart.viewholder.MenuDrawerViewHolder;

//import android.support.v7.app.ActionBar;

/**
 * Created by bruno on 21/11/14.
 */
public class MainActivity extends ActionBarActivity implements MenuDrawerFragment.MenuListener{

    MenuDrawerViewHolder menuDrawerViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuDrawerViewHolder = new MenuDrawerViewHolder(this);
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

    }
}
