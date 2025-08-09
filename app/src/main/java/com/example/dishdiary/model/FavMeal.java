package com.example.dishdiary.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_meal_table")

public class FavMeal {
    @PrimaryKey
    @NonNull
    public String idMeal;

    public FavMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }

    @NonNull
    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(@NonNull String idMeal) {
        this.idMeal = idMeal;
    }
}