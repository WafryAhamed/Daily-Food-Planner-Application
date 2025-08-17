package com.example.dishdiary.datasources.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.DayMealEntry;
import com.example.dishdiary.model.FavMeal;
import com.example.dishdiary.model.Meal;

import java.util.List;

public class MealLocalDataSourceImpl implements MealLocalDataSource {

    private static MealLocalDataSource MealLocalDataSource = null;

    private Context context;
    private AppDataBase db;
    private MealDAO mealDAO;
    private LiveData<List<Meal>> storedMeals;
//    private LiveData<List<Meal>> mealsOfDay;


    private MealLocalDataSourceImpl(Context context) {
        this.context = context;
        db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealDAO();
        storedMeals = mealDAO.getAllMeals();

    }

    public static MealLocalDataSource getInstance(Context context) {
        if (MealLocalDataSource == null) {
            MealLocalDataSource = new MealLocalDataSourceImpl(context);
        }
        return MealLocalDataSource;
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return storedMeals;
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                meal.setFav(true);
                mealDAO.insertMeal(meal);
            }
        }).start();
    }

    @Override
    public void removeMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public void checkMealExists(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                meal.setFav(mealDAO.isMealExists(meal.getIdMeal()));
            }
        }).start();
    }


    @Override
    public void insertDayMealEntry(DayMealEntry dayMealEntry) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertDayMealEntry(dayMealEntry);
            }
        }).start();
    }

    @Override
    public void deleteDayMealEntry(String day, String mealId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteDayMealEntry(day, mealId);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDay(String day) {
        return mealDAO.getMealsOfDay(day);
    }

    @Override
    public LiveData<List<Meal>> getAllPlannedMeals() {
        return mealDAO.getAllPlannedMeals();
    }

    @Override
    public LiveData<List<Meal>> getFavouriteMeals() {
        return mealDAO.getFavouriteMeals();
    }


    @Override
    public void insertFavMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertFavMeal(new FavMeal(meal.getIdMeal()));
            }
        }).start();
    }

    @Override
    public void removeFavMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteFavMeal(meal.getIdMeal());
            }
        }).start();
    }


    ////
    @Override
    public void deleteAllEntries() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteAllEntries();
            }
        }).start();

    }



}
