package com.example.dishdiary.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class CategoryResponse {

    @SerializedName("categories")
    List<Category> categories;

    public CategoryResponse(List<Category> categories) {
        this.categories = categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public List<Category> getCategories() {
        return categories;
    }

}