package com.example.dishdiary.features.meal_list.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.features.meal_list.view.MealListView;
import com.example.dishdiary.model.DayMealEntry;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepository;
import com.example.dishdiary.datasources.network.NetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class MealListPresenterImpl implements MealListPresenter, NetworkCallback {
    private MealListView _view;
    private MealsRepository _repo;

    public MealListPresenterImpl(MealListView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }


    @Override
    public void getAllMeals() { _repo.getAllMeals(this); }


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
    public void onSuccessResult(List<Meal> meals) {
        for(Meal meal : meals) {
            _repo.checkMealExists(meal);
        }
        _view.showData(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) { _view.showErrMsg(errorMsg); }



    @Override
    public void getRandomMeal() {

    }

    @Override
    public void isMealExists(Meal meal) {
        _repo.checkMealExists(meal);
    }

    @Override
    public void insertDayMealEntry(String day, String mealId) {
        _repo.insertDayMealEntry(new DayMealEntry(day, mealId));
    }


    public LiveData<List<Meal>> getPlannedMeals(String day) {
        return _repo.getMealsOfDay(day);
//        return null;
    }
}
