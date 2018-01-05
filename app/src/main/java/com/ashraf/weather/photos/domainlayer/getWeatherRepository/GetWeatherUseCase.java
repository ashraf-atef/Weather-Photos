package com.ashraf.weather.photos.domainlayer.getWeatherRepository;

import com.ashraf.weather.photos.datalayer.remote.errorConsumer.BaseErrorConsumer;
import com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository.GetWeatherRemoteRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetWeatherUseCase {

    GetWeatherRemoteRepository mGetWeatherRemoteRepository;

    @Inject
    public GetWeatherUseCase(GetWeatherRemoteRepository mGetWeatherRemoteRepository) {
        this.mGetWeatherRemoteRepository = mGetWeatherRemoteRepository;
    }

    public Single<CurrentWeather> execute() {
        return mGetWeatherRemoteRepository.getCurrentWeather()
                .map(weather ->
                        CurrentWeather.fromWeatherResponse(weather)
                );
    }

    public abstract class GetWeatherErrorConsumer extends BaseErrorConsumer {
        @Override
        public void onUnHandledError(Throwable e) {
            onUnknownError(e);
        }
    }
}
