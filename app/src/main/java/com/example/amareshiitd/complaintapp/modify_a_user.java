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

public class modify_a_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_a_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText et = (EditText)findViewById(R.id.editText5);
        Spinner spinner = (Spinner)findViewById(R.id.spinner7);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner8);
        spinner.setOnItemSelectedListener
                (new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (parent.getSelectedItem().toString().equals("username")) {
                            EditText et = (EditText) findViewById(R.id.editText5);
                            et.setVisibility(View.VISIBLE);
                            spinner2.setVisibility(View.INVISIBLE);
                        }
                        if (parent.getSelectedItem().toString().equals("password")) {
                            EditText et = (EditText) findViewById(R.id.editText5);
                            et.setVisibility(View.VISIBLE);
                            spinner2.setVisibility(View.INVISIBLE);
                        }
                        if (parent.getSelectedItem().toString().equals("hostel")) {

                            et.setVisibility(View.VISIBLE);
                            spinner2.setVisibility(View.INVISIBLE);
                        }
                        if (parent.getSelectedItem().toString().equals("usertype")) {
                            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                                    getApplicationContext(), R.array.type, R.layout.spinner_text);

                            spinner2.setVisibility(View.VISIBLE);
                            spinner2.setAdapter(adapter2);
                            et.setVisibility(View.INVISIBLE);
                        }
                        if (parent.getSelectedItem().toString().equals("department")) {

                            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                                    getApplicationContext(), R.array.help, R.layout.spinner_text);
                            spinner2.setVisibility(View.VISIBLE);
                            spinner2.setAdapter(adapter3);
                            et.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        Button button = (Button)findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View v) {
                                          EditText username = (EditText) findViewById(R.id.editText7);
                                          EditText modify = (EditText) findViewById(R.id.editText5);
                                          Spinner spinner1 = (Spinner) findViewById(R.id.spinner7);
                                          Spinner spinner2 = (Spinner) findViewById(R.id.spinner8);
                                          String username1 = username.getText().toString();
                                          String modify1 = modify.getText().toString();
                                          StringRequest jsObjRequest = null;
                                          RequestQueue _requestQueue = Volley.newRequestQueue(getApplicationContext());
                                          if (spinner1.getSelectedItem().toString().equals("username") || spinner1.getSelectedItem().toString().equals("password") || spinner1.getSelectedItem().toString().equals("hostel")) {


                                              jsObjRequest = new StringRequest(Request.Method.GET, "http://192.168.43.205/Complaint/modify.php?username=" + username1+"&type="+spinner1.getSelectedItem().toString()+"&new="+modify1,

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

                                          } else {


                                              jsObjRequest = new StringRequest(Request.Method.GET,"http://192.168.43.205/Complaint/modify.php?username=" + username1+"&type="+spinner1.getSelectedItem().toString()+"&new="+spinner2.getSelectedItem().toString(),

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
                                          }
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
