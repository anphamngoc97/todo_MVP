package com.example.myapplication.room.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static android.arch.persistence.room.ForeignKey.CASCADE;

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

}
