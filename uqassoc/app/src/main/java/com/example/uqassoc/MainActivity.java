package com.example.uqassoc;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.facebook.AccessToken;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //FOR DESIGN

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public ImageButton buttonLogIn;

    public ImageButton buttonLogOut;
    public Button buttonGestion;
    private GridView gridViewEvents = null;
    EventsAdapter adapter;
    ArrayList<Events> eventsList = new ArrayList<Events>();

    SharedPreferences pref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        //events
        popularGridView();

        onCreateDrawer();
       // gestionLogIn();
        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        View hView =  navigationView.getHeaderView(0);

        buttonLogIn = (ImageButton)hView.findViewById(R.id.imageLogIn);
        buttonLogOut = (ImageButton)hView.findViewById(R.id.imageLogOut);
        buttonGestion = (Button)findViewById(R.id.buttonGestion);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        if(pref.contains("username") && pref.contains("password")){

            String result = pref.getString("username", "");
            Log.i("ok", result);
            if (result.equals("root")) {
                Log.i("ok", "Ok cest egal");
                buttonGestion.setVisibility(View.VISIBLE);
                buttonGestion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, AdminGestion.class);
                        startActivity(intent1);

                    }
                });
            } else {
                Log.i("ok", "Ok cest pas egal");
                buttonGestion.setVisibility(View.INVISIBLE);
            }

            buttonLogIn.setVisibility(View.INVISIBLE);
            buttonLogOut.setVisibility(View.VISIBLE);
            buttonLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogOut();
                }
            });
        }else{
            buttonGestion.setVisibility(View.INVISIBLE);
            buttonLogOut.setVisibility(View.INVISIBLE);
            buttonLogIn.setVisibility(View.VISIBLE);
            buttonLogIn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            });
        }
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


    }


    public void gestionLogIn(){
        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        View hView =  navigationView.getHeaderView(0);

        buttonLogIn = (ImageButton)hView.findViewById(R.id.imageLogIn);
        buttonLogOut = (ImageButton)hView.findViewById(R.id.imageLogOut);
        buttonGestion = (Button)findViewById(R.id.buttonGestion);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);

        if(pref.contains("username") && pref.contains("password")){

            String result = pref.getString("username", "");
            Log.i("ok", result);
            if (result.equals("root")) {
                Log.i("ok", "Ok cest egal");
                buttonGestion.setVisibility(View.VISIBLE);
                buttonGestion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, AdminGestion.class);
                        startActivity(intent1);

                    }
                });
            } else {
                Log.i("ok", "Ok cest pas egal");
                buttonGestion.setVisibility(View.INVISIBLE);
            }

            buttonLogIn.setVisibility(View.INVISIBLE);
            buttonLogOut.setVisibility(View.VISIBLE);
            buttonLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogOut();
                }
            });
        }else{
            buttonGestion.setVisibility(View.INVISIBLE);
            buttonLogOut.setVisibility(View.INVISIBLE);
            buttonLogIn.setVisibility(View.VISIBLE);
            buttonLogIn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    /**
     * On save les datas que l ont veut
     * @param outState
     */
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i("DIM", "onSaveInstanceState");
        outState.putString("8INF257", "Yo, voici mon save");
       // outState.putExtra("eventList", eventsList);
    }

    /**
     * On recupere la sauvegarde
     * @param savedInstanceState
     */
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
       // this.gridViewEvents.setAdapter(null);

    }

    /**
     * On creer le Drawer
     */
    public void onCreateDrawer(){
        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();


    }

    /**
     * On creer le Grid et on ajoute les events
     */
    private void popularGridView(){

        eventsList.clear();
    adapter = null;
    gridViewEvents.data
      // this.gridViewEvents.setAdapter(null);
        this.gridViewEvents = (GridView) findViewById(R.id.gridViewEvents);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        eventsList = databaseAccess.getEvents();
        databaseAccess.close();
        adapter = new EventsAdapter(this, eventsList);


        this.gridViewEvents.setAdapter(adapter);

        gridViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String name = eventsList.get;
                String idEvent = eventsList.get(position).id;
                String image = String.valueOf(eventsList.get(position).image);
                String title = eventsList.get(position).title;
                String description = eventsList.get(position).description;

                startActivity(new Intent(MainActivity.this, Pop.class));
                Pop.EXTRA_EVENT_TITLE = title;
                Pop.EXTRA_EVENT_IMAGE = image;
                Pop.EXTRA_EVENT_ID = idEvent;
                Pop.EXTRA_EVENT_DESCRIPTION = description;

            }
        });


    }

    /**
     * Deconnexion
     */
    public void LogOut(){
         SharedPreferences.Editor editor = pref.edit();
         editor.clear();
         editor.commit();
         startActivity(new Intent(MainActivity.this, MainActivity.class));

    }

    /**
     * Bouton qui ouvre le drawer
     */
    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//    public boolean onPrepareOptionsMenu(Menu menu)
//    {
//        MenuItem admin = menu.findItem(R.id.activity_main_drawer_admin);
//        pref = getSharedPreferences("user_details",MODE_PRIVATE);
//
//        if(pref.contains("username") && pref.contains("password")) {
//            // menuInflater.inflate(R.menu.activity_main_menu_drawer,  );
//            if (pref.getString("user_details", "username") == "admin") {
//                admin.setVisible(true);
//            } else {
//                admin.setVisible(false);
//            }
//        }
//        return true;
//    }

    /**
     * Menu de navigation du drawer
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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

//            case R.id.activity_main_drawer_admin:
//                Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show();
//
//                Intent intent1 = new Intent(MainActivity.this, AdminGestion.class);
//                startActivity(intent1);
//                break;
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


}
