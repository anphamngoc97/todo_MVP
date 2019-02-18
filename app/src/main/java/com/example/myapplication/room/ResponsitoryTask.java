package com.example.myapplication.room;

import com.example.myapplication.room.model.Task;

import java.util.List;

import io.reactivex.Flowable;

public class ResponsitoryTask implements DataSourceTask {
    private  static ResponsitoryTask sResponsitoryTask;

    public static ResponsitoryTask getInstance(DataSourceTask dataSourceTask) {
        if(sResponsitoryTask == null){
            sResponsitoryTask = new ResponsitoryTask(dataSourceTask);
        }
        return sResponsitoryTask;
    }

    DataSourceTask dataSourceTask;

    public ResponsitoryTask(DataSourceTask dataSourceTask) {
        this.dataSourceTask = dataSourceTask;
    }



    @Override
    public Flowable<List<Task>> getTaskByIdList(int id) {
        return dataSourceTask.getTaskByIdList(id);
    }

    @Override
    public void insertTask(Task... task) {
        dataSourceTask.insertTask(task);
    }

    @Override
    public void editTask(int id,int idList, String title, String tag, String dateAlarm, String subTask, String typeList,
                         boolean isComplete, boolean isAlarm) {
        dataSourceTask.editTask(id,idList,title,tag,dateAlarm,subTask,typeList,isComplete,isAlarm);
    }

    @Override
    public void removeTask(Task... tasks) {
        dataSourceTask.removeTask(tasks);
    }
}
