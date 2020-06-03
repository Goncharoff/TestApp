package com.goncharoff.testapp;

import android.app.Application;

import com.goncharoff.testapp.repository.UserRepository;

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UserRepository.init(this);
    }
}
