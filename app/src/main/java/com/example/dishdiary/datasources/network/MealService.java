package com.example.dishdiary.datasources.network;

import com.example.dishdiary.model.AreaResponse;
import com.example.dishdiary.model.CategoryResponse;
import com.example.dishdiary.model.IngredientResponse;
import com.example.dishdiary.model.MealsResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface MealService {
//    @GET("search.php?s=Ara")
//    Call<MealsResponse> getMealResponse();

    @GET
    Call<MealsResponse> getMealResponse(@Url String endpoint, @QueryMap Map<String, String> queryParams);

    @GET("categories.php")
    Call<CategoryResponse> getCategoryResponse();

    @GET("list.php?a=list")
    Call<AreaResponse> getAreaResponse();

    @GET("list.php?i=list")
    Call<IngredientResponse> getIngredientResponse();

//
//    @GET("list.php?i=list")
//    Call<IngredientResponse> getIngredientResponse();


//
//    @GET("search.php")
//    Call<MealsResponse> getSearchMealResponse(@Query("s") String mealName);
//
//    @GET("filter.php")
//    Call<MealsResponse> getMealByCategoryResponse(@Query("c") String categoryName);
//
//    @GET("filter.php")
//    Call<MealsResponse> getMealByAreaResponse(@Query("a") String areaName);
//
//    @GET("lookup.php")
//    Call<MealsResponse> getMealByIdResponse(@Query("i") String mealName);


//    @GET("categories.php")
//    Call<CategoryResponse> getCategoriesResponse();


}