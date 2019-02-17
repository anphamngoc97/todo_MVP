package com.example.myapplication.util;

import com.example.myapplication.BaseIterator;
import com.example.myapplication.room.RespositoryMyList;
import com.example.myapplication.room.model.MyList;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Iterator {
    private static CompositeDisposable compositeDisposable = new CompositeDisposable();
    public static void getAllMylist(RespositoryMyList respositoryMyList,
                                          final BaseIterator.OnFinishListener onFinishListener){
        Disposable disposable = respositoryMyList.getAllList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<MyList>>() {
                    @Override
                    public void accept(List<MyList> myLists) throws Exception {

                        Showlog.d("iterator get all list success " + myLists.size());
                        onFinishListener.onSuccess(myLists);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Showlog.d("iterator get all list fail " + throwable.getMessage());
                        onFinishListener.onFailture(throwable);
                    }
                });


        compositeDisposable.add(disposable);
    }

}
