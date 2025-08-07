package com.example.dishdiary.features.explore.presenter;

import com.example.dishdiary.datasources.network.AreaNetworkCallback;
import com.example.dishdiary.datasources.network.CategoryNetworkCallback;
import com.example.dishdiary.datasources.network.IngredientNetworkCallback;
import com.example.dishdiary.features.explore.view.ExploreView;
import com.example.dishdiary.features.home.view.HomeView;
import com.example.dishdiary.model.Area;
import com.example.dishdiary.model.Category;
import com.example.dishdiary.model.Ingredient;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepository;
import com.example.dishdiary.datasources.network.NetworkCallback;

import java.util.List;

public class ExplorePresenterImpl implements ExplorePresenter, NetworkCallback, CategoryNetworkCallback, AreaNetworkCallback, IngredientNetworkCallback {
    private ExploreView view;
    private MealsRepository repo;

    public ExplorePresenterImpl(ExploreView _view, MealsRepository _repo) {
        this.view = _view;
        this.repo = _repo;
    }


    @Override
    public void getAllMeals() {
        repo.getAllMeals(this);
    }

    @Override
    public void filterByName(String name) {
        repo.filterMealsByName(this, name);
    }

    @Override
    public void filterByCategory(String category) {
        repo.filterMealsByCategory(this, category);
    }

    @Override
    public void filterByArea(String area) {
        repo.filterMealsByArea(this, area);
    }

    @Override
    public void filterByIngredient(String ingredient) {
        repo.filterMealsByIngredient(this, ingredient);
    }

    @Override
    public void getAllCategories() {
        repo.getAllCategories(this);
    }

    @Override
    public void getAllAreas() {
        repo.getAllAreas(this);
    }

    @Override
    public void getAllIngredients() {
        repo.getAllIngredients(this);
    }


    @Override
    public void onSuccessResult(List<Meal> meals) {
        if (meals != null) {
            for (Meal Meal : meals) {
                repo.checkMealExists(Meal);
            }
            view.showData(meals);
        } else {
            view.showErrMsg("No meals found");
        }

    }

    @Override
    public void onFailureResult(String errorMsg) {
        view.showErrMsg(errorMsg);
    }


    @Override
    public void onCategorySuccessResult(List<Category> categories) {
        if (categories != null) {
            view.showCategories(categories);
        } else {
            view.showErrMsg("No categories found");
        }
    }

    @Override
    public void onCategoryFailureResult(String errorMsg) {
        view.showErrMsg(errorMsg);
    }

    @Override
    public void onAreaSuccessResult(List<Area> areas) {
        if (areas != null) {
            view.showAreas(areas);
        } else {
            view.showErrMsg("No areas found");
        }
    }

    @Override
    public void onAreaFailureResult(String errorMsg) {
        view.showErrMsg(errorMsg);
    }

    @Override
    public void onIngredientSuccessResult(List<Ingredient> ingredients) {
        if (ingredients != null) {
            view.showIngredients(ingredients);
        } else {
            view.showErrMsg("No ingredients found");
        }
    }

    @Override
    public void onIngredientFailureResult(String errorMsg) {
        view.showErrMsg(errorMsg);
    }
}
