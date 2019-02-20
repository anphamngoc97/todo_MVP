package com.example.myapplication;

public interface BaseIterator {
    interface OnFinishListener<T,sT>{
        void onGetSuccess(T res);
        void onInsertSuccess(sT object);
        void onRemoveSuccess(int position);
        void onFailture(Throwable throwable);
    }
    void getData(OnFinishListener onFinishListener);

}
