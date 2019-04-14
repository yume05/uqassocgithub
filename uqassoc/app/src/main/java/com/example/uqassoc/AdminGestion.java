package com.example.uqassoc;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.uqassoc.models.Events;
import com.example.uqassoc.models.Users;

import java.util.ArrayList;

public class AdminGestion extends MainActivity {
    public Button buttonGestion;
    public ImageButton buttonLogIn;
    public ImageButton buttonLogOut;
    public Button buttonAddEvent;
    public Button buttonEvent;
    public Button buttonAssoc;
    public Button buttonUser;
    public ScrollView linearEvent;
    public ScrollView linearUser;
    public ScrollView linearAssoc;
    public ListView listUsers;
    UsersAdapter adapter;
    ArrayList<Users> usersList;

    public ListView listEvents;
    public ArrayList<Events> eventsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        super.onCreateDrawer();
        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        linearEvent = (ScrollView) findViewById(R.id.linearEvent);
        linearAssoc = (ScrollView) findViewById(R.id.linearAssoc);
        linearUser = (ScrollView) findViewById(R.id.linearUser);
        buttonEvent = (Button) findViewById(R.id.buttonEvents);
        buttonUser = (Button) findViewById(R.id.buttonUsers);
        buttonAssoc = (Button) findViewById(R.id.buttonAssoc);
        buttonAddEvent = (Button) findViewById(R.id.buttonAddEvent);


        listUsers = (ListView) findViewById(R.id.listUsers);
        listEvents = (ListView) findViewById(R.id.listEvents);

        View hView =  navigationView.getHeaderView(0);
        buttonLogIn = (ImageButton)hView.findViewById(R.id.imageLogIn);
        buttonLogOut = (ImageButton)hView.findViewById(R.id.imageLogOut);

        //super.gestionLogIn();
        buttonLogIn.setVisibility(View.INVISIBLE);
        buttonLogOut.setVisibility(View.VISIBLE);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });

        linearEvent.setVisibility(View.GONE);
        linearAssoc.setVisibility(View.GONE);
        linearUser.setVisibility(View.GONE);

        initialiseUsers();

        buttonEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearAssoc.setVisibility(View.GONE);
                linearUser.setVisibility(View.GONE);
                linearEvent.setVisibility(View.VISIBLE);

                //DatabaseAccess databaseAccess = DatabaseAccess.getInstance(AdminGestion.this);
               // databaseAccess.open();
                ////eventsList = databaseAccess.getEvents();
                //databaseAccess.close();
                //adapter = new EventsAdapter(AdminGestion.this, eventsList);
                //Log.d("User", usersList.toString());
                //listUsers.setAdapter(adapter);
            }
        });
        buttonAssoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearEvent.setVisibility(View.GONE);
                linearUser.setVisibility(View.GONE);
                linearAssoc.setVisibility(View.VISIBLE);
            }
        });
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearAssoc.setVisibility(View.GONE);
                linearEvent.setVisibility(View.GONE);
                linearUser.setVisibility(View.VISIBLE);


            }
        });

    }

    public void initialiseUsers(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(AdminGestion.this);
        databaseAccess.open();
        usersList = databaseAccess.getUsers();
        databaseAccess.close();
        adapter = new UsersAdapter(AdminGestion.this, usersList);
        Log.d("User", usersList.toString());
        listUsers.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        usersList.clear();
        initialiseUsers();
        // ArrayList<Events> eventsList = new ArrayList<Events>();

    }
}
