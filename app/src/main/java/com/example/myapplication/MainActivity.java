package com.example.myapplication;

import android.arch.persistence.room.Database;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.room.DataSourceMyList;
import com.example.myapplication.room.RespositoryMyList;
import com.example.myapplication.room.local.DatabaseTask;
import com.example.myapplication.room.local.LocalDataSourceMyList;
import com.example.myapplication.room.model.MyList;

import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RespositoryMyList respositoryMyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        DatabaseTask databaseTask = DatabaseTask.getInstance(this);

        respositoryMyList = RespositoryMyList.getInstance(
                LocalDataSourceMyList.getInstance(databaseTask.daoMyList()));


        //insertList();
        getAllMyList();
    }
    private void insertList(){
        Random random = new Random(1000);
        final MyList myList = new MyList();
        myList.cntItem=random.nextInt()%10;
        myList.name="mylist"+myList.cntItem;

        Log.d("AAA","outside obse insert");
        Disposable disposable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                respositoryMyList.insertMyList(myList);
                emitter.onComplete();
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("AAA", "insert ac");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("AAA", "insert arr " + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("AAA","insert run");
                    }
                });
        compositeDisposable.add(disposable);
    }
    private void getAllMyList(){
        Log.d("AAA","outside obs get");
        Disposable disposable = respositoryMyList.getAllList()
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<List<MyList>>() {
                    @Override
                    public void accept(List<MyList> myLists) throws Exception {
                        Log.d("AAA", "size list: " + myLists.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("AAA","get error: " + throwable.getMessage());
                    }
                });

        Log.d("AAA","top composite");
        compositeDisposable.add(disposable);
        Log.d("AAA","bottom composite");
    }
}
