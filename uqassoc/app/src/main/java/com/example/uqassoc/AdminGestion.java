package com.example.uqassoc;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminGestion extends MainActivity {
    public Button buttonGestion;
    public ImageButton buttonLogIn;

    public ImageButton buttonLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        super.onCreateDrawer();
        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
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
    }
}
