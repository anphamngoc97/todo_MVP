package com.example.myapplication.mylist;

import com.example.myapplication.room.RespositoryMyList;
import com.example.myapplication.room.model.MyList;
import com.example.myapplication.util.Iterator;

public class MylistIteratorImpl implements MylistContract.Iterator {
    RespositoryMyList respositoryMyList;

    public MylistIteratorImpl(RespositoryMyList respositoryMyList) {
        this.respositoryMyList = respositoryMyList;
    }

    @Override
    public void getData(OnFinishListener onFinishListener) {
        Iterator.getAllMylist(respositoryMyList,onFinishListener);

    }


    @Override
    public void insertData(MyList myList, OnFinishListener onFinishListener) {
        Iterator.insertMylist(respositoryMyList,onFinishListener,myList);
    }

    @Override
    public void updateData(MyList myList, OnFinishListener onFinishListener) {

    }

    @Override
    public void deleteData(MyList myList, OnFinishListener onFinishListener) {

    }
}
