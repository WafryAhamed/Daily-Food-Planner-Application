package com.example.dishdiary.features.home.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface HomePresenter {
    public void getAllMeals();
    public void addToFav(Meal meal);
    public void addToStored(Meal meal);
    public void getRandomMeal();

    //test
    public LiveData<List<Meal>> getPlannedMeals(String day);
}
