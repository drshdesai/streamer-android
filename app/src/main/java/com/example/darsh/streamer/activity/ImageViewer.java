package com.example.darsh.streamer.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.darsh.streamer.adapters.ImageViewAdapter;
import com.example.darsh.streamer.R;
import com.example.darsh.streamer.connections.TCPCon;

public class ImageViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String[] imgName;
        int size;

        String[] list={
            "Hello","How","You"
        };

        //Calling a thread to communicate from class TCPComm
        //new TCPComm().execute();



        imgName = bundle.getStringArray("img");
        size = bundle.getInt("count");
        TCPCon tcpcon2 = new TCPCon();
        tcpcon2.test0fStatic();
        tcpcon2.getCnt(size);
        Log.d("In Main", String.valueOf(imgName));

        if (bundle != null) {
            Log.d("Bundle", "Found");
            Toast.makeText(ImageViewer.this, "Bundle Found", Toast.LENGTH_SHORT).show();
            ImageViewAdapter imageViewAdapter = new ImageViewAdapter(this, size, imgName);
            ViewPager viewPager = (ViewPager) findViewById(R.id.image_slider);
            viewPager.setAdapter(imageViewAdapter);

        } else {
            Toast.makeText(ImageViewer.this, "No Bundle Found", Toast.LENGTH_SHORT).show();
        }

        Log.d("count ", String.valueOf(size));
    }
}
