package com.example.myapplication.task;

import com.example.myapplication.BasePresenter;
import com.example.myapplication.BaseView;

public interface TaskContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
