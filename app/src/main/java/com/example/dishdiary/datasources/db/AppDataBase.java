package com.example.dishdiary.datasources.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dishdiary.model.DayMealEntry;
import com.example.dishdiary.model.FavMeal;
import com.example.dishdiary.model.Meal;

@Database(entities = {Meal.class, DayMealEntry.class, FavMeal.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;

    public abstract MealDAO getMealDAO();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "mealdb").build();
        }
        return instance;
    }
}
