package com.example.myapplication.room.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class MyList {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int cntItem;
}
