package com.example.darsh.streamer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darsh.streamer.R;
import com.example.darsh.streamer.connections.TCPCon;
import com.example.darsh.streamer.fragments.MainViewPager;
import com.example.darsh.streamer.fragments.WelcomeFragment;

import org.w3c.dom.Text;

public class MainScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int PICK_IMAGE = 1;
    //to see if the connection is on or not
    Boolean con_state = Boolean.FALSE;
    //calling the class TCPCon for test purpose
    public TCPCon tcpcon;// = new TCPCon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                int flag;

                @Override
                public void onClick(View view) {
                    /*WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
                    if (wifiManager.isWifiEnabled() == true) {
                        wifiManager.setWifiEnabled(false);
                        fab.setImageResource(R.drawable.play);
                        Toast.makeText(MainScreen.this, "Wifi Disabled", Toast.LENGTH_SHORT).show();
                    } else {
                        wifiManager.setWifiEnabled(true);
                        fab.setImageResource(R.drawable.stop);
                        Toast.makeText(MainScreen.this, "Wifi Enabled", Toast.LENGTH_SHORT).show();
                    }*/
                    if (con_state==Boolean.FALSE) {
                        con_state=Boolean.TRUE;

                        tcpcon = new TCPCon();
                        tcpcon.execute();
                        //tcpcon.conect();

                        fab.setImageResource(R.drawable.ic_signal_wifi_4_bar_white_24dp);
                    }
                    else {
                        con_state=Boolean.FALSE;
                        //tcpcon.cancel(true);
                        //tcpcon.endcon();
                        fab.setImageResource(R.drawable.ic_signal_wifi_off_white_24dp);
                    }
                }
            });
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setScrimColor(Color.parseColor("#00FFFFFF"));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView= navigationView.getHeaderView(0);
        TextView userText= (TextView) headerView.findViewById(R.id.nav_username);
        SharedPreferences sp=getSharedPreferences("Activity",MODE_PRIVATE);
        userText.setText(sp.getString("username",""));

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.containerFrame, new WelcomeFragment()).commit();
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
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.string.action_exit) {
            return true;
        } else if(id==R.string.action_exit){
            Intent i=new Intent(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            startActivity(i);
            finish();
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager=getSupportFragmentManager();

        if(id==R.id.nav_home){
            fragmentManager.beginTransaction().replace(R.id.containerFrame,new MainViewPager()).commit();
        }

        if(id == R.id.aboutus){
            Intent intent = new Intent(getApplicationContext(),AboutUs.class);
            startActivity(intent);
        }

        if(id==R.id.nav_settings){
            Intent intent=new Intent(getApplicationContext(),Settings.class);
            startActivity(intent);
        }
        if (id==R.id.help){
            Intent intent=new Intent(getApplicationContext(),HelpUs.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
