package com.example.myapplication.room;

import com.example.myapplication.room.model.MyList;
import com.example.myapplication.room.model.Task;

import java.util.List;

import io.reactivex.Flowable;

public class RespositoryMyList implements DataSourceMyList{

    private static RespositoryMyList sRespositoryMyList;


    public static RespositoryMyList getInstance(DataSourceMyList dataSourceMyList){
        if(sRespositoryMyList == null){
            sRespositoryMyList = new RespositoryMyList(dataSourceMyList);
        }
        return sRespositoryMyList;
    }

    DataSourceMyList dataSourceMyList;

    public RespositoryMyList(DataSourceMyList dataSourceMyList){
        this.dataSourceMyList = dataSourceMyList;
    }

    @Override
    public Flowable<List<MyList>> getAllList() {
        return dataSourceMyList.getAllList();
    }

    @Override
    public void insertMyList(MyList myLists) {
        dataSourceMyList.insertMyList(myLists);

    }

    @Override
    public void editNameMyList(int id, String newName) {
        dataSourceMyList.editNameMyList(id,newName);
    }

    @Override
    public void removeMyList(MyList myList) {
        dataSourceMyList.removeMyList(myList);
    }
}
