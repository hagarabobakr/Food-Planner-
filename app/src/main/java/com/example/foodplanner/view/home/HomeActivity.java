package com.example.foodplanner.view.home;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.presenter.home.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnRecipeClickListner,HomeView {
    private RecyclerView randomMealsRecyclerView;
    private RecyclerView popularMealsRecyclerView;
    private HomePresenter presenter;

    private GenericMealAdapter randomMealAdapter;
    private GenericMealAdapter popularMealAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize RecyclerViews
        randomMealsRecyclerView = findViewById(R.id.recycler_random_meals);
        popularMealsRecyclerView = findViewById(R.id.recycler_popular_meals);
        // Setup LayoutManagers
        LinearLayoutManager randomMealsLayoutManager =new LinearLayoutManager(this);
        randomMealsLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        randomMealsRecyclerView.setLayoutManager(randomMealsLayoutManager);

        LinearLayoutManager popularMealsLayoutManager =new LinearLayoutManager(this);
        popularMealsLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        randomMealsRecyclerView.setLayoutManager(popularMealsLayoutManager);
        // Initialize Adapters
        randomMealAdapter = new GenericMealAdapter(new ArrayList<>(),this,this);
        popularMealAdapter = new GenericMealAdapter(new ArrayList<>(),this,this);

        randomMealsRecyclerView.setAdapter(randomMealAdapter);
        popularMealsRecyclerView.setAdapter(popularMealAdapter);


    }

    @Override
    public void onRecipeClickListner(MealsItem meal) {

    }

    @Override
    public void showRandomMeals(List<MealsItem> meals) {

    }

    @Override
    public void showPopularMeals(List<MealsItem> meals) {

    }

    @Override
    public void showCategories(List<CategoriesItem> categories) {

    }

    @Override
    public void showError(String message) {

    }
}