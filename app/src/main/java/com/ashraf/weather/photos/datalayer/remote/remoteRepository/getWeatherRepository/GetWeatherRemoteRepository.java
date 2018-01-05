package com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository;

import com.ashraf.weather.photos.assets.constant.Constant;
import com.ashraf.weather.photos.datalayer.remote.api.ApiMethods;
import com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository.GetWeatherResponseDto;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class GetWeatherRemoteRepository {

    ApiMethods mApiMethods;

    @Inject
    public GetWeatherRemoteRepository(ApiMethods apiMethods) {
        mApiMethods = apiMethods;
    }

    public Single<GetWeatherResponseDto> getCurrentWeather() {
      return   mApiMethods.getCurrentWeather(Constant.COUNTRY, Constant.APP_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
