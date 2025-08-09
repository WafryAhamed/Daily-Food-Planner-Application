package com.example.dishdiary.features.meal_list.view;

import com.example.dishdiary.model.Meal;

public interface OnMealListClickListener {
    void onLayoutClick(Meal meal);
    void onAddToFavClick(Meal meal);
    void onAddToCalendar(Meal meal);
}
