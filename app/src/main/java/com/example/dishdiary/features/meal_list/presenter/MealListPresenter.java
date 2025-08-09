package com.example.dishdiary.features.meal_list.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface MealListPresenter {
    public void getAllMeals();
    public void addToFav(Meal meal);
    public void addToStored(Meal meal);
    public void getRandomMeal();
    public void isMealExists(Meal meal);

    public void insertDayMealEntry(String day, String mealId);
    public LiveData<List<Meal>> getPlannedMeals(String day);
}
