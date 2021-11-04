package com.dawcyn.africdecoration;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dawcyn.common.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DesignerDashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lv;
    private ArrayList<HashMap<String,String>> list;
    DatabaseHelper db = new DatabaseHelper(this);
    TextView tvEmpty;

    SuperClass callClass = new SuperClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //new SuperClass().setMessage(DesignerDashboard.this, "Message from Common Module to \n Designers/Tailors/Seamstresses!");

        tvEmpty = (TextView) findViewById(R.id.tvEmpty);
        tvEmpty.setVisibility(View.INVISIBLE);
        lv = (ListView) findViewById(R.id.list);
        CollectionAdapter adapter = new CollectionAdapter(this, callClass.design_name, callClass.designs);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Intent call = new Intent(DesignerDashboard.this, ProductLargeImage.class);
               //call.putExtra("image", callClass.designs[position]);
                //call.putExtra("name", callClass.design_name[position]);
               // startActivity(call);
            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent call = new Intent(DesignerDashboard.this, ProductLargeImage.class);
                call.putExtra("image", callClass.designs[position]);
               call.putExtra("name", callClass.design_name[position]);
                startActivity(call);
                //new SuperClass().setMessage(DesignerDashboard.this, callClass.design_name[position]);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClickTailorExplorer(View view) {
        startActivity(new Intent(DesignerDashboard.this, Explorer.class));
    }

    public void onClickUpload(View view) {
        startActivity(new Intent(DesignerDashboard.this, UploadProducts.class));
    }

    public void onClickTailorStore(View view) {
       Intent call = new Intent(DesignerDashboard.this, Shop.class);
        call.putExtra("user", "Designer");
        startActivity(call);
        //new SuperClass().setMessage(DesignerDashboard.this, "Testing Shop!");
       // startActivity(new Intent(DesignerDashboard.this, Shop.class));
    }

    public void onClickTailorChat(View view) {
        startActivity(new Intent(DesignerDashboard.this, Chat.class));
    }

    public void onClickTailorProfile(View view) {
        startActivity(new Intent(DesignerDashboard.this, Profile.class));
    }
}
