package com.example.dishdiary.datasources.network;

import com.example.dishdiary.model.Category;
import com.example.dishdiary.model.Ingredient;

import java.util.List;

public interface IngredientNetworkCallback {
    void onIngredientSuccessResult(List<Ingredient> ingredients);
    void onIngredientFailureResult(String errorMsg);
}
