package com.example.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Switch;

import com.example.myapplication.calender.CalendarFragment;
import com.example.myapplication.task.TaskFragment;

public class ViewPagerMainNoteAdapter extends FragmentStatePagerAdapter {
    private int SIZE = 2;

    CalendarFragment calendarFragment;
    TaskFragment taskFragment;

    public ViewPagerMainNoteAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:{
                Log.d("AAA","getitem fragment 0");
                calendarFragment = new CalendarFragment();
                return calendarFragment;
            }
            case 1:{
                Log.d("AAA","getitem fragment 1");
                taskFragment = new TaskFragment();
                return taskFragment;

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
                return "PAGE1";
            }
            case 1:{
                return "PAGE 2";
            }
            default:{
                return "DEFAULT";
            }
        }
    }
}
