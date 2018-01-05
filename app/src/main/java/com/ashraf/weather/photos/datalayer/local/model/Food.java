package com.ashraf.weather.photos.datalayer.local.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Food")
public class Food {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
}
