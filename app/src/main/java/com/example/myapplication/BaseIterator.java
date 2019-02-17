package com.example.myapplication;

public interface BaseIterator {
    interface OnFinishListener<T>{
        void onSuccess(T res);
        void onFailture(Throwable throwable);
    }
    void getData(OnFinishListener onFinishListener);
}
