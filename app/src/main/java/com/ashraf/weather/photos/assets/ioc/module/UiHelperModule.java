package com.ashraf.weather.photos.assets.ioc.module;

import com.ashraf.weather.photos.presentationlayer.uiHelper.BitmapHelper;
import com.ashraf.weather.photos.presentationlayer.uiHelper.UiHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UiHelperModule {
    @Provides
    @Singleton
    public UiHelper provideUiHelper() {
        return new UiHelper();
    }

    @Provides
    @Singleton
    public BitmapHelper provideBitmapHelper() {
        return new BitmapHelper();
    }
}
