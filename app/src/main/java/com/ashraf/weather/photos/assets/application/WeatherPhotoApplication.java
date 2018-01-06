package com.ashraf.weather.photos.assets.application;

import android.app.Application;

import com.ashraf.weather.photos.assets.constant.Constant;
import com.ashraf.weather.photos.assets.ioc.component.AppComponent;
import com.ashraf.weather.photos.assets.ioc.component.DaggerAppComponent;
import com.ashraf.weather.photos.assets.ioc.module.AppModule;
import com.ashraf.weather.photos.assets.ioc.module.NetModule;
import com.ashraf.weather.photos.assets.ioc.module.RemoteRepositoryModule;
import com.ashraf.weather.photos.assets.ioc.module.UiHelperModule;
import com.ashraf.weather.photos.assets.ioc.module.UseCasesModule;
import com.ashraf.weather.photos.assets.ioc.module.UtilitesModule;



public class WeatherPhotoApplication extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Constant.API_URL))
                .utilitesModule(new UtilitesModule())
                .remoteRepositoryModule(new RemoteRepositoryModule())
                .uiHelperModule(new UiHelperModule())
                .useCasesModule(new UseCasesModule())
                .build();

    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
