package com.example.myapplication.calender;

import com.example.myapplication.BasePresenter;
import com.example.myapplication.BaseView;

public interface CalenderContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
