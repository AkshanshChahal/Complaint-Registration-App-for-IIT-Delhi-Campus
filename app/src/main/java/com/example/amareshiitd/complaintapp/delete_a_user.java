package com.example.amareshiitd.complaintapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class delete_a_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_a_user);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText et = (EditText)findViewById(R.id.editText4);
         final String name = et.getText().toString();
        Button add = (Button)findViewById(R.id.button2);
        Button delete = (Button)findViewById(R.id.button3);
        Button modify = (Button)findViewById(R.id.button4);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), add_a_user.class);
                i.putExtra("username",name);
                startActivity(i);

            }}                );
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                RequestQueue _requestQueue = Volley.newRequestQueue(getApplicationContext());
             EditText et =(EditText)findViewById(R.id.editText4);
                String username = et.getText().toString();
                StringRequest jsObjRequest = new StringRequest(Request.Method.GET,"http://192.168.43.205/Complaint/delete_user.php?username=" + username,

                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                try {

                                    JSONObject obj = new JSONObject(response);
                                    if (obj.getString("success").equals("true")) {

                                        Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "try2", Toast.LENGTH_LONG).show();


                                    }
                                } catch (JSONException exp) {
                                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                                }


                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "try3", Toast.LENGTH_LONG).show();
                    }
                });
                _requestQueue.add(jsObjRequest);
            }
        });
        modify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), modify_a_user.class);
                i.putExtra("username",name);
                startActivity(i);

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
