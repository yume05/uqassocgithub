package com.example.uqassoc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uqassoc.models.Users;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter{
    private final Context context;
    private final ArrayList<Users> users;
    private static LayoutInflater inflater = null;

    // 1

    public UsersAdapter(Context context, ArrayList<Users> user) {
        this.context = context;
        this.users = user;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 2

    public int getCount() {
        return users.size();
    }

    // 3

    public long getItemId(int position) {
        return position;
    }

    // 4

    public Users getItem(int position) {
        return users.get(position);
    }

    // 5

    public View getView(int position, View convertView, ViewGroup parent) {
       View itemView = convertView;
       if(itemView == null){
           itemView = inflater.inflate(R.layout.user_item, null);
       }

        TextView login_user= (TextView) itemView.findViewById(R.id.loginUser);

        Users selectedEvent = users.get(position);

        login_user.setText(selectedEvent.login);
        return itemView;
    }

}
