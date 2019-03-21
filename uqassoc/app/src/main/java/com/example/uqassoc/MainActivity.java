package com.example.uqassoc;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.uqassoc.models.Events;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public DrawerLayout buttonLogIn;
    private GridView gridViewEvents;
    EventsAdapter adapter;
    ArrayList<Events> events = new ArrayList<Events>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        //events
        popularGridView();
        //Bouton Log in
        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        View hView =  navigationView.getHeaderView(0);
        ImageButton buttonLogIn = (ImageButton)hView.findViewById(R.id.imageLogIn);

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "Ceci est un toast qui s'affiche à l'écran !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        // 6 - Configure all views

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();

        //Image Logo oconnexion

    }


    private void popularGridView(){
        gridViewEvents = (GridView) findViewById(R.id.gridViewEvents);
         for(int i=0; i < 10; i++){
            events.add(new Events("Event "+i, R.drawable.uqac));
        }
        adapter = new EventsAdapter(this, events);
         gridViewEvents.setAdapter(adapter);

        gridViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, Pop.class ));
                //Toast.makeText(MainActivity.this, "Event" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // 4 - Handle Navigation Item Click
        int id = item.getItemId();

        switch (id){

            case R.id.activity_main_drawer_assoc:
                Toast.makeText(this, "Associations", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_main_drawer_contact:
                Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_main_drawer_home:
                Toast.makeText(this, "Accueil", Toast.LENGTH_SHORT).show();
                break;

            case R.id.activity_main_drawer_liens:
                Toast.makeText(this, "Liens", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_main_drawer_mageuqac:
                Toast.makeText(this, "Mageuqac", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

       // this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar(){
        this.toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout(){
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    // 3 - Configure NavigationView
    private void configureNavigationView(){
        this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //articles
    // 1

}
