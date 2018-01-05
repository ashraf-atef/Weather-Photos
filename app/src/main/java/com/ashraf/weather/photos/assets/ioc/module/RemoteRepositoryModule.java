package com.ashraf.weather.photos.assets.ioc.module;

import com.ashraf.weather.photos.datalayer.remote.api.ApiMethods;
import com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository.GetWeatherRemoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteRepositoryModule {

    @Provides
    @Singleton
    public GetWeatherRemoteRepository providesRemoteRepository(ApiMethods apiMethods) {
        return new GetWeatherRemoteRepository(apiMethods);
    }
}
