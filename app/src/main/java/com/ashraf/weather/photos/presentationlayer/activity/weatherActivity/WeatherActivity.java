package com.ashraf.weather.photos.presentationlayer.activity.weatherActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ashraf.weather.photos.R;
import com.ashraf.weather.photos.domainlayer.getWeatherRepository.CurrentWeather;
import com.ashraf.weather.photos.presentationlayer.activity.baseActivity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends BaseActivity implements WeatherActivityContract.View {

    WeatherActivityContract.Presenter presenter;

    @BindView(R.id.place_name_editText)
    EditText placeNameEditText;
    @BindView(R.id.place_name_textInputLayout)
    TextInputLayout placeNameTextInputLayout;
    @BindView(R.id.weather_temperature_textView)
    TextView weatherTemperatureTextView;
    @BindView(R.id.weather_description_textView)
    TextView weatherDescriptionTextView;
    @BindView(R.id.photo_imageView)
    ImageView photoImageView;
    @BindView(R.id.save_button)
    Button saveButton;
    @BindView(R.id.retry_button)
    Button retryButton;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static void start(Context context) {
        Intent starter = new Intent(context, WeatherActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        setPresenter(new WeatherActivityPresenter(this));
        setPhoto();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();

    }

    @OnClick(R.id.retry_button)
    public void onRetryClick() {
        presenter.getCurrentWeather();
    }

    @OnClick(R.id.save_button)
    public void onSaveClick() {
        Bitmap bitmap = bitmapHelper.getLastBitmap();
        bitmap = bitmapHelper.typeTextAboveBitmap(bitmap,getText(), 18, Color.WHITE);
        bitmapHelper.saveBitmapOnDisk(bitmap);
    }

    private Bitmap getPhoto() {
        return bitmapHelper.getLastBitmap();
    }

    private void setPhoto() {
        Bitmap backgroundBitmap = getPhoto();
        photoImageView.setImageBitmap(backgroundBitmap);
    }
    private String getText() {
        return placeNameEditText.getText()
                +"\n"
                +weatherTemperatureTextView.getText()+
                "\n"
                +weatherDescriptionTextView;
    }

    @Override
    public void setPresenter(WeatherActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onConnectionError() {
        Toast.makeText(getBaseContext(), getString(R.string.connection_error_message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError() {
        Toast.makeText(getBaseContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetCurrentWeather(CurrentWeather currentWeather) {
        weatherTemperatureTextView.setText(String.valueOf(currentWeather.getTemperature()));
        weatherDescriptionTextView.setText(currentWeather.getDescription());
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRetryButton() {
        retryButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetryButton() {
        retryButton.setVisibility(View.INVISIBLE);
    }
}
