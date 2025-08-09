package com.example.dishdiary.features.explore.view;

import com.example.dishdiary.model.Area;
import com.example.dishdiary.model.Category;
import com.example.dishdiary.model.Ingredient;
import com.example.dishdiary.model.Meal;

import java.util.List;

public interface ExploreView {

    public void showData(List<Meal> meals);
    public void showCategories(List<Category> categories);
    public void showAreas(List<Area> area);
    public void showIngredients(List<Ingredient> ingredients);

    public void showErrMsg(String error);
}
