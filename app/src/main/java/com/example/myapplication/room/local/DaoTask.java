package com.example.myapplication.room.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.myapplication.room.model.Task;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface DaoTask {


    @Query("SELECT * FROM Task")
    Flowable<List<Task>> getAllTask();

    @Query("SELECT * FROM Task WHERE idList=:idList")
    Flowable<List<Task>> getTaskByIdList(int idList);

    @Insert
    void insertTask(Task... task);

    @Query("UPDATE Task SET idList=:idList, title=:title,tag=:tag,dateAlarm=:dateAlarm,subTask=:subTask," +
            "typeList=:typeList, isComplete=:isComplete, isAlarm=:isAlarm WHERE id=:id")
    void editTask(int id,int idList, String title, String tag, String dateAlarm, String subTask, String typeList,
                  boolean isComplete, boolean isAlarm);

    @Delete
    void removeTask(Task... tasks);

}
