package com.ashraf.weather.photos.presentationlayer.base;

public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);
}
