package com.example.darsh.streamer.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.darsh.streamer.R;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sp = getSharedPreferences("Activity", MODE_PRIVATE);
        int secondsDelayed = 3;

        final ImageView logo= (ImageView) findViewById(R.id.splashLogo);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (sp.getBoolean("first-start", false)) {
                    Intent i = new Intent(SplashScreen.this, MainScreen.class);       //direct to home screen
//                    View view=logo;
//                    String transitionName= String.valueOf(R.string.transition_name);
//                    ActivityOptions options= null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                        options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,view,transitionName);
//                    }
                    startActivity(i);
                    finish();
                }
                else {
                    Intent i=new Intent(getApplicationContext(), UsernamePrompt.class);       //direct to asking username
//                    View view=logo;
//                    String transitionName= String.valueOf(R.string.transition_name);
//                    ActivityOptions options= null;
//                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                        options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,view,transitionName);
//                    }
                    startActivity(i);
                    finish();
                }
            }
        }, secondsDelayed * 100);
    }

    /*private void checkFirstLogin() {


        //if no pref is found, that means its first login -> ask for username or direct to home screen
        if (sp.getBoolean("first-start", false)) {
            Intent i = new Intent(SplashScreen.this, MainScreen.class);       //direct to home screen
            startActivity(i);
            finish();       //prevent user to come back to logo screen
        } else {
            Intent intent = new Intent(SplashScreen.this, UsernamePrompt.class);      //ask for username
            startActivity(intent);
        }
    }*/
}
