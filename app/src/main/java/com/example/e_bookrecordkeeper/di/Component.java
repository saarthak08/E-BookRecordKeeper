package com.example.e_bookrecordkeeper.di;

import com.example.e_bookrecordkeeper.view.MainActivity;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {ApplicationModule.class,RepositoryModule.class})
public interface Component {

    void inject(MainActivity mainActivity);
}
