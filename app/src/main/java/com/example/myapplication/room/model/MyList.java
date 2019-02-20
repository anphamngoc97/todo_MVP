package com.example.myapplication.room.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class MyList implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int cntItem = 0;

    public MyList(String name) {
        this.name = name;
    }
}
