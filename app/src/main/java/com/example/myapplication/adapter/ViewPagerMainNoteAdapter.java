package com.example.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.myapplication.RootFragment;
import com.example.myapplication.calender.CalendarFragment;
import com.example.myapplication.task.TaskFragment;

public class ViewPagerMainNoteAdapter extends FragmentStatePagerAdapter {
    private int SIZE = 2;

    CalendarFragment calendarFragment;
    TaskFragment taskFragment;
    RootFragment rootFragment;

    public ViewPagerMainNoteAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:{
                rootFragment = new RootFragment();
                return rootFragment;
            }
            case 1:{
                calendarFragment = new CalendarFragment();
                return calendarFragment;

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
                return "Task";
            }
            case 1:{
                return "Calendar";
            }
            default:{
                return "DEFAULT";
            }
        }
    }
}
