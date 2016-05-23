package com.example.darsh.streamer.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.darsh.streamer.R;
import com.example.darsh.streamer.connections.TCPCon;
import com.example.darsh.streamer.adapters.ImageViewAdapter;

public class ImageSlider extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        String[] imgName;
        int size;

        imgName = bundle.getStringArray("img");
        size = bundle.getInt("count");
        TCPCon tcpcon2 = new TCPCon();
        tcpcon2.test0fStatic();
        tcpcon2.getCnt(size);
        Log.d("In Main", String.valueOf(imgName));

        if (bundle != null) {
            Log.d("Bundle", "Found");
            Toast.makeText(ImageSlider.this, "Bundle Found", Toast.LENGTH_SHORT).show();
            ImageViewAdapter imageViewAdapter = new ImageViewAdapter(this, size, imgName);
            ViewPager viewPager = (ViewPager) findViewById(R.id.image_slider);
            viewPager.setAdapter(imageViewAdapter);

        } else {
            Toast.makeText(ImageSlider.this, "No Bundle Found", Toast.LENGTH_SHORT).show();
        }

        Log.d("count ", String.valueOf(size));
    }
}
