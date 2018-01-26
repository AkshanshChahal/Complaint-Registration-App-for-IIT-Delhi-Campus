package com.example.amareshiitd.complaintapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amareshiitd on 26-03-2016.
 */
public class notification_customadapter extends ArrayAdapter<String[]> {
    public notification_customadapter(Context context,ArrayList<String[]> list) {
        super(context,R.layout.complaints_customrow , list);
    }

    @Override // This where the data passed is turned into the layout
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflator = LayoutInflater.from(getContext());
        //LayoutInflator is a way to tell the function to get ready for rendering

        View customView_marks = myInflator.inflate(R.layout.notifications_customrow, parent, false);
        // customView_marks is the reference to the file custom_row

        // Now we need references to data items which are on the custom_row
        String[] strings = getItem(position);
        TextView notificationname = (TextView) customView_marks.findViewById(R.id.textView23);
        TextView notificationtime = (TextView) customView_marks.findViewById(R.id.textView24);


        notificationname.setText(strings[0]);

        notificationtime.setText(strings[5] + "ago");

        return customView_marks;
    }
}
