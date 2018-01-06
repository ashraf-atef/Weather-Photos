package com.ashraf.weather.photos.assets.ioc.component;

import com.ashraf.weather.photos.assets.application.WeatherPhotoApplication;
import com.ashraf.weather.photos.assets.ioc.module.UiHelperModule;
import com.ashraf.weather.photos.assets.ioc.module.UseCasesModule;
import com.ashraf.weather.photos.datalayer.remote.errorConsumer.BaseErrorConsumer;
import com.ashraf.weather.photos.presentationlayer.activity.baseActivity.BaseActivity;
import com.ashraf.weather.photos.presentationlayer.activity.weatherActivity.WeatherActivityPresenter;
import com.ashraf.weather.photos.presentationlayer.base.BasePresenterDependency;
import com.ashraf.weather.photos.assets.ioc.module.AppModule;
import com.ashraf.weather.photos.assets.ioc.module.NetModule;
import com.ashraf.weather.photos.assets.ioc.module.RemoteRepositoryModule;
import com.ashraf.weather.photos.assets.ioc.module.UtilitesModule;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        RemoteRepositoryModule.class,
        UtilitesModule.class,
        UiHelperModule.class,
        UseCasesModule.class})
public interface AppComponent {
    void inject(WeatherPhotoApplication weatherPhotoApplication);
    void inject(BaseActivity baseActivity);
    void inject(BaseErrorConsumer baseErrorConsumer);
    void inject(BasePresenterDependency basePresenterDependency);
    void inject(WeatherActivityPresenter weatherActivityPresenter);

}
