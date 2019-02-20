package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.myapplication.mainnote.MainNoteActivity;
import com.example.myapplication.room.ResponsitoryTask;
import com.example.myapplication.room.RespositoryMyList;
import com.example.myapplication.room.model.MyList;
import com.example.myapplication.room.model.Task;
import com.example.myapplication.roomdagger.AppModule;
import com.example.myapplication.roomdagger.DaggerRoomComponent;
import com.example.myapplication.roomdagger.RoomComponent;
import com.example.myapplication.util.Showlog;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    RespositoryMyList respositoryMyList;
    @Inject
    ResponsitoryTask responsitoryTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Intent intent = new Intent(this, MainNoteActivity.class);
        startActivity(intent);
    }
    private void init(){
//        DatabaseTask databaseTask = DatabaseTask.getInstance(this);
//
//        respositoryMyList = RespositoryMyList.getInstance(
//                LocalDataSourceMyList.getInstance(databaseTask.daoMyList()));


        RoomComponent roomComponent = DaggerRoomComponent.builder()
                .appModule(new AppModule(getApplication()))
                .build();
        roomComponent.inject(this);


        //insertList();
        //insertTask(1);
        getTask();
        getAllMyList();
    }
    private void insertList(){
        final MyList myList = new MyList("mylist");

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
    private void insertTask(int idList){
        Showlog.d("insert task idList: " + idList);
        Random random = new Random();
        final Task task = new Task.Builder().setTitle("title task " + random.nextInt()%10).createTask(idList);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                responsitoryTask.insertTask(task);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Showlog.d("insert task"+task.title+ " complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Showlog.d("insert task error: " + e.getMessage());

                    }
                });
    }
    private void getTask(){
        Disposable disposable = responsitoryTask.getTaskByIdList(2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws Exception {
                        Showlog.d("load task ac: " +tasks.size());
                        if(tasks.size()>0){
                            Showlog.d("task: " + tasks.get(0).title);
                        }
                    }
                });
        compositeDisposable.add(disposable);
    }
    private void getAllMyList(){
        Log.d("AAA","outside obs get");
        Disposable disposable = respositoryMyList.getAllList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<MyList>>() {
                    @Override
                    public void accept(List<MyList> myLists) throws Exception {
                        Log.d("AAA", "size list: " + myLists.size());

                        //TODO DEBUG
                        if(myLists.size()>0){
                            //insertTask(myLists.get(0).id);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("AAA","get error: " + throwable.getMessage());
                    }
                });

        compositeDisposable.add(disposable);
    }
    private void getTaskByMyList(int id){
        Disposable disposable = responsitoryTask.getTaskByIdList(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws Exception {
                        Showlog.d("getTask by id listt: " + tasks.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Showlog.d("error getTask by id list: " + throwable.getMessage());
                    }
                });

        compositeDisposable.add(disposable);
    }
}
