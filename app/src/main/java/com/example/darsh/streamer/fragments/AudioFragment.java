package com.example.darsh.streamer.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darsh.streamer.R;

import java.util.ArrayList;

/**
 * Created by dinkydesai on 28/1/16.
 */
public class AudioFragment extends Fragment {
    View audioFragment;
    ListView audioListView;
    TextView textview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        audioFragment=inflater.inflate(R.layout.audio_fragment,container,false);
        audioListView= (ListView) audioFragment.findViewById(R.id.audioList);
        textview= (TextView) audioFragment.findViewById(R.id.audioText);
        ArrayList<String> audioList = new ArrayList<>();

        String[] proj = { MediaStore.Audio.Media._ID,MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA};

        Cursor audioCursor = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);

        if(audioCursor != null){
            if(audioCursor.moveToFirst()){
                do{
                    int audioIndex = audioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
                    audioList.add(audioCursor.getString(audioIndex));
                }while(audioCursor.moveToNext());
            }
        }
        else{
            textview.setText("No Music");
            textview.setVisibility(View.VISIBLE);
        }
        audioCursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1, audioList);
        audioListView.setAdapter(adapter);

        return audioFragment;
    }
}
