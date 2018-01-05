package com.ashraf.weather.photos.datalayer.local.localdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ashraf.weather.photos.datalayer.local.dao.FoodDao;
import com.ashraf.weather.photos.datalayer.local.model.Food;

@Database(entities = {Food.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {
    public abstract FoodDao foodDao();
}
