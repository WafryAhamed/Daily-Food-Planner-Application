package com.example.dishdiary.features.meal_details.presenter;

import com.example.dishdiary.features.meal_details.view.MealDetailsView;
import com.example.dishdiary.model.DayMealEntry;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepository;
import com.example.dishdiary.datasources.network.NetworkCallback;

import java.util.List;

public class MealDetailsPresenterImpl implements MealDetailsPresenter, NetworkCallback {
    private MealDetailsView _view;
    private MealsRepository _repo;

    public MealDetailsPresenterImpl(MealDetailsView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMealById(String mealId) {
        _repo.getMealById(this, mealId);
    }

    @Override
    public void getIngredient() {

    }

    @Override
    public void addToFav(Meal meal) {
        meal.setFav(true);
        _repo.addFavMeal(meal);
    }

    @Override
    public void addToStored(Meal meal) {
        _repo.insertMeal(meal);
    }

    @Override
    public void removeFromFav(Meal meal) {
        meal.setFav(false);
        _repo.removeFavMeal(meal);
    }

    @Override
    public void insertDayMealEntry(String day, String mealId) {
        _repo.insertDayMealEntry(new DayMealEntry(day, mealId));
    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
        if (meals.get(0) != null) {
            _repo.checkMealExists(meals.get(0));
            _view.showData(meals.get(0));
        } else {
            _view.showErrMsg("Meal is not found");
        }
    }

    @Override
    public void onFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);
    }

}
