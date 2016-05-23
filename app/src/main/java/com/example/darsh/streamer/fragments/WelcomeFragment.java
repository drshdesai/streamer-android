package com.example.darsh.streamer.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.darsh.streamer.R;

/**
 * Created by dinkydesai on 28/1/16.
 */
public class WelcomeFragment extends Fragment {
    String username;
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.welcome_fragment,container,false);
        sp=getActivity().getSharedPreferences("Activity", Context.MODE_PRIVATE);

        TextView usernameText= (TextView) view.findViewById(R.id.welcome_username);
        username=getUsername();
        usernameText.setText(username);

        return  view;
    }

    public String getUsername() {

        return sp.getString("username","");
    }
}
