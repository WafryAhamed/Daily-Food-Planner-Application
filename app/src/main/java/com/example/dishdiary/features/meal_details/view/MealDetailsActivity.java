package com.example.dishdiary.features.meal_details.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdiary.R;
import com.example.dishdiary.datasources.db.MealLocalDataSourceImpl;
import com.example.dishdiary.datasources.network.MealRemoteDataSourceImpl;
import com.example.dishdiary.features.explore.view.ExploreIngredientsAdapter;
import com.example.dishdiary.features.meal_details.presenter.MealDetailsPresenter;
import com.example.dishdiary.features.meal_details.presenter.MealDetailsPresenterImpl;
import com.example.dishdiary.features.meal_list.presenter.MealListPresenterImpl;
import com.example.dishdiary.model.Ingredient;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepositoryImpl;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dishdiary.databinding.ActivityMealDetailsBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsView, OnMealDetailsClickListener {

    private ActivityMealDetailsBinding binding;

    private MealDetailsPresenter mealDetailsPresenter;

    Meal meal;

    private ImageView imgView;//, imgDetCategory;
    private CircleImageView imgDetArea, imgDetCategory;
    private TextView txtName, txtCategory, txtArea, txtInstructions;
    private Button btnFav;
    private WebView webView;

    // Flags
    private Map<String, String> flags;
    private String flagsBaseUrl = "https://flagcdn.com/w80/";

    // Category
    private String categoryBaseUrl = "https://www.themealdb.com/images/category/";

    // UI - Ingredient List
    private RecyclerView ingredientRecyclerView;
    private DetailsIngredientsAdapter detailsIngredientsAdapter;
    private LinearLayoutManager ingredientLayoutManager;

    private List<Ingredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMealDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.flags = new HashMap<>();

        flags.put("American", "us");
        flags.put("British", "gb");
        flags.put("Canadian", "ca");
        flags.put("Chinese", "cn");
        flags.put("Croatian", "hr");
        flags.put("Dutch", "nl");
        flags.put("Egyptian", "eg");
        flags.put("Filipino", "ph");
        flags.put("French", "fr");
        flags.put("Greek", "gr");
        flags.put("Indian", "in");
        flags.put("Irish", "ie");
        flags.put("Italian", "it");
        flags.put("Jamaican", "jm");
        flags.put("Japanese", "jp");
        flags.put("Kenyan", "ke");
        flags.put("Malaysian", "my");
        flags.put("Mexican", "mx");
        flags.put("Moroccan", "ma");
        flags.put("Polish", "pl");
        flags.put("Portuguese", "pt");
        flags.put("Russian", "ru");
        flags.put("Spanish", "es");
        flags.put("Thai", "th");
        flags.put("Tunisian", "tn");
        flags.put("Turkish", "tr");
        flags.put("Ukrainian", "ua");
        flags.put("Vietnamese", "vn");

        // Presenter
        mealDetailsPresenter = new MealDetailsPresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(this)
                )
        );

        // UI - Floating Favourite Button
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mealDetailsPresenter.addToFav(meal);
                Snackbar.make(view, "Meal Added To Favourites", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mealDetailsPresenter.removeFromFav(meal); // Example action
                                Toast.makeText(view.getContext(), "Meal removed from favourites", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setAnchorView(R.id.fab).show();
            }
        });

        // UI - Floating Favourite Button
        FloatingActionButton fabCal = binding.fabCal;
        fabCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                        MealDetailsActivity.this,
                        (view1, year1, month1, dayOfMonth) -> {
                            calendar.set(year1, month1, dayOfMonth);
                            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                            String dayOfWeekName = days[dayOfWeek - 1];
                            date[0] = dayOfWeekName;

                            mealDetailsPresenter.addToStored(meal);
                            mealDetailsPresenter.insertDayMealEntry(date[0], meal.getIdMeal());
                            Log.i("Date...", dayOfMonth + "/" + (month1 + 1) + "/" + year1);
                        },
                        year, month, day);

                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        // UI - Meal Details Components
        imgView = binding.imgView;
        imgDetCategory = binding.scroll.imgDetCategory;
        imgDetArea = binding.scroll.imgDetArea;

        txtCategory = binding.scroll.txtCategory;
        txtArea = binding.scroll.txtArea;
        txtInstructions = binding.scroll.txtInstructions;
        ingredientRecyclerView = (RecyclerView) binding.scroll.recycDetailsIngredients;
        webView = binding.scroll.webView;

        // Ingredients
        ingredientList = new ArrayList<Ingredient>();
        ingredientRecyclerView.setHasFixedSize(true);
        detailsIngredientsAdapter = new DetailsIngredientsAdapter(this, new ArrayList<Ingredient>(), this);
        ingredientLayoutManager = new LinearLayoutManager(this);
        ingredientLayoutManager.setOrientation(RecyclerView.VERTICAL);
        ingredientRecyclerView.setLayoutManager(ingredientLayoutManager);


        Meal meal = (Meal) getIntent().getSerializableExtra("mealDetails");
        if (meal != null) {
            mealDetailsPresenter.getMealById(meal.getIdMeal());
        }
    }

    private String getEmbedUrl(String url) {
//        String videoId = null;
//        videoId = url.substring(url.indexOf("="));
//        return "https://www.youtube.com/embed/" + videoId + "?autoplay=0";
        return url.replace("https://www.youtube.com/watch?v=", "https://www.youtube.com/embed/") +
                (url.contains("&") ? url.substring(url.indexOf("&")) : "");
    }

    @Override
    public void showData(Meal meal) {
        this.meal = meal;
        if (meal != null) {
            // UI - Toolbar
            Toolbar toolbar = binding.toolbar;
            setSupportActionBar(toolbar);
            CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;

            toolBarLayout.setTitle(meal.getStrMeal());
            toolbar.setTitleTextAppearance(this, R.style.Theme_DishDiary);
            txtCategory.setText(meal.getStrCategory());
            txtArea.setText(meal.getStrArea());
            txtInstructions.setText(meal.getStrInstructions());

            Glide.with(this).load(meal.getStrMealThumb())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(imgView);


            if (!meal.getStrArea().equals("Unknown")) {
                Glide.with(this).load(flagsBaseUrl + flags.get(meal.getStrArea()) + ".png")
                        .apply(new RequestOptions().override(100, 100)
                                .placeholder(R.drawable.ic_launcher_foreground)
                                .error(R.drawable.ic_launcher_foreground))
                        .into(imgDetArea);
            } else {
                imgDetArea.setImageResource(R.drawable.ic_launcher_foreground);
            }


            Glide.with(this).load(categoryBaseUrl + meal.getStrCategory() + ".png")
                    .apply(new RequestOptions().override(100, 100)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground))
                    .into(imgDetCategory);


            // Constructing the ingredients list
            if (!meal.getStrIngredient1().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient1(), meal.getStrMeasure1()));
            if (!meal.getStrIngredient2().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient2(), meal.getStrMeasure2()));
            if (!meal.getStrIngredient3().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient3(), meal.getStrMeasure3()));
            if (!meal.getStrIngredient4().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient4(), meal.getStrMeasure4()));
            if (!meal.getStrIngredient5().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient5(), meal.getStrMeasure5()));
            if (!meal.getStrIngredient6().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient6(), meal.getStrMeasure6()));
            if (!meal.getStrIngredient7().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient7(), meal.getStrMeasure7()));
            if (!meal.getStrIngredient8().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient8(), meal.getStrMeasure8()));
            if (!meal.getStrIngredient9().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient9(), meal.getStrMeasure9()));
            if (!meal.getStrIngredient10().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient10(), meal.getStrMeasure10()));
            if (!meal.getStrIngredient11().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient11(), meal.getStrMeasure11()));
            if (!meal.getStrIngredient12().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient12(), meal.getStrMeasure12()));
            if (!meal.getStrIngredient13().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient13(), meal.getStrMeasure13()));
            if (!meal.getStrIngredient14().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient14(), meal.getStrMeasure14()));
            if (!meal.getStrIngredient15().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient15(), meal.getStrMeasure15()));
            if (!meal.getStrIngredient16().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient16(), meal.getStrMeasure16()));
            if (!meal.getStrIngredient17().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient17(), meal.getStrMeasure17()));
            if (!meal.getStrIngredient18().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient18(), meal.getStrMeasure18()));
            if (!meal.getStrIngredient19().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient19(), meal.getStrMeasure19()));
            if (!meal.getStrIngredient20().isEmpty())
                ingredientList.add(new Ingredient(meal.getStrIngredient20(), meal.getStrMeasure20()));

            showIngredients(ingredientList);
            ingredientRecyclerView.setAdapter(detailsIngredientsAdapter);


            // Video Web View
            webView.setWebViewClient(new WebViewClient());
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            webView.loadUrl(getEmbedUrl(meal.getStrYoutube()));
        }

    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        detailsIngredientsAdapter.setList(ingredients);
        detailsIngredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {

    }

    @Override
    public void onLayoutClick(Meal meal) {

    }

    @Override
    public void onAddToFavClick(Meal meal) {

    }

    @Override
    public void onRemoveFromFavClick(Meal meal) {

    }
}