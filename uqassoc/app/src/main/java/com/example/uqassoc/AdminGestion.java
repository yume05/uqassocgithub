package com.example.uqassoc;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uqassoc.models.Assoc;
import com.example.uqassoc.models.Events;
import com.example.uqassoc.models.Users;

import java.util.ArrayList;
import java.util.Calendar;

public class AdminGestion extends MainActivity {
    public Button buttonGestion;
    public ImageButton buttonLogIn;
    public ImageButton buttonLogOut;
    public Button buttonAddEvent;
    public Button buttonEvent;
    public Button buttonAssoc;
    public Button buttonUser;
    public LinearLayout linearEvent;
    public LinearLayout linearUser;
    public LinearLayout linearAssoc;
    public ListView listUsers;
    UsersAdapter adapter;
    ArrayList<Users> usersList;

    String checkedCategorie = "1";
    String idEvent;

    EditText editTitleEvent;
    EditText editDescriptionEvent;
    EditText editDateDebut;
    EditText editDateFin;

    DatePickerDialog dateDebutPickerDialog;
    DatePickerDialog dateFinPickerDialog;
    EventsAdminAdapter adapter1;
    public ListView listEvents;
    public ArrayList<Events> eventsList;

    public ListView listAssoc;
    AssocAdapter adapter2;
    ArrayList<Assoc> assocList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        super.onCreateDrawer();

        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        View hView =  navigationView.getHeaderView(0);


        buttonLogIn = (ImageButton)hView.findViewById(R.id.imageLogIn);
        buttonLogOut = (ImageButton)hView.findViewById(R.id.imageLogOut);
        buttonLogIn.setVisibility(View.INVISIBLE);
        buttonLogOut.setVisibility(View.VISIBLE);

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });

        listUsers = (ListView) findViewById(R.id.listUsers);
        initialiseUsers();
        listEvents= (ListView) findViewById(R.id.listEvents);
        initialiseEvents();
        listAssoc= (ListView) findViewById(R.id.listAssoc);
        initialiseAssoc();



        /**
         * Ajout d un evenement
         */
        editTitleEvent = (EditText) findViewById(R.id.editTitleEvent);
        editDescriptionEvent = (EditText) findViewById(R.id.editDescriptionEvent);
        editDateDebut = (EditText) findViewById(R.id.editDateDebut);
        editDateFin = (EditText) findViewById(R.id.editDateFin);

        editDateDebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                dateDebutPickerDialog = new DatePickerDialog(AdminGestion.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                editDateDebut.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                dateDebutPickerDialog.show();
            }
        });

        editDateFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                dateFinPickerDialog = new DatePickerDialog(AdminGestion.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                editDateFin.setText(dayOfMonth + "/" + monthOfYear + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                dateFinPickerDialog.show();
            }
        });




        buttonAddEvent = (Button) findViewById(R.id.buttonAddEvent);
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("addEvent", "On a bien cliquer");
                // display the values by using a toast
                String titleEvent = editTitleEvent.getText().toString();
                String descriptionEvent = editDescriptionEvent.getText().toString();
                String dateDebut = editDateDebut.getText().toString();
                String dateFin = editDateFin.getText().toString();

                if(titleEvent.length() < 2 || titleEvent == "" || titleEvent == "null"){
                    Toast.makeText(AdminGestion.this, "Le titre est obligatoire", Toast.LENGTH_SHORT).show();
                }else if(dateDebut == "" && dateFin != "") {
                    Toast.makeText(AdminGestion.this, "La date de début doit etre entrée", Toast.LENGTH_SHORT).show();
                }else{
                    DatabaseAccess databaseAccess = DatabaseAccess.getInstance(AdminGestion.this);
                    databaseAccess.open();
                    Log.i("categ checked", checkedCategorie);
                    Boolean ok = databaseAccess.insertEvents(titleEvent, descriptionEvent, dateDebut, dateFin, checkedCategorie);
                    databaseAccess.close();
                   // Log.i("add date", dateDebut);
                   // Log.i("add date", dateFin);
                    if (ok){
                        Toast.makeText(AdminGestion.this, "Evenement ajouté", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AdminGestion.this, "Erreur dans l'ajout de l'évenement", Toast.LENGTH_SHORT).show();
                    }
                    eventsList.clear();
                    initialiseEvents();
                    editTitleEvent.setText("");
                    editDescriptionEvent.setText("");
                    editDateDebut.setText("");
                    editDateFin.setText("");
                }
            }
        });



        /*
        Changement de page
        Evenement
         */

        linearEvent = (LinearLayout) findViewById(R.id.linearEvent);
        linearEvent.setVisibility(View.GONE);
        listEvents.setVisibility(View.GONE);
        listUsers.setVisibility(View.GONE);

        buttonEvent = (Button) findViewById(R.id.buttonEvents);
        buttonEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearAssoc.setVisibility(View.GONE);
                linearUser.setVisibility(View.GONE);
                linearEvent.setVisibility(View.VISIBLE);
                listEvents.setVisibility(View.VISIBLE);
                listUsers.setVisibility(View.GONE);
                //Log.d("event", "On a bien cliquer");
                eventsList.clear();
                initialiseEvents();
            }
        });


        /*
        Changement de page
        Association
         */
        linearAssoc = (LinearLayout) findViewById(R.id.linearAssoc);
        linearAssoc.setVisibility(View.GONE);

        buttonAssoc = (Button) findViewById(R.id.buttonAssoc);
        buttonAssoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearEvent.setVisibility(View.GONE);
                linearUser.setVisibility(View.GONE);
                linearAssoc.setVisibility(View.VISIBLE);
                listEvents.setVisibility(View.GONE);
                listUsers.setVisibility(View.GONE);
                assocList.clear();
                initialiseAssoc();
            }
        });

        /*
        Changement de page
        User
         */

        linearUser = (LinearLayout) findViewById(R.id.linearUser);
        linearUser.setVisibility(View.GONE);
        buttonUser = (Button) findViewById(R.id.buttonUsers);
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearAssoc.setVisibility(View.GONE);
                linearEvent.setVisibility(View.GONE);
                linearUser.setVisibility(View.VISIBLE);
                listEvents.setVisibility(View.GONE);
                listUsers.setVisibility(View.VISIBLE);


            }
        });

    }

    /**
     * Initailisement de la liste des Users
     */
    public void initialiseUsers(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(AdminGestion.this);
        databaseAccess.open();
        usersList = databaseAccess.getUsers();
        databaseAccess.close();
        adapter = new UsersAdapter(AdminGestion.this, usersList);
      //  Log.d("User", usersList.toString());
        listUsers.setAdapter(adapter);
    }

    /**
     * Initailisement de la liste des Event
     */
    public void initialiseEvents(){
        listEvents = (ListView) findViewById(R.id.listEvents);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(AdminGestion.this);
        databaseAccess.open();
        eventsList = databaseAccess.getEvents();
        databaseAccess.close();
        //Log.d("ok", eventsList.toString());
        adapter1 = new EventsAdminAdapter(AdminGestion.this, eventsList);
      //  Log.d("Events", eventsList.toString());
        listEvents.setAdapter(adapter1);
    }

    /**
     * Initailisement de la liste des Assoc
     */
    public void initialiseAssoc(){
        listAssoc = (ListView) findViewById(R.id.listAssoc);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(AdminGestion.this);
        databaseAccess.open();
        assocList = databaseAccess.getAssoc();
        databaseAccess.close();
        //Log.d("ok", eventsList.toString());
        adapter2 = new AssocAdapter(AdminGestion.this, assocList);
        //  Log.d("Events", eventsList.toString());
        listAssoc.setAdapter(adapter2);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        usersList.clear();
        eventsList.clear();
        assocList.clear();
        initialiseUsers();
        initialiseEvents();
        initialiseAssoc();
        // ArrayList<Events> eventsList = new ArrayList<Events>();

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        Log.i("CheckedCategorie", String.valueOf(view.getId()));
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonAdmin:
                if (checked)
                    // Pirates are the best
                    checkedCategorie = "1";
                    break;
            case R.id.radioButtonAssoc:
                if (checked)
                    // Ninjas rule
                    checkedCategorie = "2";
                    break;
            case R.id.radioButtonAutre:
                if (checked)
                    // Ninjas rule
                    checkedCategorie = "3";
                    break;
            case R.id.radioButtonLoisir:
                if (checked)
                    // Ninjas rule
                    checkedCategorie = "4";
                    break;
        }
    }
}
