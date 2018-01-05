package com.ashraf.weather.photos.presentationlayer.activity.weatherActivity;

        import com.ashraf.weather.photos.domainlayer.getWeatherRepository.CurrentWeather;
        import com.ashraf.weather.photos.presentationlayer.base.BasePresenter;
        import com.ashraf.weather.photos.presentationlayer.base.BaseView;

public interface WeatherActivityContract {

    interface View extends BaseView<Presenter> {
        void onConnectionError();
        void onError();
        void onGetCurrentWeather(CurrentWeather currentWeather);
        void showProgress();
        void hideProgress();
        void showRetryButton();
        void hideRetryButton();
    }

     abstract class Presenter extends BasePresenter<View> {

        protected Presenter(View view) {
            super(view);
        }
        public abstract void getCurrentWeather();
    }
}
