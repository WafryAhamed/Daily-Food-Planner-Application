package com.example.dishdiary.features.calendar.view;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface CalendarView {

    public void showData(List<Meal> meals);

    public void showSatMeals(List<Meal> meals);
    public void showSunMeals(List<Meal> meals);
    public void showMonMeals(List<Meal> meals);
    public void showTueMeals(List<Meal> meals);
    public void showWedMeals(List<Meal> meals);
    public void showThuMeals(List<Meal> meals);
    public void showFriMeals(List<Meal> meals);

    public void showErrMsg(String error);
}
