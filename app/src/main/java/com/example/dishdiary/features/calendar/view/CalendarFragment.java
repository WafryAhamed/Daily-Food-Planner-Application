package com.example.dishdiary.features.calendar.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdiary.databinding.FragmentCalendarBinding;
import com.example.dishdiary.databinding.FragmentMeallistBinding;
import com.example.dishdiary.datasources.db.MealLocalDataSourceImpl;
import com.example.dishdiary.datasources.network.MealRemoteDataSourceImpl;
import com.example.dishdiary.features.calendar.presenter.CalendarPresenter;
import com.example.dishdiary.features.calendar.presenter.CalendarPresenterImpl;
import com.example.dishdiary.features.meal_details.view.MealDetailsActivity;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepositoryImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment implements OnCalendarClickListener, CalendarView {

    private FragmentCalendarBinding binding;

    private static final String TAG = "CalendarFragment";

    // Incoming Data
    String filterBy, value;

    // Presenter
    CalendarPresenter calendarPresenter;


    // Sat
    private RecyclerView recyclerViewSat;
    private CalendarAdapter calendarAdapterSat;
    private LinearLayoutManager layoutManagerSat;
    LiveData<List<Meal>> mealsOfSat;

    // Sun
    private RecyclerView recyclerViewSun;
    private CalendarAdapter calendarAdapterSun;
    private LinearLayoutManager layoutManagerSun;
    LiveData<List<Meal>> mealsOfSun;

    // Mon
    private RecyclerView recyclerViewMon;
    private CalendarAdapter calendarAdapterMon;
    private LinearLayoutManager layoutManagerMon;
    LiveData<List<Meal>> mealsOfMon;

    // Tue
    private RecyclerView recyclerViewTue;
    private CalendarAdapter calendarAdapterTue;
    private LinearLayoutManager layoutManagerTue;
    LiveData<List<Meal>> mealsOfTue;

    // Wed
    private RecyclerView recyclerViewWed;
    private CalendarAdapter calendarAdapterWed;
    private LinearLayoutManager layoutManagerWed;
    LiveData<List<Meal>> mealsOfWed;

    // Thu
    private RecyclerView recyclerViewThu;
    private CalendarAdapter calendarAdapterThu;
    private LinearLayoutManager layoutManagerThu;
    LiveData<List<Meal>> mealsOfThu;

    // Fri
    private RecyclerView recyclerViewFri;
    private CalendarAdapter calendarAdapterFri;
    private LinearLayoutManager layoutManagerFri;
    LiveData<List<Meal>> mealsOfFri;

    LiveData<List<Meal>> plannedMeals;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        // Presenter
        calendarPresenter = new CalendarPresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(getActivity())
                )
        );



        // UI - Sat
        recyclerViewSat.setHasFixedSize(true);
        calendarAdapterSat = new CalendarAdapter(this.getContext(), "Saturday", new ArrayList<Meal>(), this);
        layoutManagerSat = new LinearLayoutManager(this.getContext());
        layoutManagerSat.setOrientation(RecyclerView.VERTICAL);
        recyclerViewSat.setLayoutManager(layoutManagerSat);
        recyclerViewSat.setAdapter(calendarAdapterSat);
        // Observe on data
        mealsOfSat = calendarPresenter.getMealOfDay("Saturday");
        mealsOfSat.observe(getViewLifecycleOwner(), this::showSatMeals);

        // UI - Sun
        recyclerViewSun.setHasFixedSize(true);
        calendarAdapterSun = new CalendarAdapter(this.getContext(), "Sunday", new ArrayList<Meal>(), this);
        layoutManagerSun = new LinearLayoutManager(this.getContext());
        layoutManagerSun.setOrientation(RecyclerView.VERTICAL);
        recyclerViewSun.setLayoutManager(layoutManagerSun);
        recyclerViewSun.setAdapter(calendarAdapterSun);
        // Observe on data
        mealsOfSun = calendarPresenter.getMealOfDay("Sunday");
        mealsOfSun.observe(getViewLifecycleOwner(), this::showSunMeals);

        // UI - Mon
        recyclerViewMon.setHasFixedSize(true);
        calendarAdapterMon = new CalendarAdapter(this.getContext(), "Monday",  new ArrayList<Meal>(), this);
        layoutManagerMon = new LinearLayoutManager(this.getContext());
        layoutManagerMon.setOrientation(RecyclerView.VERTICAL);
        recyclerViewMon.setLayoutManager(layoutManagerMon);
        recyclerViewMon.setAdapter(calendarAdapterMon);
        // Observe on data
        mealsOfMon = calendarPresenter.getMealOfDay("Monday");
        mealsOfMon.observe(getViewLifecycleOwner(), this::showMonMeals);

        // UI - Tue
        recyclerViewTue.setHasFixedSize(true);
        calendarAdapterTue = new CalendarAdapter(this.getContext(), "Tuesday",  new ArrayList<Meal>(), this);
        layoutManagerTue = new LinearLayoutManager(this.getContext());
        layoutManagerTue.setOrientation(RecyclerView.VERTICAL);
        recyclerViewTue.setLayoutManager(layoutManagerTue);
        recyclerViewTue.setAdapter(calendarAdapterTue);
        // Observe on data
        mealsOfTue = calendarPresenter.getMealOfDay("Tuesday");
        mealsOfTue.observe(getViewLifecycleOwner(), this::showTueMeals);

        // UI - Wed
        recyclerViewWed.setHasFixedSize(true);
        calendarAdapterWed = new CalendarAdapter(this.getContext(), "Wednesday", new ArrayList<Meal>(), this);
        layoutManagerWed = new LinearLayoutManager(this.getContext());
        layoutManagerWed.setOrientation(RecyclerView.VERTICAL);
        recyclerViewWed.setLayoutManager(layoutManagerWed);
        recyclerViewWed.setAdapter(calendarAdapterWed);
        // Observe on data
        mealsOfWed = calendarPresenter.getMealOfDay("Wednesday");
        mealsOfWed.observe(getViewLifecycleOwner(), this::showWedMeals);

        // UI - Thu
        recyclerViewThu.setHasFixedSize(true);
        calendarAdapterThu = new CalendarAdapter(this.getContext(), "Thursday", new ArrayList<Meal>(), this);
        layoutManagerThu = new LinearLayoutManager(this.getContext());
        layoutManagerThu.setOrientation(RecyclerView.VERTICAL);
        recyclerViewThu.setLayoutManager(layoutManagerThu);
        recyclerViewThu.setAdapter(calendarAdapterThu);
        // Observe on data
        mealsOfThu = calendarPresenter.getMealOfDay("Thursday");
        mealsOfThu.observe(getViewLifecycleOwner(), this::showThuMeals);

        // UI - Fri
        recyclerViewFri.setHasFixedSize(true);
        calendarAdapterFri = new CalendarAdapter(this.getContext(), "Friday", new ArrayList<Meal>(), this);
        layoutManagerFri = new LinearLayoutManager(this.getContext());
        layoutManagerFri.setOrientation(RecyclerView.VERTICAL);
        recyclerViewFri.setLayoutManager(layoutManagerFri);
        recyclerViewFri.setAdapter(calendarAdapterFri);
        // Observe on data
        mealsOfFri = calendarPresenter.getMealOfDay("Friday");
        mealsOfFri.observe(getViewLifecycleOwner(), this::showFriMeals);
    }

    private void initUI() {
        recyclerViewSat = (RecyclerView) binding.recycViewMealListSat;
        recyclerViewSun = (RecyclerView) binding.recycViewMealListSun;
        recyclerViewMon = (RecyclerView) binding.recycViewMealListMon;
        recyclerViewTue = (RecyclerView) binding.recycViewMealListTue;
        recyclerViewWed = (RecyclerView) binding.recycViewMealListWed;
        recyclerViewThu = (RecyclerView) binding.recycViewMealListThu;
        recyclerViewFri = (RecyclerView) binding.recycViewMealListFri;
    }


    // implementation of HomeView methods

    @Override
    public void showData(List<Meal> meals) {
//        calendarAdapter.setList(meals);
//        calendarAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSatMeals(List<Meal> meals) {
        calendarAdapterSat.setList(meals);
        calendarAdapterSat.notifyDataSetChanged();
    }

    @Override
    public void showSunMeals(List<Meal> meals) {
        calendarAdapterSun.setList(meals);
        calendarAdapterSun.notifyDataSetChanged();
    }

    @Override
    public void showMonMeals(List<Meal> meals) {
        calendarAdapterMon.setList(meals);
        calendarAdapterMon.notifyDataSetChanged();
    }

    @Override
    public void showTueMeals(List<Meal> meals) {
        calendarAdapterTue.setList(meals);
        calendarAdapterTue.notifyDataSetChanged();
    }

    @Override
    public void showWedMeals(List<Meal> meals) {
        calendarAdapterWed.setList(meals);
        calendarAdapterWed.notifyDataSetChanged();
    }

    @Override
    public void showThuMeals(List<Meal> meals) {
        calendarAdapterThu.setList(meals);
        calendarAdapterThu.notifyDataSetChanged();
    }

    @Override
    public void showFriMeals(List<Meal> meals) {
        calendarAdapterFri.setList(meals);
        calendarAdapterFri.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Implementation f OnHomeClickListener methods

    @Override
    public void onLayoutClick(Meal meal) {
//        Toast.makeText(this.getContext(), meal.toString(), Toast.LENGTH_SHORT).show();
        Intent outIntent = new Intent(getActivity(), MealDetailsActivity.class);
        outIntent.putExtra("mealDetails", meal);
        startActivity(outIntent);
    }

    @Override
    public void onAddToFavClick(Meal meal) {
        calendarPresenter.addToFav(meal);
        calendarAdapterSat.notifyDataSetChanged();
        calendarAdapterSun.notifyDataSetChanged();
        calendarAdapterMon.notifyDataSetChanged();
        calendarAdapterTue.notifyDataSetChanged();
        calendarAdapterWed.notifyDataSetChanged();
        calendarAdapterThu.notifyDataSetChanged();
        calendarAdapterFri.notifyDataSetChanged();

    }

    @Override
    public void onAddToCalendar(Meal meal) {
        Calendar calendar;
        calendar = Calendar.getInstance();

        // Get the current date
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        final String[] date = new String[1];

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                (view, year1, month1, dayOfMonth) -> {

                    calendar.set(year1, month1, dayOfMonth);
                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                    String dayOfWeekName = days[dayOfWeek - 1];
                    date[0] = dayOfWeekName;

//                    date[0] = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    calendarPresenter.addToStored(meal);
                    calendarPresenter.insertDayMealEntry(date[0], meal.getIdMeal());
                    Log.i("Date...", dayOfMonth + "/" + (month1 + 1) + "/" + year1);
                    },
                year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();

//        mealListPresenter.insertDayMealEntry(date[0], meal.getIdMeal());
    }

    @Override
    public void onRemoveFromCalendar(String day, Meal meal) {
        calendarPresenter.deleteDayMealEntry(day, meal.getIdMeal());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ((MainActivity) requireActivity()).showBottomNavBar();
    }
}