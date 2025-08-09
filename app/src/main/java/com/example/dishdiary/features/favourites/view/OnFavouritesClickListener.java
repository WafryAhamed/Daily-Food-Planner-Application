package com.example.dishdiary.features.favourites.view;

import com.example.dishdiary.model.Meal;

public interface OnFavouritesClickListener {
    void onLayoutClick(Meal meal);
    void onRemoveFromFavClick(Meal meal);
    void onAddToCalendar(Meal meal);


}
