package com.example.myapplication.task;

public class TaskPresenterImpl implements TaskContract.Presenter {

    TaskContract.View view;
    @Override
    public void setView(TaskContract.View view) {
        this.view = view;

    }
}
