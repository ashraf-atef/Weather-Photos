package com.ashraf.weather.photos.presentationlayer.activity.weatherActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ashraf.weather.photos.R;
import com.ashraf.weather.photos.domainlayer.getWeatherRepository.CurrentWeather;
import com.ashraf.weather.photos.presentationlayer.activity.baseActivity.BaseActivity;
import com.ashraf.weather.photos.presentationlayer.activity.galleryActivity.GalleryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends BaseActivity implements WeatherActivityContract.View {

    WeatherActivityContract.Presenter presenter;
    CurrentWeather currentWeather;

    @BindView(R.id.place_name_editText)
    TextInputEditText placeNameEditText;
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
    @BindView(R.id.toolBar)
    Toolbar toolBar;

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
        mUiHelper.updateActionBar(this, toolBar, R.color.white);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.retry_button)
    public void onRetryClick() {
        presenter.getCurrentWeather();
    }

    @OnClick(R.id.save_button)
    public void onSaveClick() {
        if (verify()) {
            Bitmap bitmap = mBitmapHelper.getLastBitmap();
            bitmap = mBitmapHelper.drawTextToBitmap(bitmap,
                    getResources().getDisplayMetrics().density,
                    getText(),
                    Color.WHITE,
                    Color.DKGRAY);
            mBitmapHelper.deleteLastBitmap();
            mBitmapHelper.saveBitmapOnDisk(bitmap);
            finish();
            GalleryActivity.start(this, mBitmapHelper.photoList.size() - 1);
        }
    }

    private Bitmap getPhoto() {
        return mBitmapHelper.getLastBitmap();
    }

    private void setPhoto() {
        Bitmap backgroundBitmap = getPhoto();
        photoImageView.setImageBitmap(backgroundBitmap);
    }

    private String getText() {
        return placeNameEditText.getText().toString() + " - "
                + weatherTemperatureTextView.getText() + " - "
                + weatherDescriptionTextView.getText();
    }

    public boolean verify() {
        if (currentWeather == null) {
            Toast.makeText(getBaseContext(), getString(R.string.wait_message), Toast.LENGTH_LONG).show();
            return false;
        }
        if (placeNameEditText.getText().toString().length() == 0) {
            placeNameEditText.setError(getString(R.string.empty_place_name_message));
            return false;
        }
        return true;
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
        this.currentWeather = currentWeather;
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
