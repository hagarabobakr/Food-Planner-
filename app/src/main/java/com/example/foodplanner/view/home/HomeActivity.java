package com.example.foodplanner.view.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.RetrofitClient;
import com.example.foodplanner.presenter.home.HomePresenter;
import com.example.foodplanner.view.home.category.GetMealsFromAnyWhereActivity;
import com.example.foodplanner.view.home.details.MealDetailsActivity;
import com.example.foodplanner.view.home.popular.PopularMealAdapter;
import com.example.foodplanner.view.home.random.RandumMealAdapter;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.chip.Chip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnRecipeClickListner, HomeView, OnCatigoryClickListner {
    private static final String TAG = "Home Activity";
    private static final String MEAL = "meal";
    private RecyclerView randomMealsRecyclerView;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView populerMealsRecyclerView;
    private ChipGroup chipGroup;

    private HomePresenter presenter;

    private RandumMealAdapter randomMealAdapter;
    private PopularMealAdapter populerMealAdapter;
    // private GenericMealAdapter popularMealAdapter;
    private CategoryAdapter categoryAdapter;

    LinearLayoutManager randomMealsLayoutManager;
    LinearLayoutManager catigoryItemLayoutManager;
    LinearLayoutManager populerMealsLayoutManager;

    //GUEST MODE
    private boolean isGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Check if user is a guest
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        isGuest = prefs.getBoolean("is_guest", false);

        // Initialize RecyclerViews
        randomMealsRecyclerView = findViewById(R.id.recycler_random_meals);
        categoriesRecyclerView = findViewById(R.id.recycler_categories);
        populerMealsRecyclerView = findViewById(R.id.recycler_popular_meals);
        chipGroup = findViewById(R.id.chipGroup);
        // Setup LayoutManagers
        //RANDOM MEAL
        randomMealsLayoutManager = new LinearLayoutManager(this);
        randomMealsLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        randomMealsRecyclerView.setLayoutManager(randomMealsLayoutManager);
        //POPULAR MEAL
        populerMealsLayoutManager = new LinearLayoutManager(this);
        populerMealsLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        populerMealsRecyclerView.setLayoutManager(populerMealsLayoutManager);
        //CATEGORY ITEMS
        catigoryItemLayoutManager = new LinearLayoutManager(this);
        catigoryItemLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoriesRecyclerView.setLayoutManager(catigoryItemLayoutManager);
        // Initialize Adapters
        //RANDOM ADAPTER
        randomMealAdapter = new RandumMealAdapter(new ArrayList<>(), this, this);
        randomMealsRecyclerView.setAdapter(randomMealAdapter);
        //CATEGORY ADAPTER
        categoryAdapter = new CategoryAdapter(new ArrayList<>(), this, this);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        //POPULAR ADAPTER
        populerMealAdapter = new PopularMealAdapter(new ArrayList<>(), this, this);
        populerMealsRecyclerView.setAdapter(populerMealAdapter);
        presenter = new HomePresenter(this, RetrofitClient.getRetrofitInstance());
        presenter.getRandumMeal();
        presenter.getMealsByFirstLitter("B");
        presenter.getCatigoryIteams();
        presenter.getCountries();
    }

    @Override
    public void onRecipeClickListner(MealsItem meal) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            presenter.getMealDetails(meal.getIdMeal());
        }
    }

    @Override
    public void showRandomMeals(List<MealsItem> meals) {
        randomMealAdapter = new RandumMealAdapter(meals, this, this);
        randomMealsRecyclerView.setAdapter(randomMealAdapter);
        randomMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPopularMeals(List<MealsItem> meals) {
        populerMealAdapter = new PopularMealAdapter(meals, this, this);
        populerMealsRecyclerView.setAdapter(populerMealAdapter);
        populerMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCategories(List<CategoriesItem> categories) {
        Log.i(TAG, "showCategories: ");
        categoryAdapter = new CategoryAdapter(categories, this, this);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMealDetails(MealsItem mealsItem) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            Intent intent = new Intent(this, MealDetailsActivity.class);
            intent.putExtra(MEAL, mealsItem);
            startActivity(intent);
        }
    }

    @Override
    public void showChips(List<String> areas) {
        chipGroup.removeAllViews();
        for (String area : areas) {
            Chip chip = new Chip(this);
            chip.setText(area);
            chip.setTextColor(R.color.chip_ripple_color);
            chip.setRippleColorResource(R.color.blue);
            chip.setChipStrokeColorResource(R.color.blue);
            chip.setElevation(10);
            chip.setChipBackgroundColorResource(R.color.white);
            // Set an OnClickListener for the chip
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isGuest) {
                        showGuestRestrictionMessage();
                    } else {
                        String chipText = ((Chip) v).getText().toString();
                        presenter.getMealsByCountry(chipText);
                    }
                }
            });
            chipGroup.addView(chip);

        }
    }

    @Override
    public void showMealsByCategory(List<MealsItem> meals) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            Intent intent = new Intent(this, GetMealsFromAnyWhereActivity.class);
            intent.putExtra(MEAL, (Serializable) meals);
            startActivity(intent);
        }

    }

    @Override
    public void showMealsByCountry(List<MealsItem> meals) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            Intent intent = new Intent(this, GetMealsFromAnyWhereActivity.class);
            intent.putExtra(MEAL, (Serializable) meals);
            startActivity(intent);
        }
    }

    @Override
    public void onCatigoryClickListner(CategoriesItem categoriesItem) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            presenter.getMealsByCategorie(categoriesItem.getStrCategory());
        }
    }
    private void showGuestRestrictionMessage() {
        Toast.makeText(this, "Login is required to access this feature", Toast.LENGTH_LONG).show();
    }
}