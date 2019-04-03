package com.example.uqassoc;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.uqassoc.models.Events;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    EventsAdapter adapter;
    private Context context;
    ArrayList<Events> eventList = new ArrayList<Events>();
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
     * @return a List of quotes
     */
    public ArrayList<Events> getEvents() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM events", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.d("ok", cursor.getString(0));
            eventList.add(new Events(cursor.getString(0), cursor.getString(1), R.drawable.uqac, cursor.getString(3)));
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
     * @return a List of quotes
     */
    public Boolean connect(String login, String password) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM associations WHERE login = "+login+" AND password = "+password+";", null);
        cursor.moveToFirst();
        Boolean resultat;
        if(cursor.getCount() > 0){
            resultat = true;
        }else{
            resultat = false;
        }
        cursor.close();
        return resultat;
    }
}