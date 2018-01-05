package com.ashraf.weather.photos.datalayer.remote.api;


import com.ashraf.weather.photos.assets.constant.Constant;
import com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository.GetWeatherResponseDto;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiMethods {

    @GET("/data/2.5/weather")
    Single<GetWeatherResponseDto> getCurrentWeather(
            @Query("q") String country,
            @Query(Constant.APP_ID_KEY) String appId
    );
}

