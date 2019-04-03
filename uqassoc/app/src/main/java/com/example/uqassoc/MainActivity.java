package com.example.uqassoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;


import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uqassoc.models.Events;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public ImageButton buttonLogIn;
    public TextView buttonHome;
    public TextView textTitle;
    private GridView gridViewEvents = null;
    EventsAdapter adapter;
    ArrayList<Events> eventsList = new ArrayList<Events>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        //events
        popularGridView();

        onCreateDrawer();


//        this.listView = (ListView) findViewById(R.id.listView);
//        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
//        databaseAccess.open();
//        eventsList = databaseAccess.getEvents();
//        databaseAccess.close();
//
//       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventsList);
//        adapter = new EventsAdapter(this, eventsList);
//        this.listView.setAdapter(adapter);
        // 6 - Configure all views


//
        //Open CONNEXION
        //Bouton Log in
        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        View hView =  navigationView.getHeaderView(0);
        buttonLogIn = (ImageButton)hView.findViewById(R.id.imageLogIn);

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });






    }
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        this.gridViewEvents.setAdapter(null);
        popularGridView();

        //popularGridView();
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i("DIM", "onSaveInstanceState");
        outState.putString("8INF257", "Yo, voici mon save");
        outState.putParcelableArrayList("eventList", eventsList);

    }

    public void onCreateDrawer(){
        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();


    }

    private void popularGridView(){
       // this.gridViewEvents.setAdapter(null);
        Log.i("ETATGRID", "1");
       this.gridViewEvents = (GridView) findViewById(R.id.gridViewEvents);
       // this.listView = (ListView) findViewById(R.id.listView);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        eventsList = databaseAccess.getEvents();
        databaseAccess.close();

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, eventsList);
        adapter = new EventsAdapter(this, eventsList);

        this.gridViewEvents.setAdapter(adapter);


//        gridViewEvents = (GridView) findViewById(R.id.gridViewEvents);
//         for(int i=0; i < 10; i++){
//            eventsList.add(new Events("Event "+i, R.drawable.uqac));
//        }
//        adapter = new EventsAdapter(this, eventsList);
//         gridViewEvents.setAdapter(adapter);

        gridViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String name = eventsList.get;
                String idEvent = eventsList.get(position).id;
                String image = String.valueOf(eventsList.get(position).image);
                String title = eventsList.get(position).title;
                String description = eventsList.get(position).description;
                //Log.i("ok", ok);
//                Pop.(MainActivity.this, idEvent, image, title, description);

//                textTitle = (TextView)findViewById(R.class.);
//                textTitle.setText(title);
                startActivity(new Intent(MainActivity.this, Pop.class));
                Pop.EXTRA_EVENT_TITLE = title;
                Pop.EXTRA_EVENT_IMAGE = image;
                Pop.EXTRA_EVENT_ID = idEvent;
                Pop.EXTRA_EVENT_DESCRIPTION = description;

                //Toast.makeText(MainActivity.this, "Event" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //le bouton qui permet d ouvrir ou pas la le drawer
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

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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
