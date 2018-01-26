package com.example.amareshiitd.complaintapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amareshiitd on 27-03-2016.
 */
public class add_a_complaint extends Fragment {
 public add_a_complaint(){

 }
String ccode;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        
        final View v = inflater.inflate(R.layout.add_a_complaint, container, false);
        final Spinner spinner = (Spinner)v.findViewById(R.id.spinner);
        final Spinner spinner2 = (Spinner)v.findViewById(R.id.spinner2);
        final Spinner spinner3 = (Spinner)v.findViewById(R.id.spinner9);

        final Button bt = (Button)v.findViewById(R.id.button);
        Bundle args = getArguments();

        if (args != null && args.containsKey("username")) {
            ccode = args.getString("username");
        }
        spinner.setOnItemSelectedListener     // Drop Down menu
                (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(parent.getSelectedItem().toString().equals("Individual"))
                        {
                            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                                    v.getContext(), R.array.individual, android.R.layout.simple_spinner_dropdown_item);

                            spinner2.setAdapter(adapter);
                        }
                        if(parent.getSelectedItem().toString().equals("hostel"))
                        {
                            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                                    v.getContext(), R.array.hostel, android.R.layout.simple_spinner_dropdown_item);

                            spinner2.setAdapter(adapter2);
                        }
                        if(parent.getSelectedItem().toString().equals("Institute"))
                        {
                            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                                    v.getContext(), R.array.Institute, android.R.layout.simple_spinner_dropdown_item);

                            spinner2.setAdapter(adapter3);
                        }
                    }



                    @Override
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View l) {
                RequestQueue _requestQueue = Volley.newRequestQueue(v.getContext());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                Toast.makeText(getContext(), date, Toast.LENGTH_LONG).show();
                final EditText username = (EditText) v.findViewById(R.id.editText6);
                final EditText password = (EditText) v.findViewById(R.id.editText);
                final String title = username.getText().toString();


                final String desc = password.getText().toString();

                String hell = "http://192.168.43.205/Complaint/post_complaint.php?username=" + ccode + "&title=" + title +
                        "&level=" + spinner.getSelectedItem().toString() + "&target=" + spinner2.getSelectedItem().toString() + "&description=" + spinner2.getSelectedItem().toString()+":"+desc + "&visibility=" + spinner3.getSelectedItem().toString()+ "&date=" + date.substring(0,10) +"%20" +date.substring(11,19);
                Toast.makeText(getContext(), hell, Toast.LENGTH_LONG).show();

                StringRequest jsObjRequest = new StringRequest(Request.Method.GET,hell ,

                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                                try {

                                    JSONObject obj = new JSONObject(response);
                                    if (obj.getString("success").equals("true")) {

                                        Toast.makeText(getContext(), "successful", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getContext(), "try2", Toast.LENGTH_LONG).show();


                                    }
                                } catch (JSONException exp) {
                                    Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                                }


                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "try3", Toast.LENGTH_LONG).show();
                    }
                });
                _requestQueue.add(jsObjRequest);
            }
        });
        return v;
    }
}
