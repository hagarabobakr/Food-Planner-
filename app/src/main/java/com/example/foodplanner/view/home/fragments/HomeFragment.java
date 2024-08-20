package com.example.foodplanner.view.home.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.RetrofitClient;
import com.example.foodplanner.presenter.home.HomePresenter;
import com.example.foodplanner.view.home.CategoryAdapter;
import com.example.foodplanner.view.home.HomeView;
import com.example.foodplanner.view.home.OnCatigoryClickListner;
import com.example.foodplanner.view.home.OnRecipeClickListner;
import com.example.foodplanner.view.home.category.GetMealsFromAnyWhereActivity;
import com.example.foodplanner.view.home.details.MealDetailsActivity;
import com.example.foodplanner.view.home.popular.PopularMealAdapter;
import com.example.foodplanner.view.home.random.RandumMealAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnRecipeClickListner, HomeView, OnCatigoryClickListner {

    private static final String TAG = "Home Fragment";
    private static final String MEAL = "meal";

    private RecyclerView randomMealsRecyclerView;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView populerMealsRecyclerView;
    private ChipGroup chipGroup;

    private HomePresenter presenter;
    private RandumMealAdapter randomMealAdapter;
    private PopularMealAdapter populerMealAdapter;
    private CategoryAdapter categoryAdapter;

    private boolean isGuest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Check if user is a guest
        SharedPreferences prefs = requireActivity().getSharedPreferences("user_prefs", getContext().MODE_PRIVATE);
        isGuest = prefs.getBoolean("is_guest", false);

        // Initialize RecyclerViews
        randomMealsRecyclerView = view.findViewById(R.id.recycler_random_meals);
        categoriesRecyclerView = view.findViewById(R.id.recycler_categories);
        populerMealsRecyclerView = view.findViewById(R.id.recycler_popular_meals);
        chipGroup = view.findViewById(R.id.chipGroup);

        // Setup LayoutManagers
        randomMealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        populerMealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Initialize Adapters
        randomMealAdapter = new RandumMealAdapter(new ArrayList<>(),  this,getContext());
        randomMealsRecyclerView.setAdapter(randomMealAdapter);

        categoryAdapter = new CategoryAdapter(new ArrayList<>(), this,getContext());
        categoriesRecyclerView.setAdapter(categoryAdapter);

        populerMealAdapter = new PopularMealAdapter(new ArrayList<>(), this,getContext());
        populerMealsRecyclerView.setAdapter(populerMealAdapter);

        // Initialize Presenter
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
        randomMealAdapter = new RandumMealAdapter(meals, this, getContext());
        randomMealsRecyclerView.setAdapter(randomMealAdapter);
        randomMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPopularMeals(List<MealsItem> meals) {
        populerMealAdapter = new PopularMealAdapter(meals, this, getContext());
        populerMealsRecyclerView.setAdapter(populerMealAdapter);
        populerMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCategories(List<CategoriesItem> categories) {
        Log.i(TAG, "showCategories: ");
        categoryAdapter = new CategoryAdapter(categories, this, getContext() );
        categoriesRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMealDetails(MealsItem mealsItem) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            Intent intent = new Intent(getContext(), MealDetailsActivity.class);
            intent.putExtra(MEAL, mealsItem);
            startActivity(intent);
        }
    }

    @Override
    public void showChips(List<String> areas) {
        chipGroup.removeAllViews();
        for (String area : areas) {
            Chip chip = new Chip(getContext());
            chip.setText(area);
            chip.setTextColor(R.color.chip_ripple_color);
            chip.setRippleColorResource(R.color.blue);
            chip.setChipStrokeColorResource(R.color.blue);
            chip.setElevation(10);
            chip.setChipBackgroundColorResource(R.color.white);
            chip.setOnClickListener(v -> {
                if (isGuest) {
                    showGuestRestrictionMessage();
                } else {
                    String chipText = ((Chip) v).getText().toString();
                    presenter.getMealsByCountry(chipText);
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
            Intent intent = new Intent(getContext(), GetMealsFromAnyWhereActivity.class);
            intent.putExtra(MEAL, (Serializable) meals);
            startActivity(intent);
        }
    }

    @Override
    public void showMealsByCountry(List<MealsItem> meals) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            Intent intent = new Intent(getContext(), GetMealsFromAnyWhereActivity.class);
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
        Toast.makeText(getContext(), "Login is required to access this feature", Toast.LENGTH_LONG).show();
    }
}