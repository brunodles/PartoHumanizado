package br.com.PartoHumanizado.viewholder;

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

import br.com.PartoHumanizado.adapter.MenuDrawerAdapter;
import br.com.PartoHumanizado.fragment.MenuDrawerFragment;
import br.com.PartoHumanizado.R;
//import bruno.android.PartoHumanizado.R;

/**
 * Created by bruno on 22/11/14.
 */
public class MenuDrawerViewHolder {

    private MenuDrawerFragment menuDrawerFragment;
    private View mFragmentContainerView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    ActionBarActivity activity;

    public MenuDrawerViewHolder(ActionBarActivity activity) {
        this.activity = activity;
        onCreate();
    }

    private void onCreate() {
        menuDrawerFragment = (MenuDrawerFragment)
                activity.getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mFragmentContainerView = activity.findViewById(R.id.navigation_drawer);
        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = activity.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                activity,                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.open,  /* "open drawer" description for accessibility */
                R.string.close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);


        menuDrawerFragment.getMenuDrawerAdapter().add(new MenuDrawerAdapter.DrawerItem() {
            @Override
            public String getTitle() {
                return "Teste";
            }

            @Override
            public View getMenuView(int position, View convertView, ViewGroup parent) {
                TextView view = new TextView(parent.getContext(), null);
                view.setText(getTitle());
                return view;
            }

            @Override
            public Fragment getFragment() {
                return null;
            }

        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item);
    }

    public boolean onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
            return true;
        }
        return false;
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(mFragmentContainerView);
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }
}
