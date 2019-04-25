package com.example.uqassoc;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.facebook.CallbackManager;

import com.example.uqassoc.models.Assoc;
import com.example.uqassoc.models.Events;
import com.example.uqassoc.models.Users;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    EventsAdapter adapter;

    private Context context;
    ArrayList<Events> eventList = new ArrayList<Events>();
    ArrayList<Users> userList = new ArrayList<Users>();
    ArrayList<Assoc> assocList = new ArrayList<Assoc>();


    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        context = context;
        this.openHelper = new DatabaseOpenHelper(context);

    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of events
     */
    public ArrayList<Events> getEvents() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM events", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String dateDebut;
            if(cursor.getString(4) == "" || cursor.getString(4) == null){
                dateDebut = "Pas de date";
            }else{
                dateDebut = cursor.getString(4);
            }
            String dateFin;
            if(cursor.getString(4) == "" || cursor.getString(5) == null){
                dateFin = "Pas de date";
            }else{
                dateFin = cursor.getString(4);
            }
            //Log.d("Event date ", dateFin);
            int image = cursor.getInt(3);
           // Log.i("image", image+"");
            //switch){

            if(image ==1 ) {
                //admin
                eventList.add(new Events(cursor.getInt(0), cursor.getString(1), cursor.getString(2), R.drawable.admin, dateDebut, dateFin));
            }else if(image ==2) {
                //assoc
                eventList.add(new Events(cursor.getInt(0), cursor.getString(1), cursor.getString(2), R.drawable.association, dateDebut, dateFin));
            }else if(image ==3) {
                //autre
                eventList.add(new Events(cursor.getInt(0), cursor.getString(1), cursor.getString(2), R.drawable.autres, dateDebut, dateFin));
            }else if(image ==4) {
                //loisir
                eventList.add(new Events(cursor.getInt(0), cursor.getString(1), cursor.getString(2), R.drawable.loisir, dateDebut, dateFin));
            }else{
                //rien
                eventList.add(new Events(cursor.getInt(0), cursor.getString(1), cursor.getString(2), R.drawable.autres, dateDebut, dateFin));
            }

           // eventList.add(new Events(cursor.getInt(0), cursor.getString(1),  cursor.getString(2), R.drawable.autres, dateDebut, dateFin));
            cursor.moveToNext();
        }
       // eventList.add(new Events("event", R.drawable.uqac));
       // adapter = new EventsAdapter(context, eventList);
        cursor.close();
        return eventList;
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of events
     */
    public void select(String query) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        Log.d("QUERY :", query);
        while (!cursor.isAfterLast()) {
            Log.d("Field 1 ", cursor.getString(0));
            Log.d("Field 2", cursor.getString(1));
            cursor.moveToNext();
        }
        // eventList.add(new Events("event", R.drawable.uqac));
        // adapter = new EventsAdapter(context, eventList);
        cursor.close();
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of users
     */
    public ArrayList<Users> getUsers() {
        Cursor cursor = database.rawQuery("SELECT login FROM user", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
        //    Log.d("User  ", cursor.getString(0));
            userList.add(new Users(cursor.getString(0)));
            cursor.moveToNext();
        }
        // eventList.add(new Events("event", R.drawable.uqac));
        // adapter = new EventsAdapter(context, eventList);
        cursor.close();
        return userList;
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of users
     */
    public ArrayList<Assoc> getAssoc() {
        Cursor cursor = database.rawQuery("SELECT associations.id, associations.nom, activity.nom FROM associations, activity WHERE associations.activity = activity.id;", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //    Log.d("User  ", cursor.getString(0));
            assocList.add(new Assoc(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        // eventList.add(new Events("event", R.drawable.uqac));
        // adapter = new EventsAdapter(context, eventList);
        cursor.close();
        return assocList;
    }


    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public Boolean getConnect(String query) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
       // while (!cursor.isAfterLast()) {
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //    Log.d("User  ", cursor.getString(0));
            Log.d("connect", cursor.getString(0));
            cursor.moveToNext();
        }
        // eventList.add(new Events("event", R.drawable.uqac));
        // adapter = new EventsAdapter(context, eventList);
        cursor.close();
        return true;
    }
    public boolean selectLogin(String query) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        boolean resultat = false;
        Log.d("QUERY :", query);
        while (!cursor.isAfterLast()) {
            Log.d("Field 1 ", cursor.getString(0));
            Log.d("Field 2", cursor.getString(1));
            resultat = true;
            cursor.moveToNext();
            //resultat = cursor.getString(0);
        }
        // eventList.add(new Events("event", R.drawable.uqac));
        // adapter = new EventsAdapter(context, eventList);
        cursor.close();
        return resultat;
    }

    /**
     * Insert Events
     * @param title
     * @param description
     */
    public boolean insertEvents(String title, String description, String dateDebut, String dateFin, String categ) {
        //String sql = "INSERT INTO events(title,description) VALUES(?,?)";
        //database.insert("events", "" )
        if (title == "") {
            return false;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", title);
            contentValues.put("description", description);
            contentValues.put("dateDebut", dateDebut);
            contentValues.put("dateFin", dateFin);
            contentValues.put("image", categ);
            long result = database.insert("events", null, contentValues);

            if (result == -1) {
                return false;
            } else {

                return true;
            }
        }
    }
    public boolean removeEvents(String id){
        database.delete("events", "id" + "='" + id+"'", null);

        return true;
    }

}