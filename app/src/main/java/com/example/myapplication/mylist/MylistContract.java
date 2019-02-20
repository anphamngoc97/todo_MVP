package com.example.myapplication.mylist;

import com.example.myapplication.BaseIterator;
import com.example.myapplication.BasePresenter;
import com.example.myapplication.BaseView;
import com.example.myapplication.room.model.MyList;

import java.util.ArrayList;

public interface MylistContract {
    interface View extends BaseView{
        void refreshList(ArrayList<MyList> myList);
        void refreshInsert(MyList myList);
        void refreshRemove(int sposition);
        void startDetailActivity(int position);
    }
    interface Presenter extends BasePresenter<View>{
        void getData();
        void insertMyList(String name);
        void removeDetail(int position);
        void onClickListItem(int position);
    }
    interface Iterator extends BaseIterator{
        void insertData(MyList myList,OnFinishListener onFinishListener);
        void updateData(MyList myList,OnFinishListener onFinishListener);
        void deleteData(MyList myList,OnFinishListener onFinishListener);
    }
}
