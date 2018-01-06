package com.ashraf.weather.photos.presentationlayer.activity.weatherActivity;

import com.ashraf.weather.photos.assets.application.WeatherPhotoApplication;
import com.ashraf.weather.photos.domainlayer.getWeatherRepository.GetWeatherUseCase;

import javax.inject.Inject;

public class WeatherActivityPresenter extends WeatherActivityContract.Presenter {

    @Inject
    GetWeatherUseCase getWeatherUseCase;

    protected WeatherActivityPresenter(WeatherActivityContract.View view) {
        super(view);
        WeatherPhotoApplication.getAppComponent().inject(this);
    }

    @Override
    public void start() {
        getCurrentWeather();
    }

    @Override
    public void getCurrentWeather() {
        view.showProgress();
        view.hideRetryButton();
        getWeatherUseCase.execute().subscribe(
                currentWeather -> {
                    view.onGetCurrentWeather(currentWeather);
                    view.hideProgress();
                },
                getWeatherUseCase.new GetWeatherErrorConsumer() {
                    @Override
                    public void onUnknownError(Throwable e) {
                        view.onError();
                        view.showRetryButton();
                        view.hideProgress();
                    }

                    @Override
                    public void onConnectionError(Throwable e) {
                        view.onConnectionError();
                        view.showRetryButton();
                        view.hideProgress();
                    }
                });
    }

}
