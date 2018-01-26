package com.example.amareshiitd.complaintapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by amareshiitd on 26-03-2016.
 */
public class notifications extends Fragment {

    public notifications() {
        // Required empty public constructor
    }
        String ccode;

    // This page returns the list of courses with the course code in a list view

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v=inflater.inflate(R.layout.notifications, container, false);
        final ListView listv=(ListView) v.findViewById(R.id.complaintlist);
        RequestQueue _requestQueue = Volley.newRequestQueue(v.getContext());
        Bundle args = getArguments();
        if (args != null && args.containsKey("username")) {
            ccode = args.getString("username");

        }
        // JSON request to fetch the List of the courses


        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, "http://192.168.43.205/Complaint/notification.php?name=" + ccode, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {
                Toast.makeText(v.getContext(), response, Toast.LENGTH_LONG).show();
                try{



                    ArrayList<String[]> alist=new ArrayList<String[]>();

                    JSONObject obj=new JSONObject(response);
                    JSONArray arr=obj.getJSONArray("le");
                    if(arr.length()!=0) {
                        for (int i = 0; i < arr.length(); i++) {
                            String test[] = new String[6];
                            JSONObject h = arr.getJSONObject(i);
                            test[0] = h.getString("string");
                            test[1] = h.getString("username");
                            test[2] = h.getString("level");
                            test[3] = h.getString("complaint_id");
                            test[4]=h.getString("target");
                            test[5]=h.getString("date");
                            alist.add(test);
                        }

                        ListAdapter myAdapter1 = new complaint_customadapter(v.getContext(), alist);

                        listv.setAdapter(myAdapter1);
                    }
                    else

                        Toast.makeText(v.getContext(),"No Complaints posted yet",Toast.LENGTH_LONG).show();
                }
                catch(JSONException exp)
                {
                    Toast.makeText(v.getContext(), "ERROR", Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });


        _requestQueue.add(jsObjRequest);
        listv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void  onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent i=new Intent(v.getContext(),complaint_details.class);
                        String s[]=(String[])parent.getItemAtPosition(position);
                        String move = String.valueOf(s[3]);
                        String user = String.valueOf(s[1]);
                        String level = String.valueOf(s[2]);
                        String target = String.valueOf(s[4]);
                        i.putExtra("Complaintid",move);
                        i.putExtra("username",ccode);
                        i.putExtra("level",level);
                        i.putExtra("user",user);
                        i.putExtra("target",target);
                        startActivity(i);


                    }
                }
        );

        return v;
    }

}
