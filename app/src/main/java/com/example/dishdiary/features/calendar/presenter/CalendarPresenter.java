package com.example.dishdiary.features.calendar.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface CalendarPresenter {
    public void getAllMeals();
    public void addToFav(Meal meal);
    public void addToStored(Meal meal);
    public void isMealExists(Meal meal);

    //test
    public void insertDayMealEntry(String day, String mealId);
    public void deleteDayMealEntry(String day, String mealId);
    public LiveData<List<Meal>> getPlannedMeals();
    public LiveData<List<Meal>> getMealOfDay(String day);
}
