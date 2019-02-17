package com.example.myapplication.mylist;

import com.example.myapplication.room.RespositoryMyList;
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

}
