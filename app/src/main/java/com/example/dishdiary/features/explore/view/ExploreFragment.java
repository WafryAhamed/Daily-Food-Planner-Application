package com.example.dishdiary.features.explore.view;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.dishdiary.R;
import com.example.dishdiary.databinding.FragmentExploreBinding;
import com.example.dishdiary.datasources.db.MealLocalDataSourceImpl;
import com.example.dishdiary.datasources.network.MealRemoteDataSourceImpl;
import com.example.dishdiary.features.explore.presenter.ExplorePresenterImpl;
import com.example.dishdiary.features.meal_details.view.MealDetailsActivity;
import com.example.dishdiary.features.meal_list.presenter.MealListPresenterImpl;
import com.example.dishdiary.features.meal_list.view.MealListAdapter;
import com.example.dishdiary.features.meal_list.view.MealListViewModel;
import com.example.dishdiary.model.Area;
import com.example.dishdiary.model.Category;
import com.example.dishdiary.model.Ingredient;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepositoryImpl;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExploreFragment extends Fragment implements OnExploreClickListener, ExploreView {

    private FragmentExploreBinding binding;

    // MealList view model
    MealListViewModel mealListViewModel;

    // Presenter
    ExplorePresenterImpl explorePresenter;

    // Maintaining the selected navigation bar selected item
    int selectedItemId;
    BottomNavigationView bottomNavigationView;

    // UI - Category List
    private RecyclerView recyclerView;
    private ExploreAdapter exploreAdapter;
    private GridLayoutManager layoutManager;

    // UI - Areas List
    private RecyclerView areaRecyclerView;
    private ExploreAreasAdapter exploreAreasAdapter;
    private LinearLayoutManager areaLayoutManager;

    // UI - Ingredient List
    private RecyclerView ingredientRecyclerView;
    private ExploreIngredientsAdapter exploreIngredientsAdapter;
    private LinearLayoutManager ingredientLayoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExploreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Presenter
        explorePresenter = new ExplorePresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(getActivity())
                )
        );

        // View Model
        ExploreViewModel exploreViewModel = new ViewModelProvider(requireActivity()).get(ExploreViewModel.class);

        // Auto Complete Text View for Search
        final AutoCompleteTextView autoCompSearch = binding.autoCompSearch;

        String[] mealsNames = {
                "Apple Frangipan Tart", "Apple & Blackberry Crumble", "Apam balik", "Ayam Percik",
                "Bakewell tart", "Bread and Butter Pudding", "Beef Wellington", "Baingan Bharta", "Beef Brisket Pot Roast", "Beef Sunday Roast", "Braised Beef Chilli", "Beef stroganoff", "Broccoli & Stilton soup", "Bean & Sausage Hotpot", "Banana Pancakes", "Beef Dumpling Stew", "Beef and Mustard Pie", "Beef and Oyster pie", "Blackberry Fool", "Battenberg Cake", "Beef Bourguignon", "Brie wrapped in prosciutto & brioche", "Boulangère Potatoes", "BeaverTails", "Brown Stew Chicken", "Beef Lo Mein", "Baked salmon with fennel & tomatoes", "Budino Di Ricotta", "Breakfast Potatoes", "Bitterballen (Dutch meatballs)", "BBQ Pork Sloppy Joes", "Beef Banh Mi Bowls with Sriracha Mayo", "Carrot & Pickled Cucumber", "Big Mac", "Bigos (Hunters Stew)", "Boxty Breakfast", "Beef Rendang", "Burek", "Beef Mechado", "Bistek", "Beef Caldereta", "Beef Asado", "Bread omelette", "Beetroot Soup (Borscht)", "Blini Pancakes",
                "Chicken Enchilada Casserole", "Chocolate Gateau", "Cream Cheese Tart", "Christmas Pudding Flapjack", "Chicken Handi", "Chicken Alfredo Primavera", "Chicken Fajita Mac and Cheese", "Cajun spiced fish tacos", "Crock Pot Chicken Baked Tacos", "Chicken Karaage", "Coq au vin", "Chilli prawn linguine", "Clam chowder", "Creamy Tomato Soup", "Chicken & mushroom Hotpot", "Chicken Couscous", "Chocolate Avocado Mousse", "Choc Chip Pecan Pie", "Chocolate Raspberry Brownies", "Chickpea Fajitas", "Chicken Ham and Leek Pie", "Chicken Parmentier", "Carrot Cake", "Chelsea Buns", "Chocolate Souffle", "Chinon Apple Tarts", "Chicken Marengo", "Canadian Butter Tarts", "Chicken Basquaise", "Callaloo Jamaican Style", "Chicken Congee", "Chocolate Caramel Crispy", "Chakchouka", "Cashew Ghoriba Biscuits", "Corba", "Classic Christmas pudding", "Christmas Pudding Trifle", "Christmas cake", "Corned Beef and Cabbage", "Crispy Sausages and Greens", "Chicken Quinoa Greek Salad", "Chick-Fil-A Sandwich", "Coddled pork with cider", "Cevapi Sausages", "Croatian lamb peka", "Croatian Bean Stew", "Chivito uruguayo", "Crispy Eggplant", "Cabbage Soup (Shchi)",
                "Dal fry", "Dundee cake", "Duck Confit",
                "Eton Mess", "Eccles Cakes", "English Breakfast", "Escovitch Fish", "Egg Drop Soup", "Egyptian Fatteh", "Eggplant Adobo",
                "Fish pie", "French Lentils With Garlic and Thyme", "Fettucine alfredo", "Full English Breakfast", "French Onion Soup", "Flamiche", "French Omelette", "Fish Stew with Rouille", "Fennel Dauphinoise", "Fruit and Cream Cheese Breakfast Pastries", "French Onion Chicken with Roasted Carrots & Mashed Potatoes", "Ful Medames", "Feteer Meshaltet", "Fish fofos", "Fresh sardines", "Fettuccine Alfredo", "Fish Soup (Ukha)",
                "Garides Saganaki", "Grilled Mac and Cheese Sandwich", "General Tso's Chicken", "Gigantes Plaki", "Gołąbki (cabbage roll)", "Grilled Portuguese sardines", "Grilled eggplant with coconut milk",
                "Honey Teriyaki Salmon", "Hot Chocolate Fudge", "Hot and Sour Soup", "Home-made Mandazi", "Honey Balsamic Chicken with Crispy Broccoli & Potatoes", "Honey Yogurt Cheesecake", "Ham hock colcannon",
                "Irish stew",
                "Jam Roly-Poly", "Jerk chicken with rice & peas", "Jamaican Beef Patties", "Japanese gohan rice", "Japanese Katsudon",
                "Kapsalon", "Kentucky Fried Chicken", "Katsu Chicken curry", "Key Lime Pie", "Kidney Bean Curry", "Kedgeree", "Kung Pao Chicken", "Kung Po Prawns", "Kafteji", "Keleya Zaara", "Kumpir", "Krispy Kreme Donut", "Koshari",
                "Lamb tomato and sweet spices", "Lamb Biryani", "Lamb Rogan josh", "Laksa King Prawn Noodles", "Lamb Tagine", "Lasagne", "Lamb and Potato pie", "Lancashire hotpot", "Leblebi Soup", "Lasagna Sandwiches", "Lamb and Lemon Souvlaki", "Lamb Tzatziki Burgers", "Lamb Pilaf (Plov)",
                "Mediterranean Pasta Salad", "Massaman Beef curry", "Mushroom & Chestnut Rotolo", "Matar Paneer", "Minced Beef Pie", "McSinghs Scotch pie", "Madeira Cake", "Montreal Smoked Meat", "Ma Po Tofu", "Mbuzi Choma (Roasted Goat)", "Mince Pies", "Moussaka", "Mulukhiyah", "Mustard champ", "Moroccan Carrot Soup", "Mee goreng mamak", "Mushroom soup with buckwheat",
                "Nutty Chicken Curry", "New York cheesecake", "Nanaimo Bars", "Nasi lemak",
                "Osso Buco alla Milanese", "Oxtail with broad beans",
                "Pad See Ew", "Potato Gratin with Chicken", "Poutine", "Pilchard puttanesca", "Pork Cassoulet", "Pancakes", "Pumpkin Pie", "Peanut Butter Cheesecake", "Peach & Blueberry Grunt", "Parkin Cake", "Pear Tarte Tatin", "Provençal Omelette Cake", "Prawn & Fennel Bisque", "Pate Chinois", "Pouding chomeur", "Peanut Butter Cookies", "Pizza Express Margherita", "Paszteciki (Polish Pasties)", "Pierogi (Polish Dumplings)", "Polskie Naleśniki (Polish Pancakes)", "Piri-piri chicken and slaw", "Portuguese prego with green piri-piri", "Portuguese barbecued pork (Febras assadas)", "Portuguese fish stew (Caldeirada de peixe)", "Portuguese custard tarts", "Potato Salad (Olivier Salad)",
                "Rigatoni with fennel sausage sauce", "Rocky Road Fudge", "Recheado Masala Fish", "Ribollita", "Roasted Eggplant With Tahini", "Pine Nuts, and Lentils", "Rock Cakes", "Ratatouille", "Rappie Pie", "Red Peas Soup", "Roast fennel and aubergine paella", "Rosół (Polish Chicken Soup)", "Rogaliki (Polish Croissant Cookies)", "Roti john",
                "Spaghetti Bolognese", "Spicy Arrabiata Penne", "Smoky Lentil Chili with Squash", "Sticky Toffee Pudding Ultimate", "Spicy North African Potato Salad", "Stovetop Eggplant With Harissa, Chickpeas, and Cumin Yogurt", "Salmon Prawn Risotto", "Salted Caramel Cheescake", "Seafood fideuà", "Spinach & Ricotta Cannelloni", "Squash linguine", "Spanish Tortilla", "Steak and Kidney Pie", "Sticky Toffee Pudding", "Spotted Dick", "Summer Pudding", "Summer Pistou", "Split Pea Soup", "Sugar Pie", "Steak Diane", "Saltfish and Ackee", "Sweet and Sour Pork", "Szechuan Beef", "Shrimp Chow Fun", "Salmon Avocado Salad", "Salmon Eggs Eggs Benedict", "Shakshuka", "Smoked Haddock Kedgeree", "Stamppot", "Snert (Dutch Split Pea Soup)", "Spaghetti alla Carbonara", "Soy-Glazed Meatloaves with Wasabi Mashed Potatoes & Roasted Carrots", "Skillet Apple Pork Chops with Roasted Sweet Potatoes & Zucchini", "Strawberry Rhubarb Pie", "Stuffed Lamb Tomatoes", "Sledz w Oleju (Polish Herrings)", "Shawarma", "Spring onion and prawn empanadas", "Seri muka kuih", "Sushi", "Stuffed Bell Peppers with Quinoa and Black Beans", "Strawberries Romanoff",
                "Teriyaki Chicken Casserole", "Tandoori chicken", "Thai Green Curry", "Toad In The Hole", "Turkey Meatloaf", "Tuna Nicoise", "Tahini Lentils", "Three Fish Pie", "Treacle Tart", "Tarte Tatin", "Three-cheese souffles", "Tourtiere", "Timbits", "Tunisian Orange Cake", "Tunisian Lamb Soup", "Tuna and Egg Briks", "Tamiya", "Tonkatsu pork", "Traditional Croatian Goulash", "Tortang Talong",
                "Vegan Lasagna", "Vegan Chocolate Cake", "Vietnamese Grilled Pork (bun-thit-nuong)", "Venetian Duck Ragu", "Vegetarian Casserole", "Vegetarian Chilli", "Vegetable Shepherd's Pie",
                "White chocolate creme brulee", "Wontons", "Walnut Roll Gužvara",
                "Yaki Udon",
                "15-minute chicken & halloumi burgers"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_dropdown_item_1line, mealsNames);
        autoCompSearch.setAdapter(adapter);


        // Share data with MealList Fragment through its View Model
        mealListViewModel = new ViewModelProvider(requireActivity()).get(MealListViewModel.class);

        // Search Button
        final FloatingActionButton floatBtnSearch = binding.floatBtnSearch;
        floatBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explorePresenter.filterByName(autoCompSearch.getText().toString());
            }
        });

        // Navigation bar
        bottomNavigationView = getActivity().findViewById(R.id.nav_view);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        initUI();

        // List Categories
        explorePresenter.getAllCategories();

        // Categories - UI
        recyclerView.setHasFixedSize(true);
        exploreAdapter = new ExploreAdapter(this.getContext(), new ArrayList<Category>(), this);
        layoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(exploreAdapter);


        // List Areas
        explorePresenter.getAllAreas();

        // Categories - UI
        areaRecyclerView.setHasFixedSize(true);
        exploreAreasAdapter = new ExploreAreasAdapter(this.getContext(), new ArrayList<Area>(), this);
        areaLayoutManager = new LinearLayoutManager(this.getContext());
        areaLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        areaRecyclerView.setLayoutManager(areaLayoutManager);
        areaRecyclerView.setAdapter(exploreAreasAdapter);


        // List Ingredients
        explorePresenter.getAllIngredients();

        // Ingredients - UI
        ingredientRecyclerView.setHasFixedSize(true);
        exploreIngredientsAdapter = new ExploreIngredientsAdapter(this.getContext(), new ArrayList<Ingredient>(), this);
        ingredientLayoutManager = new LinearLayoutManager(this.getContext());
        ingredientLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        ingredientRecyclerView.setLayoutManager(ingredientLayoutManager);
        ingredientRecyclerView.setAdapter(exploreIngredientsAdapter);
    }

    private void initUI() {
        recyclerView = (RecyclerView) binding.recycCategories;
        areaRecyclerView = (RecyclerView) binding.recycAreas;
        ingredientRecyclerView = (RecyclerView) binding.recycIngredients;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCategoryLayoutClick(Category category) {
        explorePresenter.filterByCategory(category.getStrCategory());
    }

    @Override
    public void onAreaClick(Area area) {
        explorePresenter.filterByArea(area.getStrArea());
    }

    @Override
    public void onIngredientClick(Ingredient ingredient) {
        explorePresenter.filterByIngredient(ingredient.getStrIngredient());
    }

    @Override
    public void onSearchClick() {

    }


    @Override
    public void showData(List<Meal> meals) {
        mealListViewModel.setMealsList(meals);
        NavController navController = NavHostFragment.findNavController(getParentFragment());
        navController.navigate(R.id.action_navigation_explore_to_navigation_meallist);

        selectedItemId = bottomNavigationView.getSelectedItemId();
        bottomNavigationView.setSelectedItemId(selectedItemId);
    }

    @Override
    public void showCategories(List<Category> categories) {
        exploreAdapter.setList(categories);
        exploreAdapter.notifyDataSetChanged();
    }


    @Override
    public void showAreas(List<Area> areas) {
        exploreAreasAdapter.setList(areas);
        exploreAreasAdapter.notifyDataSetChanged();
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        exploreIngredientsAdapter.setList(ingredients);
        exploreIngredientsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        Toast.makeText(getContext(), "No results found", Toast.LENGTH_SHORT).show();
    }
}