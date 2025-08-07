package com.example.dishdiary.features.home.view;

import com.example.dishdiary.model.Meal;

public interface OnHomeClickListener {
    void onLayoutClick(Meal meal);
    void onAddToFavClick(Meal meal);
    void openDetails();
}
