package com.example.dishdiary.datasources.network;

import android.util.Log;

import com.example.dishdiary.model.AreaResponse;
import com.example.dishdiary.model.CategoryResponse;
import com.example.dishdiary.model.IngredientResponse;
import com.example.dishdiary.model.MealsResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImpl implements MealRemoteDataSource {
    public static final String TAG = "MealRemoteDataSource";
    public static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealService mealService;

    private static MealRemoteDataSource client = null;

    public MealRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mealService = retrofit.create(MealService.class);
    }

    public static MealRemoteDataSource getInstance() {
        if(client == null) {
            client = new MealRemoteDataSourceImpl();
        }
        return client;
    }

    @Override
    public void makeNetworkCall(NetworkCallback networkCallback, String endPoint, Map<String, String> queryParams) {
        mealService.getMealResponse(endPoint, queryParams).enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                Log.i(TAG, "OnResponse..." + response.body().getMeals());
                networkCallback.onSuccessResult(response.body().getMeals());
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable throwable) {
                networkCallback.onFailureResult(throwable.getMessage());
            }
        });
    }

    @Override
    public void makeCategoryNetworkCall(CategoryNetworkCallback categoryNetworkCallback) {
        mealService.getCategoryResponse().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.i(TAG, "OnCategoryResponse..." + response.body().getCategories());
                categoryNetworkCallback.onCategorySuccessResult(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable throwable) {
                categoryNetworkCallback.onCategoryFailureResult(throwable.getMessage());

            }
        });
    }

    @Override
    public void makeAreaNetworkCall(AreaNetworkCallback areaNetworkCallback) {
        mealService.getAreaResponse().enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                Log.i(TAG, "OnAreaResponse..." + response.body().getAreas());
                areaNetworkCallback.onAreaSuccessResult(response.body().getAreas());
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable throwable) {
                areaNetworkCallback.onAreaFailureResult(throwable.getMessage());

            }
        });
    }

    @Override
    public void makeIngredientNetworkCall(IngredientNetworkCallback ingredientNetworkCallback) {
        mealService.getIngredientResponse().enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                Log.i(TAG, "OnIngredientResponse..." + response.body().getIngredients());
                ingredientNetworkCallback.onIngredientSuccessResult(response.body().getIngredients());
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
                ingredientNetworkCallback.onIngredientFailureResult(throwable.getMessage());

            }
        });
    }


}
