package com.example.myapplication.tag;

import com.example.myapplication.task.TaskContract;

public class TagPresenterImpl implements TaskContract.Presenter {
    TaskContract.View view;
    @Override
    public void setView(TaskContract.View view) {
        this.view = view;
    }
}
