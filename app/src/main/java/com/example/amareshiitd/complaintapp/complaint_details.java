package com.example.amareshiitd.complaintapp;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class complaint_details extends AppCompatActivity {
    String code;
    String username;
    String level;
    String user;
    String target;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle=getIntent().getExtras();
        code=bundle.getString("Complaintid");
        username = bundle.getString("username");
        level = bundle.getString("level");
        user=bundle.getString("user");
        target=bundle.getString("target");

        RequestQueue _requestQueue = Volley.newRequestQueue(this);
        final ListView trylist = (ListView)findViewById(R.id.listView);

        Toast.makeText(getApplicationContext(), "http://192.168.43.205/Complaint/complaint_details.php?complaint_id="+code+username, Toast.LENGTH_LONG).show();
        // JSON request to obtain the thread with all the comments.
        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, "http://192.168.43.205/Complaint/complaint_details.php?complaint_id="+code, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                try{

                    JSONObject obj=new JSONObject(response);
                    JSONArray arr=obj.optJSONArray("comments");
                    JSONObject info = obj.optJSONObject("details");
                    TextView compliantname = (TextView)findViewById(R.id.complaint_name);
                    TextView description = (TextView)findViewById(R.id.description);
                    TextView user = (TextView)findViewById(R.id.user);
                    TextView date = (TextView)findViewById(R.id.date);
                    TextView resolved_status = (TextView)findViewById(R.id.resolved_status);
                    TextView vote_count = (TextView)findViewById(R.id.vote_count);

                    compliantname.setText(info.getString("title"));
                    date.setText(info.getString("date"));

                    description.setText(info.getString("description"));
                    user.setText(info.getString("username"));
                    resolved_status.setText(info.getString("resolve_status"));
                    vote_count.setText(info.getString("vote_count"));

                    ArrayList<String[]> alist = new ArrayList<String[]>();
                    for (int i = 0; i < arr.length(); i++) {
                        String test[] = new String[3];
                        JSONObject p = arr.getJSONObject(i);
                        test[0] = p.getString("description");
                        test[1] = p.getString("username");
                        test[2] = p.getString("date");
                        alist.add(test);
                    }
                    ListAdapter myAdapter1 = new complaint_details_customadapter(getApplicationContext(), alist);

                    trylist.setAdapter(myAdapter1);


                }
                catch(JSONException exp)
                {
                    Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "rjgn", Toast.LENGTH_LONG).show();

            }
        });
        _requestQueue.add(jsObjRequest);
        Button b = (Button)findViewById(R.id.button);


        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RequestQueue _requestQueue = Volley.newRequestQueue(getApplicationContext());
                EditText ed = (EditText) findViewById(R.id.comment);
                String comment = ed.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                Toast.makeText(getApplicationContext(), "http://192.168.43.205/Complaint/add_comment.php?complaint_id=" + code + "&description=" + comment+"&date=" + date.substring(0,10) +"%20" +date.substring(11,19)+"&username="+username
                        , Toast.LENGTH_LONG).show();
                StringRequest jsObjRequest = new StringRequest(Request.Method.GET, "http://192.168.43.205/Complaint/add_comment.php?complaint_id=" + code + "&description=" + comment+"&date=" + date.substring(0,10) +"%20" +date.substring(11,19)+"&username="+username,

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

        RadioGroup vote = (RadioGroup)findViewById(R.id.vote);
        RadioButton vote_up =(RadioButton)findViewById(R.id.voteup);
        RadioButton vote_down =(RadioButton)findViewById(R.id.votedown);
        RadioButton resolve =(RadioButton)findViewById(R.id.resolve);

        if(level.equals("individual"))
        {
            vote.setVisibility(View.INVISIBLE);
        }
        else
        {

        vote.setVisibility(View.VISIBLE);
        vote_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RequestQueue _requestQueue = Volley.newRequestQueue(getApplicationContext());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                StringRequest jsObjRequest = new StringRequest(Request.Method.GET, "http://192.168.43.205/Complaint/vote.php?complaint_id=" + code + "&vote_value=" + 1+"&date=" + date.substring(0,10) +"%20" +date.substring(11,19)+"&username="+username,

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
            }}

        );

        vote_down.setOnClickListener(new View.OnClickListener() {

                                       @Override
                                       public void onClick(View v) {
                                           RequestQueue _requestQueue = Volley.newRequestQueue(getApplicationContext());
                                           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                           String date = sdf.format(new Date());
                                           StringRequest jsObjRequest = new StringRequest(Request.Method.GET, "http://192.168.43.205/Complaint/vote.php?complaint_id=" + code + "&vote_value=" + -1+"&date=" + date.substring(0,10) +"%20" +date.substring(11,19)+"&username="+username,

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
                                       }}

        );}
        if((level.equals("individual")
                &&user.equals(username))||
                ((!level.equals("individual")) &&
                        target.equals(username)))
        {

        resolve.setVisibility(View.VISIBLE);
        resolve.setOnClickListener(new View.OnClickListener() {

                                         @Override
                                         public void onClick(View v)
                                         {
                                             RequestQueue _requestQueue = Volley.newRequestQueue(getApplicationContext());
                                             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                             String date = sdf.format(new Date());
                                             StringRequest jsObjRequest = new StringRequest(Request.Method.GET, "http://192.168.43.205/Complaint/resolved.php?complaint_id=" + code+"&username="+username,

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
                                         }}

        );}
        else
        {
            resolve.setVisibility(View.INVISIBLE);
        }

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
