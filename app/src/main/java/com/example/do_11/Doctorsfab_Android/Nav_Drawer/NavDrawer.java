package com.example.do_11.Doctorsfab_Android.Nav_Drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.do_11.Doctorsfab_Android.Exercise_list.ListviewAdapter;
import com.example.do_11.Doctorsfab_Android.Exercise_list.Listviewitem;
import com.example.do_11.Doctorsfab_Android.Wifi_Search.Wifi_MainActivity;
import com.example.do_11.R;

import java.util.ArrayList;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView_exercise;
    public static String tag = "tag >>>>>>>>>>>> ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

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
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView_exercise = findViewById(R.id.listview_exercise);
        ArrayList<Listviewitem> data = new ArrayList<>();

        Listviewitem pushup = new Listviewitem(R.drawable.pushup, "pushup \n 15회. 3세트");
        Listviewitem bentknee_sit_up = new Listviewitem(R.drawable.bentknee_sit_up, "bentknee_sit_up \n 15회. 3세트");
        Listviewitem plank = new Listviewitem(R.drawable.plank, "plank \n 15회. 3세트");
        Listviewitem lunge = new Listviewitem(R.drawable.lunge, "lunge \n 15회. 3세트");
        Listviewitem glute_bridge = new Listviewitem(R.drawable.glute_bridge, "glute_bridge \n 15회. 3세트");

        data.add(pushup);
        data.add(bentknee_sit_up);
        data.add(plank);
        data.add(lunge);
        data.add(glute_bridge);

        ListviewAdapter adapter = new ListviewAdapter(this, R.layout.listview1_item, data);
        listView_exercise.setAdapter(adapter);

        listview_ClickEvent();




    }

    private void listview_ClickEvent() {
        listView_exercise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(tag, String.valueOf(position));




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
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
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

            Intent intent = new Intent(this, Wifi_MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
