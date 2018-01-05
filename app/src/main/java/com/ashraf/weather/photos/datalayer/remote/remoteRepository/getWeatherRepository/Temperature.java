package com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    float temp;
    int pressure;
    int humidity;
    @SerializedName("temp_min")
    float tempMin;
    @SerializedName("temp_max")
    float tempMax;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }
}
