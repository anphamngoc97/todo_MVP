package com.example.myapplication.mainnote;

public class MainNotePresenterImpl implements MainNoteContract.Presenter {
    MainNoteContract.View view;
    @Override
    public void setView(MainNoteContract.View view) {
        this.view = view;
    }

}
