package com.example.myapplication.roomdagger;

import android.app.Application;
import android.util.Log;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application getApplication(){
        Log.d("AAA","provide appl");
        return this.application;
    }
}
