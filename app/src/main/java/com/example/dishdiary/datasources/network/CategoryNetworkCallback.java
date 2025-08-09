package com.example.dishdiary.datasources.network;

import com.example.dishdiary.model.Category;

import java.util.List;

public interface CategoryNetworkCallback {
    void onCategorySuccessResult(List<Category> categories);
    void onCategoryFailureResult(String errorMsg);
}
