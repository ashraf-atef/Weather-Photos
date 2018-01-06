package com.ashraf.weather.photos.repositiory;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository.GetWeatherRemoteRepository;
import com.ashraf.weather.photos.datalayer.remote.remoteRepository.getWeatherRepository.GetWeatherResponseDto;
import com.ashraf.weather.photos.testApplication.TestApplication;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(AndroidJUnit4.class)
public class GetWeatherRemoteRepositoryTest {

    @Inject
    GetWeatherRemoteRepository getWeatherRemoteRepository;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        TestApplication testApplication
                = (TestApplication) instrumentation.getTargetContext().getApplicationContext();
        testApplication.getTestComponent().inject(this);
    }

    @Test
    public void getCurrentWeatherTest() {
        GetWeatherResponseDto getWeatherResponseDto = getWeatherRemoteRepository.getCurrentWeather().blockingGet();
        Assert.assertNotNull(getWeatherResponseDto);
        Assert.assertNotNull(getWeatherResponseDto.getTemperature());
        // we doesn't expect temp less than zero in egypt
        Assert.assertTrue(getWeatherResponseDto.getTemperature().getHumidity() > 0);
        Assert.assertTrue(getWeatherResponseDto.getTemperature().getPressure() > 0);
        Assert.assertTrue(getWeatherResponseDto.getTemperature().getTemp() > 0);
        Assert.assertTrue(getWeatherResponseDto.getTemperature().getTempMin() > 0);
        Assert.assertTrue(getWeatherResponseDto.getTemperature().getTempMax() > 0);

        Assert.assertNotNull(getWeatherResponseDto.getWeather());
        Assert.assertTrue(getWeatherResponseDto.getWeather().size() > 0);
        Assert.assertTrue(getWeatherResponseDto.getTemperature().getTempMax() > 0);
        Assert.assertNotNull(getWeatherResponseDto.getWeather().get(0).getDescription());
        Assert.assertTrue(getWeatherResponseDto.getWeather().get(0).getDescription().length() > 0);
        Assert.assertNotNull(getWeatherResponseDto.getWeather().get(0).getMain());
        Assert.assertTrue(getWeatherResponseDto.getWeather().get(0).getMain().length() > 0);
        Assert.assertTrue(getWeatherResponseDto.getWeather().get(0).getId() > 0);

    }
}
