package com.example.myapplication.mainnote;

import com.example.myapplication.BasePresenter;
import com.example.myapplication.BaseView;

public interface MainNoteContract {
    interface View extends BaseView {
        void changeTab(int position);
    }
    interface Presenter extends BasePresenter<View> {

    }
}
