package com.example.dishdiary.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {

    @SerializedName("meals")
    List<Ingredient> ingredients;


    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

}

