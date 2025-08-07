package com.example.dishdiary.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealsResponse {

    @SerializedName("meals")
    List<Meal> meals;

    public MealsResponse(List<Meal> meals) {
        this.meals = meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
    public List<Meal> getMeals() {
        return meals;
    }

}
