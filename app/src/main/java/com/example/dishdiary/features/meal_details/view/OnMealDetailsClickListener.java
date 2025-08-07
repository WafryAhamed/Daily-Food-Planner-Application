package com.example.dishdiary.features.meal_details.view;

import com.example.dishdiary.model.Meal;

public interface OnMealDetailsClickListener {
    void onLayoutClick(Meal meal);
    void onAddToFavClick(Meal meal);
    void onRemoveFromFavClick(Meal meal);
}
