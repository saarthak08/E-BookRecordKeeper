package com.example.e_bookrecordkeeper.di;

import com.example.e_bookrecordkeeper.MainActivity;

@dagger.Component(modules = {ApplicationModule.class,RepositoryModule.class})
public interface Component {

    void inject(MainActivity mainActivity);
}
