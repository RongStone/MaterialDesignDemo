package com.example.rs.materialdesigndemo;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import ui.CardContentFragment;
import ui.ListContentFragment;
import ui.PagerAdapter;
import ui.TileContentFragment;

import static com.example.rs.materialdesigndemo.R.id.action_bar_container;
import static com.example.rs.materialdesigndemo.R.id.drawer;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_main_b);


        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(drawer);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("MaterialDesign");
        toolbar.setSubtitle("subtitle");
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.v(TAG, "onNavigationItemSelected " + item.getTitle());
                return false;
            }
        });

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//                toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawerLayout.setDrawerListener(toggle);
//        toggle.syncState();

        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }


        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        Log.v(TAG, "onNavigationItemSelected " + menuItem.getTitle());
                        // TODO: handle navigation
                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        if(menuItem.getItemId() == R.id.one){

                        } else if(menuItem.getItemId() == R.id.two){

                        } else if (menuItem.getItemId() == R.id.three){

                        }
                        return true;
                    }
                });
        Log.v(TAG,  "" + navigationView.getMenu().getItem(0).getTitle());

        navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,  "headview clicked");
            }
        });


        //锁定侧边栏
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN, GravityCompat.START);

        //隐藏侧边栏某一项
        MenuItem menuItem = navigationView.getMenu().findItem(R.id.sub2);
        menuItem.setVisible(false);    // true 为显示，false 为隐藏

        //如果PagerAdapter中设置了title此处无效；
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Tab 1"));
        tabs.addTab(tabs.newTab().setText("Tab 2"));
        tabs.addTab(tabs.newTab().setText("Tab 3"));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setPagerView(viewPager);

        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello Snackbar!", Snackbar.LENGTH_LONG)
                        .setAction("click snackbar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.v(TAG, "onSnackbarClick ");
                                Toast.makeText(MainActivity.this, "ss", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    private void setPagerView(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListContentFragment(), "List");
        adapter.addFragment(new TileContentFragment(), "Tile");
        adapter.addFragment(new CardContentFragment(), "Card");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.v(TAG, "onOptionsItemSelected action_settings " + item.getTitle());
            return true;
        } else if (id == android.R.id.home) {
            Log.v(TAG, "onOptionsItemSelected home " + item.getTitle());
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
