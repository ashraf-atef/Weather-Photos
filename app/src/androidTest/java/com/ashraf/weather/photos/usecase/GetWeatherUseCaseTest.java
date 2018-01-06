package com.ashraf.weather.photos.usecase;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import com.ashraf.weather.photos.domainlayer.getWeatherRepository.CurrentWeather;
import com.ashraf.weather.photos.domainlayer.getWeatherRepository.GetWeatherUseCase;
import com.ashraf.weather.photos.testApplication.TestApplication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

public class GetWeatherUseCaseTest {
    @Inject
    GetWeatherUseCase getWeatherUseCase;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        TestApplication testApplication
                = (TestApplication) instrumentation.getTargetContext().getApplicationContext();
        testApplication.getTestComponent().inject(this);
    }

    @Test
    public void getCurrentWeatherTest() {
        CurrentWeather currentWeather = getWeatherUseCase.execute().blockingGet();
        Assert.assertNotNull(currentWeather);
        Assert.assertTrue(currentWeather.getTemperature() > 0);
        Assert.assertNotNull(currentWeather.getDescription());
        Assert.assertTrue(currentWeather.getDescription().length() >0);
    }

}
