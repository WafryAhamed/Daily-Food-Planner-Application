package com.example.dishdiary.model;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.datasources.network.AreaNetworkCallback;
import com.example.dishdiary.datasources.network.CategoryNetworkCallback;
import com.example.dishdiary.datasources.network.IngredientNetworkCallback;
import com.example.dishdiary.datasources.network.NetworkCallback;

import java.util.List;

public interface MealsRepository {
    // Remote Data Source
    // Meals
    public void getAllMeals(NetworkCallback networkCallback);
    public void getMealById(NetworkCallback networkCallback, String mealId);
    public void getRandomMeal(NetworkCallback networkCallback);
    public void filterMealsByName(NetworkCallback networkCallback, String name);
    public void filterMealsByCategory(NetworkCallback networkCallback, String category);
    public void filterMealsByArea(NetworkCallback networkCallback, String area);
    public void filterMealsByIngredient(NetworkCallback networkCallback, String ingredient);

    // Categories
    public void getAllCategories(CategoryNetworkCallback categoryNetworkCallback);

    // Areas
    public void getAllAreas(AreaNetworkCallback areaNetworkCallback);

    // Ingredients
    public void getAllIngredients(IngredientNetworkCallback ingredientNetworkCallback);

    // Local Data Source - Stored Meals
    public LiveData<List<Meal>> getStoredMeals();
    public void insertMeal(Meal meal);
    public void deleteMeal(Meal meal);
    public void checkMealExists(Meal meal);

    // Local Data Source - favourites
    public LiveData<List<Meal>> getFavouriteMeals();
    public void addFavMeal(Meal meal);
    public void removeFavMeal(Meal meal);

    // Local Data Source - Day-Meal Entry
    public void insertDayMealEntry(DayMealEntry dayMealEntry);
    public void deleteDayMealEntry(String day, String mealId);
    public LiveData<List<Meal>> getMealsOfDay(String day);
    public LiveData<List<Meal>> getPlannedMeals();

}
