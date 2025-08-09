package com.example.dishdiary;

import android.os.Bundle;
import android.view.View;

import com.example.dishdiary.features.home.view.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dishdiary.databinding.ActivityMainBinding;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // UI
    private final String HOME_FRAGMENT = "dyn_home_frag";
    HomeFragment homeFragment;
    BottomNavigationView navView;

//    private final String PRODUCT_DETAILS_FRAGMENT = "dyn_product_details_frag";
//    ProductDetailsFrag productDetailsFrag;

    FragmentManager mgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // UI
        // Obtaining reference from the fragment support manager
//        mgr = getSupportFragmentManager();

//        if (savedInstanceState == null) {
        // creating an object of FragProductsList and ProductDetailsFrag
//            homeFragment = new HomeFragment();
//            productDetailsFrag = new ProductDetailsFrag();

//            // begin transaction
//            FragmentTransaction trns = mgr.beginTransaction();
//            // adding fragProductsList object to the fragment container view
//            trns.add(R.id.fragProductsList, productListFrag, PRODUCTS_LIST_FRAGMENT);
//            trns.add(R.id.fragProductDetails, productDetailsFrag, PRODUCT_DETAILS_FRAGMENT);
//            // commit change
//            trns.commit();

        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_explore, R.id.navigation_saved, R.id.navigation_plan, R.id.navigation_meallist)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


//        } else {
        // obtain reference from fragmentB
//            homeFragment = (HomeFragment) mgr.findFragmentByTag(HOME_FRAGMENT);
//            productDetailsFrag = (ProductDetailsFrag) mgr.findFragmentByTag(PRODUCT_DETAILS_FRAGMENT);

//        }


    }

    public void hideBottomNavBar() {
        navView.setVisibility(View.GONE);
    }

    public void showBottomNavBar() {
        navView.setVisibility(View.VISIBLE);
    }

}