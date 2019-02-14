package com.example.myapplication.roomdagger;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.myapplication.room.ResponsitoryTask;
import com.example.myapplication.room.RespositoryMyList;
import com.example.myapplication.room.local.DaoMyList;
import com.example.myapplication.room.local.DaoTask;
import com.example.myapplication.room.local.DatabaseTask;
import com.example.myapplication.room.local.LocalDataSourceMyList;
import com.example.myapplication.room.local.LocalDataSourceTask;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {AppModule.class})
public class RoomModule {


//    public RoomModule(Application application){
//        databaseTask = Room.databaseBuilder(
//                application.getApplicationContext(),
//                DatabaseTask.class,DatabaseTask.DB_NAME
//        )
//                .build();
//
//    }

    @Provides
    @Singleton
    public DatabaseTask provideDatabaseTask(Application application){
        DatabaseTask databaseTask = Room.databaseBuilder(
                application.getApplicationContext(),
                DatabaseTask.class,DatabaseTask.DB_NAME
        )
                .build();

        return databaseTask;
    }


//    @Provides
//    @Singleton
//    public DatabaseTask provideDatabaseTask(){
//        return databaseTask;
//    }

    @Provides
    @Singleton
    public DaoMyList provideDaoMyList(DatabaseTask databaseTask){
        return databaseTask.daoMyList();
    }

    @Provides
    @Singleton
    public LocalDataSourceMyList provideLocalDataSourceMyList(DaoMyList daoMyList){
        return new LocalDataSourceMyList(daoMyList);
    }
    @Provides
    @Singleton
    public RespositoryMyList provideRespositoryMyList(LocalDataSourceMyList localDataSourceMyList){
        return new RespositoryMyList(localDataSourceMyList);
    }

    @Provides
    @Singleton
    public DaoTask provideDaoTask(DatabaseTask databaseTask){
        return databaseTask.daoTask();
    }
//    @Provides
//    @Singleton
//    public LocalDataSourceTask provideLocalDataSourceTask(DaoTask daoTask){
//        return new LocalDataSourceTask(daoTask);
//    }
//    @Provides
//    @Singleton
//    ResponsitoryTask provideResponsitoryTask(LocalDataSourceTask localDataSourceTask){
//        return new ResponsitoryTask(localDataSourceTask);
//    }

}
