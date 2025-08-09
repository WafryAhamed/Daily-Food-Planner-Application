package com.example.dishdiary.features.meal_details.presenter;

import com.example.dishdiary.model.Meal;

public interface MealDetailsPresenter {
    public void getMealById(String mealId);
    public void getIngredient();
    public void addToFav(Meal meal);
    public void addToStored(Meal meal);
    public void removeFromFav(Meal meal);
    public void insertDayMealEntry(String day, String mealId);
}
