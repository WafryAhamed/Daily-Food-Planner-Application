package com.example.dishdiary.datasources.network;

import java.util.Map;

public interface MealRemoteDataSource {
    void makeNetworkCall(NetworkCallback networkCallback, String endPoint, Map<String, String> queryParams);
    void makeCategoryNetworkCall(CategoryNetworkCallback categoryNetworkCallback);
    void makeAreaNetworkCall(AreaNetworkCallback areaNetworkCallback);
    void makeIngredientNetworkCall(IngredientNetworkCallback ingredientNetworkCallback);
}
