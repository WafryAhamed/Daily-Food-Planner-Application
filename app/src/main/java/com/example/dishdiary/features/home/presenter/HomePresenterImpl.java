package com.example.dishdiary.features.home.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.features.home.view.HomeView;
import com.example.dishdiary.model.MealsRepository;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.datasources.network.NetworkCallback;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, NetworkCallback {
    private HomeView _view;
    private MealsRepository _repo;

    public HomePresenterImpl(HomeView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }


    @Override
    public void getAllMeals() { _repo.getAllMeals(this); }
//    public void getAllMeals() { _view.showData(_repo.getMealsOfDay(sun).getQueryValue()); }



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
        for(Meal Meal : meals) {
            _repo.checkMealExists(Meal);
        }
        _view.showData(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) { _view.showErrMsg(errorMsg); }

    @Override
    public void getRandomMeal() { _repo.getRandomMeal(this); }


    public LiveData<List<Meal>> getPlannedMeals(String day) {
        return _repo.getMealsOfDay(day);
//        return null;
    }
}
