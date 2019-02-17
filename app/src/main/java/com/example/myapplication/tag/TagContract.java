package com.example.myapplication.tag;

import com.example.myapplication.BasePresenter;
import com.example.myapplication.BaseView;

public interface TagContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }
}
