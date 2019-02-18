package com.example.myapplication.room.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = MyList.class,
                parentColumns = "id",
                childColumns = "idList",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)})
public class Task {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int idList;

    public String title;
    public String tag;
    public String dateAlarm;
    public String subTask;
    public String createDate;
    public String typeList;
    public boolean isComplete;
    public boolean isAlarm;

    public Task(int idList, String title, String tag, String dateAlarm, String subTask, String createDate, String typeList, boolean isComplete, boolean isAlarm) {
        this.idList = idList;
        this.title = title;
        this.tag = tag;
        this.dateAlarm = dateAlarm;
        this.subTask = subTask;
        this.createDate = createDate;
        this.typeList = typeList;
        this.isComplete = isComplete;
        this.isAlarm = isAlarm;
    }

    public static class Builder{

        private int idList;
        private String title;
        private String tag;
        private String dateAlarm;
        private String subTask;
        private String createDate;
        private String typeList = "";
        private boolean isComplete = false;
        private boolean isAlarm = false;


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder setDateAlarm(String dateAlarm) {
            this.dateAlarm = dateAlarm;
            return this;
        }

        public Builder setSubTask(String subTask) {
            this.subTask = subTask;
            return this;
        }


        public Builder setTypeList(String typeList) {
            this.typeList = typeList;
            return this;
        }

        public Builder setIsComplete(boolean isComplete) {
            this.isComplete = isComplete;
            return this;
        }

        public Builder setIsAlarm(boolean isAlarm) {
            this.isAlarm = isAlarm;
            return this;
        }

        public Task createTask(int _idList) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date date = new Date();
            createDate = simpleDateFormat.format(date);
            this.idList = _idList;
            return new Task(idList, title, tag, dateAlarm, subTask, createDate, typeList, isComplete, isAlarm);
        }
    }


}
