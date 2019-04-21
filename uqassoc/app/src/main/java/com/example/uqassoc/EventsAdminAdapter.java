package com.example.uqassoc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.uqassoc.models.Events;
import com.example.uqassoc.AdminGestion;

import java.util.ArrayList;

public class EventsAdminAdapter extends BaseAdapter{
    private final Context context;
    private final ArrayList<Events> events;
    private static LayoutInflater inflater = null;
    private Events selectedEvent;
    // 1

    public EventsAdminAdapter(Context context, ArrayList<Events> events) {
        this.context = context;
        this.events = events;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 2

    public int getCount() {
        return events.size();
    }

    // 3

    public long getItemId(int position) {
        Events selectedEvent = events.get(position);

        return position;
    }

    public String getTitle(int position) {
        Events selectedEvent = events.get(position);
        String title = selectedEvent.title;
        if(title == ""){
            title = "";
        }
        return title;
    }
    public String getDescription(int position) {
        Events selectedEvent = events.get(position);
        String description = selectedEvent.description;
        if(description == ""){
            //   Log.i("description vide", "OK");
            description = "";
        }
        // Log.i("Description", description);
        return description;
    }

    public String getDateDebut(int position) {
        Events selectedEvent = events.get(position);
        String dateDebut = selectedEvent.dateDebut;
        // Log.i("date debut", dateDebut);
        return dateDebut;
    }

    public String getDateFin(int position) {
        Events selectedEvent = events.get(position);
        String dateFin = selectedEvent.dateFin;
        //  Log.i("date debut", dateFin);
        return dateFin;
    }

    // 4

    public Events getItem(int position) {
        return events.get(position);
    }

    // 5

    public View getView(int position, final View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = inflater.inflate(R.layout.events_admin_item, null);
        }

        TextView t = (TextView) itemView.findViewById(R.id.titleEvent);
        TextView d= (TextView) itemView.findViewById(R.id.descriptionEvent);
        TextView dd = (TextView) itemView.findViewById(R.id.dateDebutEvent);
        TextView df= (TextView) itemView.findViewById(R.id.dateFinEvent);

        selectedEvent = events.get(position);

        t.setText(selectedEvent.title);
        if(selectedEvent.description.length() > 20){
            String descriptionafter = selectedEvent.description.substring(0, Math.min(selectedEvent.description.length(), 20));
            d.setText(descriptionafter+"[...]");
        }else{
            d.setText(selectedEvent.description);
        }

        if( selectedEvent.dateDebut.length() < 4){
            dd.setText("Null");
        }else{
            dd.setText(selectedEvent.dateDebut);
        }
        if(selectedEvent.dateFin.length() < 4){
            df.setText("Null");
        }else{
            df.setText(selectedEvent.dateFin);
        }


        Button buttonDeleteEvent = (Button) itemView.findViewById(R.id.buttonDeleteEvent);
       buttonDeleteEvent.setId(selectedEvent.id);

       buttonDeleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("event", String.valueOf(v.getId()));
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
                databaseAccess.open();
                String id = String.valueOf(v.getId());
                Log.i("idevent", id);
                Boolean ok = databaseAccess.removeEvents(id);
                databaseAccess.close();
                // Log.i("add date", dateDebut);
                // Log.i("add date", dateFin);
                if (ok){
                    Toast.makeText(context, "Evenement supprimé", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Erreur dans la suppression de l'évenement", Toast.LENGTH_SHORT).show();
                }
                ((AdminGestion)context).eventsList.clear();
                ((AdminGestion)context).initialiseEvents();
            }

       });

        return itemView;
    }

}
