package com.example.e_bookrecordkeeper.di;

import android.app.Application;

import com.example.e_bookrecordkeeper.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class RepositoryModule {

    @Provides
    Repository providesRepository(Application application)
    {
        return new Repository(application);
    }
}
