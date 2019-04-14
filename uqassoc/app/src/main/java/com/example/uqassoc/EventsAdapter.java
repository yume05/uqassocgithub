package com.example.uqassoc;

import android.content.Context;
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
        return position;
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
        TextView idViewEvent= (TextView) itemView.findViewById(R.id.idViewEvent);

        Events selectedEvent = events.get(position);

        imageViewEventName.setImageResource(selectedEvent.image);
        textViewEvent.setText(selectedEvent.title);
        idViewEvent.setText(selectedEvent.id);

        return itemView;
    }

}
