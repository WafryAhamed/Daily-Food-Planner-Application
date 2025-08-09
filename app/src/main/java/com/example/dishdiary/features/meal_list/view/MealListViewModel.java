package com.example.dishdiary.features.meal_list.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dishdiary.model.Meal;

import java.util.HashMap;
import java.util.List;

public class MealListViewModel extends ViewModel {

    private final MutableLiveData<List<Meal>> mealsList;

    public MealListViewModel() {
        mealsList = new MutableLiveData<List<Meal>>();
    }

    public MutableLiveData<List<Meal>> getMealsList() { return mealsList; }

    public void setMealsList(List<Meal> data) { mealsList.setValue(data); }

}