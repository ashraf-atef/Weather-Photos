package com.ashraf.weather.photos.assets.ioc.module;

import com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository.GetWeatherRemoteRepository;
import com.ashraf.weather.photos.domainlayer.getWeatherRepository.GetWeatherUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCasesModule {

    @Provides
    public GetWeatherUseCase provideGetWeatherUseCase(GetWeatherRemoteRepository getWeatherRemoteRepository) {
        return new GetWeatherUseCase(getWeatherRemoteRepository);
    }

}
