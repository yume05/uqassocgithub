package com.example.uqassoc;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.example.uqassoc.models.Events;

import java.util.ArrayList;

public class EventsAdapter extends BaseAdapter{
    private final Context context;
    private final ArrayList<Events> events;
    private static LayoutInflater inflater = null;

    // 1

    public EventsAdapter(Context context, ArrayList<Events> events) {
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

    public int getImage(int position) {
        Events selectedEvent = events.get(position);
        int image = selectedEvent.image;
        Log.i("image popup", image+"");
        return image;
    }

    // 4

    public Events getItem(int position) {
        return events.get(position);
    }

    // 5

    public View getView(int position, View convertView, ViewGroup parent) {
       View itemView = convertView;
       if(itemView == null){
           itemView = inflater.inflate(R.layout.event_item, null);
       }

        ImageView imageViewEventName = (ImageView) itemView.findViewById(R.id.imageViewEvent);
        TextView textViewEvent= (TextView) itemView.findViewById(R.id.textViewEvent);


        Events selectedEvent = events.get(position);

        imageViewEventName.setImageResource(selectedEvent.image);
        textViewEvent.setText(selectedEvent.title);

        return itemView;
    }

}
