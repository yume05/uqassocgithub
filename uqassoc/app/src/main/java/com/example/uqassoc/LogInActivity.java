package com.example.uqassoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends MainActivity {
    public TextView buttonHome;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        //Open CONNEXION
        //Bouton Log in
//        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
//        View hView =  navigationView.getHeaderView(0);
//        buttonLogIn = (ImageButton)hView.findViewById(R.id.imageLogIn);
//
//        buttonLogIn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(LogInActivity.this, LogInActivity.class);
//                startActivity(intent);
//            }
//        });
////
//        //Open HOME
        super.onCreateDrawer();

    }


}
