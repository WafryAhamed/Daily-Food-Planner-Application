package com.example.dishdiary.features.favourites.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.features.favourites.view.FavouritesView;
import com.example.dishdiary.model.DayMealEntry;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepository;

import java.util.List;

public class FavouritesPresenterImpl implements FavouritesPresenter {
    private FavouritesView view;
    private MealsRepository repo;

    public FavouritesPresenterImpl(FavouritesView _view, MealsRepository _repo) {
        this.view = _view;
        this.repo = _repo;
    }


    @Override
    public LiveData<List<Meal>> getFavMeals() {
        return repo.getFavouriteMeals();
    }

    @Override
    public void removeFromFav(Meal meal) {
        meal.setFav(false);
        repo.removeFavMeal(meal);
    }

    @Override
    public void insertDayMealEntry(String day, String mealId) {
        repo.insertDayMealEntry(new DayMealEntry(day, mealId));
    }
}
