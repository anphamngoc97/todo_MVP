package com.example.myapplication.room.local;

import com.example.myapplication.room.DataSourceMyList;
import com.example.myapplication.room.model.MyList;
import com.example.myapplication.room.model.Task;

import java.util.List;

import io.reactivex.Flowable;

public class LocalDataSourceMyList implements DataSourceMyList {
    private static LocalDataSourceMyList sLocalDataSourceMyList;

    public static final LocalDataSourceMyList getInstance(DaoMyList daoMyList){
        if(sLocalDataSourceMyList == null){
            sLocalDataSourceMyList = new LocalDataSourceMyList(daoMyList);
        }
        return sLocalDataSourceMyList;
    }

    private DaoMyList daoMyList;

    public LocalDataSourceMyList(DaoMyList daoMyList) {
        this.daoMyList = daoMyList;
    }

    @Override
    public Flowable<List<MyList>> getAllList() {
        return daoMyList.getMyList();
    }

    @Override
    public void insertMyList(MyList myLists) {
        daoMyList.insertMyList(myLists);
    }

    @Override
    public void editNameMyList(int id, String newName) {
        daoMyList.editNameMyList(id,newName);
    }

    @Override
    public void removeMyList(MyList myList) {
        daoMyList.removeMyList(myList);
    }
}
