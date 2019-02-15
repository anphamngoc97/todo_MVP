package com.example.myapplication.mainnote;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerMainNoteAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import hkm.ui.materialtabs.MaterialTab;
import hkm.ui.materialtabs.MaterialTabHost;
import hkm.ui.materialtabs.MaterialTabListener;

public class MainNoteActivity extends AppCompatActivity implements MainNoteContract.View, MaterialTabListener {

    @BindView(R.id.tabHost)
    MaterialTabHost tabHost;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ViewPagerMainNoteAdapter mainNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        ButterKnife.bind(this);

        init();
    }
    private void init(){
        setUpViewPager();
    }
    private void setUpViewPager(){
        mainNoteAdapter = new ViewPagerMainNoteAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainNoteAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                tabHost.setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        for(int i=0;i<mainNoteAdapter.getCount();i++){
            tabHost.addTab(tabHost.newTab().setText(mainNoteAdapter.getPageTitle(i))
            .setTabListener(this));
        }
    }

    @Override
    public void changeTab(int position) {

    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        Log.d("AAA","tab select");
        viewPager.setCurrentItem(materialTab.getPosition(),true);
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }
}
