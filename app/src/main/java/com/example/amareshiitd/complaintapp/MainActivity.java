package com.example.amareshiitd.complaintapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Button button_s = (Button) findViewById(R.id.student_login_button);

        EditText username = (EditText) findViewById(R.id.username_input);
        EditText password = (EditText) findViewById(R.id.password_input);
        final String u = username.getText().toString();
        final String p = password.getText().toString();
        //
        button_s.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final EditText username = (EditText) findViewById(R.id.username_input);
                final EditText password = (EditText) findViewById(R.id.password_input);
                final String u = username.getText().toString();
                final String p = password.getText().toString();
                sendRequest(u, p);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendRequest(final String u, final String p) {


        RequestQueue _requestQueue = Volley.newRequestQueue(getApplicationContext());




        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, "http://192.168.43.205/Complaint/login.php?name="+u+"&password="+p, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try{
                    Toast.makeText(getApplicationContext(),"try",Toast.LENGTH_LONG).show();
                    JSONObject obj=new JSONObject(response);
                    String department = obj.getString("department");
                    if(obj.getString("success").equals("true"))
                    {
                        Toast.makeText(getApplicationContext(),"try1",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), navigation_drawer.class);
                        i.putExtra("username",u);
                        i.putExtra("department",department);
                        startActivity(i);
                        TextView tv = (TextView)findViewById(R.id.textView27);
                        tv.setText("");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"try2",Toast.LENGTH_LONG).show();
                        TextView tv = (TextView)findViewById(R.id.textView27);
                        tv.setText("Invalid username or password");

                    }
                }
                catch(JSONException exp)
                {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"try3",Toast.LENGTH_LONG).show();
            }
        })

           /* @Override

            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", u);
                params.put("password", p);
                //params.put("title", "vegetaVsgoku");
                //params.put("date", "2000-12-22 12:00:00");
                //params.put("level", "saiyan");
                //params.put("target", "cell");
                //params.put("description", "Kaioken");
                // params.put("hostel", "earth");
                //params.put("organisation", "KK");
                //params.put("position", "oken");
                return params;
            }*/
        ;


        _requestQueue.add(jsObjRequest);
    }
    }




