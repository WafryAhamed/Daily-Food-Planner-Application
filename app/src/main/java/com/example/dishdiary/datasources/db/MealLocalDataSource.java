package com.example.dishdiary.datasources.db;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.DayMealEntry;
import com.example.dishdiary.model.Meal;

import java.util.List;

public interface MealLocalDataSource {
    // Meals Table
    LiveData<List<Meal>> getStoredMeals();
    void insertMeal(Meal meal);
    void removeMeal(Meal meal);
    void checkMealExists(Meal meal);

    // Day-Meal Entry Table Entries
    void insertDayMealEntry(DayMealEntry dayMealEntry);
    void deleteDayMealEntry(String day, String mealId);
    LiveData<List<Meal>> getMealsOfDay(String day);
    LiveData<List<Meal>> getAllPlannedMeals();

    // Favourite Meals Table
    LiveData<List<Meal>> getFavouriteMeals();
    void insertFavMeal(Meal meal);
    void removeFavMeal(Meal meal);
    //
    void deleteAllEntries();

}
