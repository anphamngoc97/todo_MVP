package com.example.myapplication.util;

import com.example.myapplication.BaseIterator;
import com.example.myapplication.room.RespositoryMyList;
import com.example.myapplication.room.model.MyList;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
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
                        onFinishListener.onGetSuccess(myLists);
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
    public static void insertMylist(final RespositoryMyList respositoryMyList,
                                    final BaseIterator.OnFinishListener onFinishListener, final MyList mylist){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                respositoryMyList.insertMyList(mylist);
            }
        })
        .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Showlog.d("itetator insert complete");
                        onFinishListener.onInsertSuccess(mylist);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Showlog.d("iterator insert fail: " + e.getMessage());
                    }
                });
    }
}
