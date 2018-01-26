package com.example.amareshiitd.complaintapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class add_a_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Spinner spinner = (Spinner)findViewById(R.id.spinner5); // Drop Down menu
        Button button = (Button)findViewById(R.id.button5);
        spinner.setOnItemSelectedListener
                (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (parent.getSelectedItem().toString().equals("Individual")) {
                            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                                    getApplicationContext(), R.array.individual1, R.layout.spinner_text);
                            final Spinner spinner2 = (Spinner)findViewById(R.id.spinner6);
                            spinner2.setAdapter(adapter);
                        }
                        if (parent.getSelectedItem().toString().equals("hostel")) {
                            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                                   getApplicationContext(), R.array.hostel, R.layout.spinner_text);
                            final Spinner spinner2 = (Spinner)findViewById(R.id.spinner6);
                            spinner2.setAdapter(adapter2);
                        }
                        if (parent.getSelectedItem().toString().equals("Institute")) {
                            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                                   getApplicationContext(), R.array.Institute, R.layout.spinner_text);
                            final Spinner spinner2 = (Spinner)findViewById(R.id.spinner6);
                            spinner2.setAdapter(adapter3);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        button.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View v) {
                                          EditText username = (EditText)findViewById(R.id.textView4);
                                          EditText password = (EditText)findViewById(R.id.textView5);
                                          EditText hostel = (EditText)findViewById(R.id.textView6);
                                          Spinner usertype = (Spinner)findViewById(R.id.spinner5);
                                          Spinner  department = (Spinner)findViewById(R.id.spinner6);
                                          String username1 = username.getText().toString();
                                          String password1 = password.getText().toString();
                                          String hostel1 = hostel.getText().toString();
                                          RequestQueue _requestQueue = Volley.newRequestQueue(getApplicationContext());

                                          StringRequest jsObjRequest = new StringRequest(Request.Method.GET,"http://192.168.43.205/Complaint/add_user.php?username=" + username1+"&password="+password1+"&hostel="+hostel1+
                                                  "&usertype="+usertype.getSelectedItem().toString()+"&department="+department.getSelectedItem().toString(),

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
                                  }

        );
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
