package com.example.myapplication.room.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.myapplication.room.model.MyList;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface DaoMyList {
    @Query("SELECT * FROM MyList")
    Flowable<List<MyList>> getMyList();

    @Insert
    void insertMyList(MyList... myLists);

    @Query("UPDATE MyList SET name=:newName WHERE id=:id ")
    void editNameMyList(int id,String newName);

    @Delete
    void removeMyList(MyList myList);
}
