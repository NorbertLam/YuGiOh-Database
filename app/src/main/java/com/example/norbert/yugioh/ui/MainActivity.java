package com.example.norbert.yugioh.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.norbert.yugioh.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_menu)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentManager tf = getSupportFragmentManager();
            CardSearchFragment cardSearch = CardSearchFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, cardSearch).commit();
        }
        bindViews();
        setupToolbar();
        setupNavView();
        linkDrawer();
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupNavView() {

        mNavView.inflateMenu(R.menu.drawer);
        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                FragmentManager tf = getSupportFragmentManager();
                CardSearchFragment cardSearch = CardSearchFragment.newInstance();
                RisingFragment rising = RisingFragment.newInstance();
                FallingFragment falling = FallingFragment.newInstance();
                ExpensiveFragment expensive = ExpensiveFragment.newInstance();
                switch (menuItem.getItemId()) {
                    case R.id.drawer_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, cardSearch).commit();
                        break;
                    case R.id.drawer_rising:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, rising).commit();
                        break;
                    case R.id.drawer_falling:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, falling).commit();
                        break;
                    case R.id.drawer_expensive:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, expensive).commit();
                        break;
                }
                mDrawerLayout.closeDrawer(mNavView);
                return false;
            }
        });
    }

    private void linkDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.hello_blank_fragment, R.string.hello_blank_fragment);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
