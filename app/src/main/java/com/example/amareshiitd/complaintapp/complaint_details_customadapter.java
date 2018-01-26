package com.example.amareshiitd.complaintapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by amareshiitd on 27-03-2016.
 */
public class complaint_details_customadapter extends ArrayAdapter<String[]> {
    public complaint_details_customadapter(Context context,ArrayList<String[]> list) {
        super(context,R.layout.comment_customrow , list);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflator = LayoutInflater.from(getContext()) ;
        //LayoutInflator is a way to tell the function to get ready for rendering

        final View customView_marks = myInflator.inflate(R.layout.comment_customrow, parent, false);
        // customView_marks is the reference to the file custom_row

        // Now we need references to data items which are on the custom_row

        String[] strings = getItem(position);
        TextView comment=(TextView) customView_marks.findViewById(R.id.textView23);
        TextView user=(TextView) customView_marks.findViewById(R.id.textView24);
        TextView dateandtime=(TextView) customView_marks.findViewById(R.id.textView25);




        comment.setText(strings[0]);
        user.setText(strings[1]);
        dateandtime.setText(strings[2]);

        return customView_marks;
    }
}
