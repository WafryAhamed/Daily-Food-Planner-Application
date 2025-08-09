package com.example.dishdiary.features.meal_list.view;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdiary.MainActivity;
import com.example.dishdiary.R;
import com.example.dishdiary.databinding.FragmentMeallistBinding;
import com.example.dishdiary.datasources.db.MealLocalDataSourceImpl;
import com.example.dishdiary.features.meal_details.view.MealDetailsActivity;
import com.example.dishdiary.features.meal_list.presenter.MealListPresenter;
import com.example.dishdiary.features.meal_list.presenter.MealListPresenterImpl;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepositoryImpl;
import com.example.dishdiary.datasources.network.MealRemoteDataSourceImpl;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MealListFragment extends Fragment implements OnMealListClickListener, MealListView {

    private FragmentMeallistBinding binding;

    private static final String TAG = "MealListFragment";

    // Incoming Data
    String filterBy, value;

    // Presenter
    MealListPresenter mealListPresenter;

    // UI
    private RecyclerView recyclerView;
    private MealListAdapter mealListAdapter;
    private LinearLayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMeallistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        ((MainActivity) requireActivity()).hideBottomNavBar();

        // Sharing data
        MealListViewModel mealListViewModel = new ViewModelProvider(requireActivity()).get(MealListViewModel.class);
        mealListViewModel.getMealsList().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if(meals != null)
                    showData(meals);
                else
                    Toast.makeText(getContext(), "No meals found", Toast.LENGTH_SHORT).show();
            }
        });

        ///
//        DashboardViewModel dashboardViewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);


//        final Button btn = binding.btn;
//        btn.setOnClickListener(view -> dashboardViewModel.setText(txtDash.getText().toString()));

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // disable Navigation bar

        initUI();

        // Presenter
        mealListPresenter = new MealListPresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(getActivity())
                )
        );


        // UI
        recyclerView.setHasFixedSize(true);
        mealListAdapter = new MealListAdapter(this.getContext(), new ArrayList<Meal>(), this);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mealListAdapter);
    }

    private void initUI() {
        recyclerView = (RecyclerView) binding.recycViewMealList;
    }


    // implementation of HomeView methods

    @Override
    public void showData(List<Meal> meals) {
        mealListAdapter.setList(meals);
        mealListAdapter.notifyDataSetChanged();
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
        mealListPresenter.addToFav(meal);
        mealListAdapter.notifyDataSetChanged();
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

                    mealListPresenter.addToStored(meal);
                    mealListPresenter.insertDayMealEntry(date[0], meal.getIdMeal());
                    Log.i("Date...", dayOfMonth + "/" + (month1 + 1) + "/" + year1);
                    },
                year, month, day);

        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ((MainActivity) requireActivity()).showBottomNavBar();
    }
}