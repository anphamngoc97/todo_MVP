package com.example.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.myapplication.mylist.MylistFragment;
import com.example.myapplication.tag.TagFragment;

public class ViewPagerTaskAdapter extends FragmentStatePagerAdapter {
    private int SIZE = 2;

    MylistFragment mylistFragment;
    TagFragment tagFragment;

    public ViewPagerTaskAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:{
                mylistFragment = new MylistFragment();
                return mylistFragment;
            }
            case 1:{
                tagFragment = new TagFragment();
                return tagFragment;
            }
            default:{
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return SIZE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:{
                return "My List";
            }
            case 1:{
                return "Tag";
            }
            default:{
                return "default";
            }
        }
    }
}
