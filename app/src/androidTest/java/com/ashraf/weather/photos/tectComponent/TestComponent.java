package com.ashraf.weather.photos.tectComponent;

import com.ashraf.weather.photos.assets.ioc.module.NetModule;
import com.ashraf.weather.photos.assets.ioc.module.RemoteRepositoryModule;
import com.ashraf.weather.photos.assets.ioc.module.UseCasesModule;
import com.ashraf.weather.photos.assets.ioc.module.UtilitesModule;
import com.ashraf.weather.photos.repositiory.GetWeatherRemoteRepositoryTest;
import com.ashraf.weather.photos.usecase.GetWeatherUseCaseTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NetModule.class,
        RemoteRepositoryModule.class,
        UtilitesModule.class,
        UseCasesModule.class
      })
public interface TestComponent {
    void inject(GetWeatherRemoteRepositoryTest getWeatherRemoteRepositoryTest);
    void inject(GetWeatherUseCaseTest getWeatherUseCaseTest);
}
