package com.example.dishdiary.features.meal_list.view;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface MealListView {

    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
}
