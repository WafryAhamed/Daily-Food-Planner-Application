package com.example.dishdiary.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "day_meal_table")
public class DayMealEntry {
    @PrimaryKey (autoGenerate = true)
    public int idEntry;

    @NonNull
    public String day;

    @NonNull
    public String idMeal;


    public DayMealEntry(String day, String idMeal) {
        this.day = day;
        this.idMeal = idMeal;
    }
}