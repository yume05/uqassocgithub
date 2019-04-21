package com.example.uqassoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uqassoc.models.Assoc;
import com.example.uqassoc.models.Users;

import java.util.ArrayList;

public class AssocAdapter extends BaseAdapter{
    private final Context context;
    private final ArrayList<Assoc> assocs;
    private static LayoutInflater inflater = null;

    // 1

    public AssocAdapter(Context context, ArrayList<Assoc> assoc) {
        this.context = context;
        this.assocs = assoc;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 2

    public int getCount() {
        return assocs.size();
    }

    // 3

    public long getItemId(int position) {
        return position;
    }

    // 4

    public Assoc getItem(int position) {
        return assocs.get(position);
    }

    // 5

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = inflater.inflate(R.layout.assoc_item, null);
        }

        TextView nom_assoc= (TextView) itemView.findViewById(R.id.nomAssoc);
        TextView activity_assoc= (TextView) itemView.findViewById(R.id.activityAssoc);

        Assoc selectedEvent = assocs.get(position);

        nom_assoc.setText(selectedEvent.nom);
        activity_assoc.setText(selectedEvent.activity);
        return itemView;
    }

}
