package com.example.darsh.streamer.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.darsh.streamer.R;
import com.example.darsh.streamer.connections.TCPCon;

/**
 * Created by drshdesai on 3/31/16.
 */
public class ImageViewAdapter extends PagerAdapter {
    Bitmap bmp;
    int current=-1;
    Context context;
    int size;
    String[] imgName;
    LayoutInflater layoutInflater;
    int[] list;

    public ImageViewAdapter(Context context, int size, String[] imgName) {
        this.context = context;
        this.imgName = imgName;
        this.size = size;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //private View mCurrentView;

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Log.d("Networking", "in setPrimaryItem");
        if (current != position) {
            current=position;
            TCPCon tcpcon3 = new TCPCon();
            //tcpcon3.execute();
            tcpcon3.imgaddress(imgName[position], current);
            //mCurrentView = (View) object;
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = layoutInflater.inflate(R.layout.image_slider, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_slider_container);
        Log.d("image", String.valueOf(position));
        Log.d("ToBeDisplayed", imgName[position]);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inJustDecodeBounds = false;
        options.inSampleSize = 8;
        bmp = BitmapFactory.decodeFile(imgName[position], options);
        imageView.setImageBitmap(bmp);
        container.addView(view);
        return view;
    }

}
