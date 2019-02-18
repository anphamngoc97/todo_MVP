package com.example.myapplication.mylist;

import com.example.myapplication.BaseIterator;
import com.example.myapplication.BasePresenter;
import com.example.myapplication.BaseView;
import com.example.myapplication.room.model.MyList;

import java.util.ArrayList;

public interface MylistContract {
    interface View extends BaseView{
        void refreshList(ArrayList<MyList> myList);
        void startDetailActivity(int position);
    }
    interface Presenter extends BasePresenter<View>{
        void getData();
        void addMyList(String name);
        void moveToDetail(int position);
    }
    interface Iterator extends BaseIterator{

    }
}
