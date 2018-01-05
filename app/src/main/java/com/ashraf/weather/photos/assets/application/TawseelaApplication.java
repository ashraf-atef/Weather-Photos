package com.ashraf.weather.photos.assets.application;

import android.app.Application;

import com.ashraf.weather.photos.R;
import com.ashraf.weather.photos.assets.constant.Constant;
import com.ashraf.weather.photos.assets.ioc.component.AppComponent;
import com.ashraf.weather.photos.assets.ioc.component.DaggerAppComponent;
import com.ashraf.weather.photos.assets.ioc.module.AppModule;
import com.ashraf.weather.photos.assets.ioc.module.NetModule;
import com.ashraf.weather.photos.assets.ioc.module.RemoteRepositoryModule;
import com.ashraf.weather.photos.assets.ioc.module.StorageModule;
import com.ashraf.weather.photos.assets.ioc.module.UiHelperModule;
import com.ashraf.weather.photos.assets.ioc.module.UseCasesModule;
import com.ashraf.weather.photos.assets.ioc.module.UtilitesModule;


import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class TawseelaApplication extends Application {

    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(Constant.API_URL))
                .storageModule(new StorageModule())
                .utilitesModule(new UtilitesModule())
                .remoteRepositoryModule(new RemoteRepositoryModule())
                .uiHelperModule(new UiHelperModule())
                .useCasesModule(new UseCasesModule())
                .build();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.regular_font_path))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
