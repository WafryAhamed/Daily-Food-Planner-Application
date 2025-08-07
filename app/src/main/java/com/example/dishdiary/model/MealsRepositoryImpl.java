package com.example.dishdiary.model;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.datasources.db.MealLocalDataSource;

import com.example.dishdiary.datasources.network.AreaNetworkCallback;
import com.example.dishdiary.datasources.network.CategoryNetworkCallback;
import com.example.dishdiary.datasources.network.IngredientNetworkCallback;
import com.example.dishdiary.datasources.network.NetworkCallback;
import com.example.dishdiary.datasources.network.MealRemoteDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealsRepositoryImpl implements MealsRepository {
    MealRemoteDataSource remoteDataSource;
    MealLocalDataSource localDataSource;
    private static MealsRepositoryImpl repo = null;

    public static MealsRepositoryImpl getInstance(MealRemoteDataSource remoteDataSource, MealLocalDataSource localDataSource) {
        if(repo == null) {
            repo = new MealsRepositoryImpl(remoteDataSource, localDataSource);
        }
//        localDataSource.deleteAllJunctions();
//        localDataSource.deleteAllDays();
        return repo;
    }

    private MealsRepositoryImpl(MealRemoteDataSource remoteDataSource, MealLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public void getAllMeals(NetworkCallback networkCallback) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("s", "Ara");
        remoteDataSource.makeNetworkCall(networkCallback, "search.php", queryParams);
    }

    @Override
    public void getMealById(NetworkCallback networkCallback, String mealId) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("i", mealId);
        remoteDataSource.makeNetworkCall(networkCallback, "lookup.php", queryParams);
    }

    @Override
    public void filterMealsByName(NetworkCallback networkCallback, String name) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("s", name);
        remoteDataSource.makeNetworkCall(networkCallback, "search.php", queryParams);
    }

    @Override
    public void filterMealsByCategory(NetworkCallback networkCallback, String category) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("c", category);
        remoteDataSource.makeNetworkCall(networkCallback, "filter.php", queryParams);
    }

    @Override
    public void filterMealsByArea(NetworkCallback networkCallback, String area) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("a", area);
        remoteDataSource.makeNetworkCall(networkCallback, "filter.php", queryParams);
    }

    @Override
    public void filterMealsByIngredient(NetworkCallback networkCallback, String ingredient) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("i", ingredient);
        remoteDataSource.makeNetworkCall(networkCallback, "filter.php", queryParams);
    }

    @Override
    public void getRandomMeal(NetworkCallback networkCallback) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("s", "a");
        remoteDataSource.makeNetworkCall(networkCallback, "random.php", queryParams);
    }

    @Override
    public void getAllCategories(CategoryNetworkCallback categoryNetworkCallback) {
        remoteDataSource.makeCategoryNetworkCall(categoryNetworkCallback);
    }

    @Override
    public void getAllAreas(AreaNetworkCallback areaNetworkCallback) {
        remoteDataSource.makeAreaNetworkCall(areaNetworkCallback);
    }

    @Override
    public void getAllIngredients(IngredientNetworkCallback ingredientNetworkCallback) {
        remoteDataSource.makeIngredientNetworkCall(ingredientNetworkCallback);
    }


    @Override
    public LiveData<List<Meal>> getStoredMeals() { return localDataSource.getStoredMeals(); }

    @Override
    public void insertMeal(Meal meal) { localDataSource.insertMeal(meal); }

    @Override
    public void deleteMeal(Meal meal) { localDataSource.removeMeal(meal); }

    @Override
    public void checkMealExists(Meal meal) { localDataSource.checkMealExists(meal); }

    @Override
    public LiveData<List<Meal>> getFavouriteMeals() { return localDataSource.getFavouriteMeals(); }

    @Override
    public void addFavMeal(Meal meal) {
        localDataSource.insertFavMeal(meal);
        localDataSource.insertMeal(meal);
    }

    @Override
    public void removeFavMeal(Meal meal) {
        localDataSource.removeFavMeal(meal);
    }

    @Override
    public void insertDayMealEntry(DayMealEntry dayMealEntry) {
        localDataSource.insertDayMealEntry(dayMealEntry);
    }

    @Override
    public void deleteDayMealEntry(String day, String mealId) {
        localDataSource.deleteDayMealEntry(day, mealId);
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDay(String day) {
        return localDataSource.getMealsOfDay(day);
    }

    @Override
    public LiveData<List<Meal>> getPlannedMeals() {
        return localDataSource.getAllPlannedMeals();
    }

}
