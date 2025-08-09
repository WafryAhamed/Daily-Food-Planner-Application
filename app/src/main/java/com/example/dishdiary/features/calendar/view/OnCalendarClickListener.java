package com.example.dishdiary.features.calendar.view;

import com.example.dishdiary.model.Meal;

public interface OnCalendarClickListener {
    void onLayoutClick(Meal meal);
    void onAddToFavClick(Meal meal);
    void onAddToCalendar(Meal meal);
    void onRemoveFromCalendar(String day, Meal meal);
}
