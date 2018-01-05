package com.ashraf.weather.photos.presentationlayer.activity.homeActivity;

import android.graphics.Bitmap;

public class BitmapDto {

    String fileName;
    Bitmap bitmap;

    public BitmapDto(String fileName, Bitmap bitmap) {
        this.fileName = fileName;
        this.bitmap = bitmap;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
