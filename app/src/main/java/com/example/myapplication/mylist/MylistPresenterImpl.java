package com.example.myapplication.mylist;

import com.example.myapplication.room.model.MyList;
import com.example.myapplication.util.Showlog;

import java.util.ArrayList;

public class MylistPresenterImpl implements MylistContract.Presenter,
        MylistContract.Iterator.OnFinishListener<ArrayList<MyList>> {

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
    public void onSuccess(ArrayList<MyList> res) {
        view.refreshList(res);
        for(MyList t:res){
            Showlog.d("mylist: " + t.name);
        }
    }

    @Override
    public void onFailture(Throwable throwable) {

    }

    @Override
    public void getData() {
        iterator.getData(this);
    }

    @Override
    public void addMyList(String name) {

    }

    @Override
    public void moveToDetail(int position) {
        view.startDetailActivity(position);
    }
}
