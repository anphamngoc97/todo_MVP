package com.example.myapplication.room.model;

import android.arch.persistence.room.Entity;

@Entity
public enum Tag {
    PRIORITY,
    IMPORTANT,
    IN_PROGRESS,
    DEADLINE,
    FAMILY,
    TRACKBACK,
    WORK;


    public String name = this.name();
}
