package com.example.uqassoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uqassoc.models.Events;

public class Pop extends Activity {

   /* public static String EXTRA_EVENT_ID = "";
    public static String EXTRA_EVENT_IMAGE = "";
    public static String EXTRA_EVENT_TITLE = "";
    public static String EXTRA_EVENT_DESCRIPTION = "";*/
    public String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);
        Bundle extras = getIntent().getExtras();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

//       title =  getIntent().getStringExtra(EXTRA_EVENT_TITLE);
        getWindow().setLayout((int) (width * .9), (int) (height * .9));
        //Log.i("ok", title);
        if (extras.getString("EXTRA_EVENT_TITLE").length() > 15){
            ((TextView) findViewById(R.id.titlePopup)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }else if (extras.getString("EXTRA_EVENT_TITLE").length() > 10){
            ((TextView) findViewById(R.id.titlePopup)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        }else if (extras.getString("EXTRA_EVENT_TITLE").length() > 5){
            ((TextView) findViewById(R.id.titlePopup)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        }
        ((ImageView) findViewById(R.id.imagePopup)).setImageResource(extras.getInt("EXTRA_EVENT_IMAGE"));
        /*int image =extras.getInt("EXTRA_EVENT_IMAGE");
        if(image ==1 ) {
            //admin
            ((ImageView) findViewById(R.id.imagePopup)).setImageResource(extras.getInt("EXTRA_EVENT_IMAGE"));
        }else if(image ==2) {
            //assoc
            ((ImageView) findViewById(R.id.imagePopup)).setImageResource(R.drawable.association);
        }else if(image ==3) {
            //autre
            ((ImageView) findViewById(R.id.imagePopup)).setImageResource(R.drawable.autres);
        }else if(image ==4) {
            //loisir
            ((ImageView) findViewById(R.id.imagePopup)).setImageResource(R.drawable.loisir);
        }else{
            //rien
            ((ImageView) findViewById(R.id.imagePopup)).setImageResource(R.drawable.autres);
        }*/
        ((TextView) findViewById(R.id.titlePopup)).setText(extras.getString("EXTRA_EVENT_TITLE"));
        //Log.i("ok", EXTRA_EVENT_DESCRIPTION);
        ((TextView) findViewById(R.id.descriptionPopup)).setText(extras.getString("EXTRA_EVENT_DESCRIPTION"));
        ((TextView) findViewById(R.id.dateDebutPopup)).setText("Date début :"+extras.getString("EXTRA_EVENT_DATED"));
        ((TextView) findViewById(R.id.dateFinPopup)).setText("Date fin :"+extras.getString("EXTRA_EVENT_DATEF"));

        Button close = (Button) findViewById(R.id.buttonClosePop);

        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Toast.makeText(Pop.this, "Ferme popup", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
