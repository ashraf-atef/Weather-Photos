package com.ashraf.weather.photos.datalayer.remote.errorConsumer;

import com.ashraf.weather.photos.assets.application.WeatherPhotoApplication;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;

public abstract class BaseErrorConsumer implements Consumer<Throwable> {

    public BaseErrorConsumer() {
        WeatherPhotoApplication.getAppComponent().inject(this);
    }

    @Override
    public void accept(Throwable e) throws Exception {
        if (e instanceof UnknownHostException || e instanceof ConnectException || e instanceof SocketTimeoutException) {
            onConnectionError(e);
        }  else {
            onUnHandledError(e);
        }
    }

    public abstract void onUnHandledError(Throwable e);

    public abstract void onUnknownError(Throwable e);

    public abstract void onConnectionError(Throwable e);

}
