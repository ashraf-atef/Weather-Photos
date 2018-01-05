package com.ashraf.weather.photos.assets.ioc.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ashraf.weather.photos.datalayer.local.localdatabase.FoodDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreference(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    public FoodDatabase provideFoodDatabase(Context context) {
        return Room.databaseBuilder(context, FoodDatabase.class, "foodDB").build();
    }
}
