package com.example.dishdiary.features.home.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdiary.features.meal_details.view.MealDetailsActivity;
import com.example.dishdiary.databinding.FragmentHomeBinding;
import com.example.dishdiary.features.home.presenter.HomePresenter;
import com.example.dishdiary.model.Meal;

import java.util.List;

import com.example.dishdiary.features.home.presenter.HomePresenterImpl;
import com.example.dishdiary.model.MealsRepositoryImpl;
import com.example.dishdiary.datasources.network.MealRemoteDataSourceImpl;
import com.example.dishdiary.datasources.db.MealLocalDataSourceImpl;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnHomeClickListener, HomeView{

    private static final String TAG = "HomeFragment";

    // Presenter
    HomePresenter homePresenter;

    // UI
    private RecyclerView recycHome;
    private HomeAdapter homeAdapter;
    private LinearLayoutManager layoutManager;

    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        // Presenter
        homePresenter = new HomePresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(getActivity())
                )
        );

        homePresenter.getRandomMeal();

        // UI
        recycHome.setHasFixedSize(true);
        homeAdapter = new HomeAdapter(this.getContext(), new ArrayList<Meal>(), this);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycHome.setLayoutManager(layoutManager);
        recycHome.setAdapter(homeAdapter);

    }

    private void initUI() {
        recycHome = (RecyclerView) binding.recycHome;
    }


    // implementation of HomeView methods

    @Override
    public void showData(List<Meal> meals) {
        homeAdapter.setList(meals);
        homeAdapter.notifyDataSetChanged();
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
        homePresenter.addToFav(meal);
    }

    @Override
    public void openDetails() {
        Intent outIntent = new Intent(getActivity(), MealDetailsActivity.class);
        outIntent.putExtra("data", "hi from home");
        startActivity(outIntent);
    }
}