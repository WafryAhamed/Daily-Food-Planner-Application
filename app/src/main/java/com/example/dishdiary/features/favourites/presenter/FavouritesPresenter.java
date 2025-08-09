package com.example.dishdiary.features.favourites.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface FavouritesPresenter {
    public LiveData<List<Meal>> getFavMeals();
    public void removeFromFav(Meal meal);

    public void insertDayMealEntry(String day, String mealId);
}
