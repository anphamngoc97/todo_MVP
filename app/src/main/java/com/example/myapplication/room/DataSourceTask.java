package com.example.myapplication.room;

import com.example.myapplication.room.model.Task;

import java.util.List;

import io.reactivex.Flowable;

public interface DataSourceTask {

    Flowable<List<Task>> getTaskByIdList(int id);

    void insertTask(Task... task);

    void editTask(int id,int idList, String title, String tag, String dateAlarm, String subTask, String typeList,
                  boolean isComplete, boolean isAlarm);

    void removeTask(Task... tasks);

}
