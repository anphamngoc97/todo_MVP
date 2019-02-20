package com.example.myapplication.mylist;

import com.example.myapplication.room.model.MyList;
import com.example.myapplication.util.Showlog;

import java.util.ArrayList;

public class MylistPresenterImpl implements MylistContract.Presenter,
        MylistContract.Iterator.OnFinishListener<ArrayList<MyList>,MyList> {

    MylistContract.View view;
    MylistContract.Iterator iterator;

    public MylistPresenterImpl(MylistContract.View view, MylistContract.Iterator iterator) {
        setView(view);
        this.iterator = iterator;
    }

    @Override
    public void setView(MylistContract.View view) {
        this.view = view;
    }

    @Override
    public void onGetSuccess(ArrayList<MyList> res) {
        view.refreshList(res);
        for(MyList t:res){
            Showlog.d("mylist: " + t.name);
        }
    }

    @Override
    public void onInsertSuccess(MyList object) {
        view.refreshInsert(object);
    }


    @Override
    public void onRemoveSuccess(int position) {

    }

    @Override
    public void onFailture(Throwable throwable) {

    }

    @Override
    public void getData() {
        iterator.getData(this);
    }

    @Override
    public void insertMyList(String name) {
        MyList myList = new MyList(name);
        iterator.insertData(myList,this);
    }

    @Override
    public void removeDetail(int position) {
        view.startDetailActivity(position);
    }

    @Override
    public void onClickListItem(int position) {
        view.startDetailActivity(position);
    }
}
