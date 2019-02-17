package com.example.myapplication.task;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerTaskAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskFragment extends Fragment {

    @BindView(R.id.tabHost)
    TabLayout tabHost;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ViewPagerTaskAdapter taskAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task,container,false);
        ButterKnife.bind(this,view);
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();

    }
    private void init(){
        setupViewPager();
    }
    private void setupViewPager(){
        taskAdapter = new ViewPagerTaskAdapter(getChildFragmentManager());
        viewPager.setAdapter(taskAdapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        for(int i=0;i<taskAdapter.getCount();i++){

            tabHost.addTab(tabHost.newTab().setText(taskAdapter.getPageTitle(i)));
        }
        tabHost.setupWithViewPager(viewPager);
        tabHost.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public void onPause() {
        Log.d("AAA","task pause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("AAA","task stop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("AAA","task destroyview");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("AAA","task destroy");
        super.onDestroy();
    }
}
