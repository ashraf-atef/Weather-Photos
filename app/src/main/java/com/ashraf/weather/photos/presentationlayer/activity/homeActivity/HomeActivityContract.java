package com.ashraf.weather.photos.presentationlayer.activity.homeActivity;

import com.ashraf.weather.photos.presentationlayer.base.BasePresenter;
import com.ashraf.weather.photos.presentationlayer.base.BaseView;


public interface HomeActivityContract {

    interface View extends BaseView<Presenter> {

    }

    abstract class Presenter extends BasePresenter<HomeActivityContract.View> {
        public Presenter(View view) {
            super(view);
        }

    }
}
