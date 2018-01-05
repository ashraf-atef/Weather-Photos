package com.ashraf.weather.photos.datalayer.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ashraf.weather.photos.datalayer.local.model.Food;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Food> foodLocal);

    @Query(value = "SELECT * FROM Food WHERE id > :id ORDER BY id ASC")
    Single<List<Food>> getItems(int id);
}
