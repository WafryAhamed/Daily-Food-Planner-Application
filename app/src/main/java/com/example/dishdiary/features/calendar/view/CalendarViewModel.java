package com.example.dishdiary.features.calendar.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dishdiary.model.Meal;

import java.util.List;

public class CalendarViewModel extends ViewModel {

    private final MutableLiveData<List<Meal>> mealsList;

    public CalendarViewModel() {
        mealsList = new MutableLiveData<List<Meal>>();
    }

    public MutableLiveData<List<Meal>> getMealsList() { return mealsList; }

    public void setMealsList(List<Meal> data) { mealsList.setValue(data); }

}