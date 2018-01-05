package com.ashraf.weather.photos.domainlayer.getWeatherRepository;

import com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository.GetWeatherResponseDto;
import com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository.Weather;

public class CurrentWeather {

    float temperature;
    String description;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static CurrentWeather fromWeatherResponse(GetWeatherResponseDto getWeatherResponseDto) {
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setTemperature(getWeatherResponseDto.getTemperature().getTemp());
        if (getWeatherResponseDto.getWeather().size() > 0) {
            currentWeather.setDescription(getWeatherResponseDto.getWeather().get(0).getDescription());
        }
        return currentWeather;
    }
}
