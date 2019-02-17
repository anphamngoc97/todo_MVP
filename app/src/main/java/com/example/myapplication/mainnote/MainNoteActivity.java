package com.example.myapplication.mainnote;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerMainNoteAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;


public class MainNoteActivity extends AppCompatActivity {

    @BindView(R.id.tabHost)
    NavigationTabBar tabHost;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ViewPagerMainNoteAdapter mainNoteAdapter;

    final ArrayList<NavigationTabBar.Model> tabModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setUpViewPager();
    }

    private void setUpViewPager() {
        mainNoteAdapter = new ViewPagerMainNoteAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainNoteAdapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        for (int i = 0; i < mainNoteAdapter.getCount(); i++) {
            //tabHost.addTab(tabHost.newTab().setText(mainNoteAdapter.getPageTitle(i))
            //.setTabListener(this));
            tabModel.add(new NavigationTabBar.Model.Builder(null,Color.BLUE)
                    .title("title")
                    .badgeTitle("badge")
                    .build()
            );
        }
        tabHost.setModels(tabModel);
        tabHost.setViewPager(viewPager);
        tabHost.setTitleMode(NavigationTabBar.TitleMode.ALL);
    }
}
