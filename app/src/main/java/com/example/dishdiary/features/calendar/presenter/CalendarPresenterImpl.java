package com.example.dishdiary.features.calendar.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.datasources.network.NetworkCallback;
import com.example.dishdiary.features.calendar.view.CalendarView;
import com.example.dishdiary.model.DayMealEntry;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepository;

import java.util.List;

public class CalendarPresenterImpl implements CalendarPresenter, NetworkCallback {
    private CalendarView _view;
    private MealsRepository _repo;

    public CalendarPresenterImpl(CalendarView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }


    @Override
    public void getAllMeals() { _repo.getAllMeals(this); }

//    public void getAllMeals() { _view.showData(_repo.getMealsOfDay(sun).getQueryValue()); }

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
    public void isMealExists(Meal meal) {
        _repo.checkMealExists(meal);
    }

    @Override
    public void insertDayMealEntry(String day, String mealId) {
        _repo.insertDayMealEntry(new DayMealEntry(day, mealId));
    }

    @Override
    public void deleteDayMealEntry(String day, String mealId) {
        _repo.deleteDayMealEntry(day, mealId);
    }

    @Override
    public LiveData<List<Meal>> getPlannedMeals() {
        return _repo.getPlannedMeals();
    }

    @Override
    public LiveData<List<Meal>> getMealOfDay(String day) {
        return _repo.getMealsOfDay(day);
    }
}
