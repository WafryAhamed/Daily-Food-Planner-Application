package com.example.dishdiary.datasources.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdiary.model.DayMealEntry;
import com.example.dishdiary.model.FavMeal;
import com.example.dishdiary.model.Meal;

import java.util.List;

@Dao
public interface MealDAO {

    // Meal table

    @Query("SELECT * FROM meals_table")
    LiveData<List<Meal>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("SELECT EXISTS(SELECT 1 FROM meals_table WHERE idMeal = :idMeal)")
    boolean isMealExists(String idMeal);


    // Meal-Day Entry table

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDayMealEntry(DayMealEntry dayMealEntry);

    @Query("DELETE FROM day_meal_table WHERE day = :day AND idMeal = :mealId")
    void deleteDayMealEntry(String day, String mealId);


    @Query("SELECT meals_table.* FROM meals_table " +
            "JOIN day_meal_table ON meals_table.idMeal = day_meal_table.idMeal ")
    LiveData<List<Meal>> getAllPlannedMeals();


    @Query("SELECT meals_table.* FROM meals_table " +
            "JOIN day_meal_table ON meals_table.idMeal = day_meal_table.idMeal " +
            "WHERE day_meal_table.day = :dayName")
    LiveData<List<Meal>> getMealsOfDay(String dayName);


    @Query("DELETE FROM day_meal_table")
    void deleteAllEntries();


    // favourite Meals Table

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavMeal(FavMeal favMeal);

    @Query("DELETE FROM fav_meal_table WHERE idMeal = :mealId")
    void deleteFavMeal(String mealId);

    @Query("SELECT meals_table.* FROM meals_table " +
            "JOIN fav_meal_table ON meals_table.idMeal = fav_meal_table.idMeal ")
    LiveData<List<Meal>> getFavouriteMeals();


}
