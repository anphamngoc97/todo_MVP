package com.example.myapplication.room.local;

import com.example.myapplication.room.DataSourceTask;
import com.example.myapplication.room.model.MyList;
import com.example.myapplication.room.model.Task;

import java.util.List;

import io.reactivex.Flowable;

public class LocalDataSourceTask implements DataSourceTask {
    private static LocalDataSourceTask sLocalDataSourceTask;

    public static LocalDataSourceTask getInstance(DaoTask daoTask) {
        if(sLocalDataSourceTask == null){
            sLocalDataSourceTask = new LocalDataSourceTask(daoTask);
        }
        return sLocalDataSourceTask;
    }

    DaoTask daoTask;

    public LocalDataSourceTask(DaoTask daoTask) {
        this.daoTask = daoTask;
    }

    @Override
    public Flowable<List<Task>> getTaskByNameList(String nameList) {
        return daoTask.getTaskByNameList(nameList);
    }

    @Override
    public void insertTask(Task... task) {
        daoTask.insertTask(task);
    }

    @Override
    public void editTask(int id,int idList, String title, String tag, String dateAlarm, String subTask, String typeList,
                         boolean isComplete, boolean isAlarm) {
        daoTask.editTask(id,idList,title,tag,dateAlarm,subTask,typeList,isComplete,isAlarm);
    }

    @Override
    public void removeTask(Task... tasks) {

    }
}
