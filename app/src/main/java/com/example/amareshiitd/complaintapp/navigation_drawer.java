package com.example.amareshiitd.complaintapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class navigation_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
      String course_code;
    String department;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle=getIntent().getExtras();
        course_code=bundle.getString("username");
        department =bundle.getString("department");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(department.equals("admin"))
        {
            fab.setVisibility(View.VISIBLE);

            //FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
            //FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
            //FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
            //fab1.setEnabled(false);
            //fab2.setEnabled(false);
            //fab3.setEnabled(false);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                /*FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
                FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
                FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
                if (fab1.getVisibility() == view.VISIBLE) {
                    fab1.setVisibility(view.INVISIBLE);
                    fab1.setEnabled(false);
                } else {
                    fab1.setVisibility(view.VISIBLE);
                    fab1.setEnabled(true);
                }

                if (fab2.getVisibility() == view.VISIBLE) {
                    fab2.setVisibility(view.INVISIBLE);
                    fab2.setEnabled(false);
                } else {
                    fab2.setVisibility(view.VISIBLE);
                    fab2.setEnabled(true);
                }
                if (fab3.getVisibility() == view.VISIBLE) {
                    fab3.setVisibility(view.INVISIBLE);
                    fab3.setEnabled(false);
                } else {
                    fab3.setVisibility(view.VISIBLE);
                    fab3.setEnabled(true);
                }
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), navigation_drawer.class);
                startActivity(i);
            }
            });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), navigation_drawer.class);
                startActivity(i);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), navigation_drawer.class);
                startActivity(i);
            }
        });*/

                    Intent i = new Intent(getApplicationContext(), delete_a_user.class);
                    startActivity(i);
                }
            });
        }
        else
        {
            fab.setVisibility(View.INVISIBLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        Bundle bundle=new Bundle();
        bundle.putString("username",course_code);
        if (id == R.id.Complaints) {
            complaints fragment = new complaints();
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragment.setArguments(bundle);
            ft.replace(R.id.fragment_container,fragment);
            ft.commit();
        } else if (id == R.id.Add_a_complaint) {

            add_a_complaint fragment = new add_a_complaint();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,fragment);
            ft.commit();
        } else if (id == R.id.Notifications) {

            notifications fragment = new notifications();
            fragment.setArguments(bundle);
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
