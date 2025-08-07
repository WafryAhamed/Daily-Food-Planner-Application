package com.example.dishdiary.features.explore.view;

import com.example.dishdiary.model.Area;
import com.example.dishdiary.model.Category;
import com.example.dishdiary.model.Ingredient;
import com.example.dishdiary.model.Meal;

public interface OnExploreClickListener {
    void onCategoryLayoutClick(Category category);
    void onAreaClick(Area area);
    void onIngredientClick(Ingredient ingredient);
//    void onAddToFavClick(Meal meal);
//    void openDetails();
    void onSearchClick();
}
