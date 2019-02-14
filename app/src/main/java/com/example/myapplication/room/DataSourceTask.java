package com.example.myapplication.room;

import android.arch.persistence.room.Query;

import com.example.myapplication.room.model.MyList;
import com.example.myapplication.room.model.Task;

import java.util.List;

import io.reactivex.Flowable;

public interface DataSourceTask {

    Flowable<List<Task>> getTaskByNameList(String nameList);

    void insertTask(Task... task);

    void editTask(int id,int idList, String title, String tag, String dateAlarm, String subTask, String typeList,
                  boolean isComplete, boolean isAlarm);

    void removeTask(Task... tasks);

}
