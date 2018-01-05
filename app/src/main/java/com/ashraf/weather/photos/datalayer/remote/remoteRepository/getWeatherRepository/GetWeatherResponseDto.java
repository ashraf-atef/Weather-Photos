package com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWeatherResponseDto {

    @SerializedName("main")
    Temperature temperature;
    List<Weather> weather;

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

}
