package com.example.darsh.streamer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.darsh.streamer.R;

public class UsernamePrompt extends AppCompatActivity {
    SharedPreferences sp;
    FloatingActionButton fab;
    TextInputEditText unameEdit;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_prompt);
        sp = getSharedPreferences("Activity", MODE_PRIVATE);

        unameEdit = (TextInputEditText) findViewById(R.id.username_edittext);
        fab = (FloatingActionButton) findViewById(R.id.fab_next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (unameEdit.getText().length()==0) {
                    unameEdit.setError("Please enter username!");
                } else if (unameEdit.getText().length() <= 8 && unameEdit.getText().length() >= 4) {
                    username = unameEdit.getText().toString();
                    saveUsername(username);
                    //sharedAnimation();
                    Intent intent = new Intent(UsernamePrompt.this, MainScreen.class);
                    startActivity(intent);
                    sp.edit().putBoolean("first-start", true).apply();
                    finish();

                } else {
                    unameEdit.setError("Please enter username between 4 to 8 characters!");
                }

            }
        });
    }

   /* private void sharedAnimation() {
        View view=fab;
        String transitionName= String.valueOf(R.string.transition_name);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(UsernamePrompt.this, MainScreen.class);
            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(UsernamePrompt.this,view,transitionName);
            startActivity(intent, options.toBundle());
        }
    }*/

    private void saveUsername(String username) {
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("username",username);
        editor.apply();
    }
}
