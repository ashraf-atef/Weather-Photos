package com.ashraf.weather.photos.assets.ioc.module;

import com.ashraf.weather.photos.assets.constant.Utilities;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilitesModule {
    @Provides
    @Singleton
    public Utilities providesUtilites() {
        return new Utilities();
    }
}
