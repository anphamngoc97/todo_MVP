package com.example.myapplication.room;

import com.example.myapplication.room.model.MyList;
import com.example.myapplication.room.model.Task;

import java.util.List;

import io.reactivex.Flowable;

public interface DataSourceMyList {
    Flowable<List<MyList>> getAllList();

    void insertMyList(MyList myLists);

    void editNameMyList(int id,String newName);

    void removeMyList(MyList myList);
}
