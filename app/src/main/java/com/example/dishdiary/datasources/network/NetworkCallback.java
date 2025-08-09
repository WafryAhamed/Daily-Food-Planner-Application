package com.example.dishdiary.datasources.network;

import com.example.dishdiary.model.Category;
import com.example.dishdiary.model.Meal;

import java.util.List;

public interface NetworkCallback {
    // Category Result Handlers
    void onSuccessResult(List<Meal> meals);
    void onFailureResult(String errorMsg);
}
