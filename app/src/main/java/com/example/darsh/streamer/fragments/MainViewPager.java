package com.example.darsh.streamer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.darsh.streamer.R;
import com.example.darsh.streamer.adapters.PageAdapter;

/**
 * Created by dinkydesai on 28/1/16.
 */
public class MainViewPager extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.viewpager_fragment,container,false);

        ViewPager viewPager=(ViewPager)view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageAdapter(getChildFragmentManager()));

        TabLayout tabLayout=(TabLayout)view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(new PageAdapter(getChildFragmentManager()));
        return view;
    }
}
