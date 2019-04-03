package com.example.uqassoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Pop extends Activity {

    public static String EXTRA_EVENT_ID = "";
    public static String EXTRA_EVENT_IMAGE = "";
    public static String EXTRA_EVENT_TITLE = "";
    public static String EXTRA_EVENT_DESCRIPTION = "";
    public String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

//       title =  getIntent().getStringExtra(EXTRA_EVENT_TITLE);
        getWindow().setLayout((int) (width * .9), (int) (height * .9));
        //Log.i("ok", title);

        ((TextView) findViewById(R.id.titlePopup)).setText(EXTRA_EVENT_TITLE);
        //Log.i("ok", EXTRA_EVENT_DESCRIPTION);
        ((TextView) findViewById(R.id.descriptionPopup)).setText(EXTRA_EVENT_DESCRIPTION);
        ((ImageView) findViewById(R.id.imagePopup)).setImageResource(R.drawable.uqac);
        Button close = (Button) findViewById(R.id.buttonClosePop);

        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Pop.this, "Ferme popup", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
