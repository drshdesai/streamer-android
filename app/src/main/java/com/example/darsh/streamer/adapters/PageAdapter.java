package com.example.darsh.streamer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.darsh.streamer.fragments.AudioFragment;
import com.example.darsh.streamer.fragments.ImageFragment;
import com.example.darsh.streamer.fragments.OtherFragment;
import com.example.darsh.streamer.fragments.VideoFragment;

/**
 * Created by dinkydesai on 28/1/16.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ImageFragment();
            case 1:

                return new AudioFragment();
            case 2:
                return new VideoFragment();
            case 3:
                return new OtherFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Image";
                break;
            case 1:
                title="Music";
                break;
            case 2:
                title="Video";
                break;
            case 3:
                title="Other";
                break;
        }
        return title;
    }
}
