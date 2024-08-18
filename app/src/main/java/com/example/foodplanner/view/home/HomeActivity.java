package com.example.foodplanner.view.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.RetrofitClient;
import com.example.foodplanner.presenter.home.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements OnRecipeClickListner,HomeView,OnCatigoryClickListner {
    private static final String TAG = "Home Activity";
    private RecyclerView randomMealsRecyclerView;
    private RecyclerView categoriesRecyclerView;

    private HomePresenter presenter;

    private RandumMealAdapter randomMealAdapter;
   // private GenericMealAdapter popularMealAdapter;
    private CategoryAdapter categoryAdapter;

    LinearLayoutManager randomMealsLayoutManager;
    LinearLayoutManager catigoryItemLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home );
        // Initialize RecyclerViews
        randomMealsRecyclerView = findViewById(R.id.recycler_random_meals);

        categoriesRecyclerView = findViewById(R.id.recycler_categories);
        // Setup LayoutManagers
        randomMealsLayoutManager =new LinearLayoutManager(this);
        randomMealsLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        randomMealsRecyclerView.setLayoutManager(randomMealsLayoutManager);

        catigoryItemLayoutManager =new LinearLayoutManager(this);
        catigoryItemLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoriesRecyclerView.setLayoutManager(catigoryItemLayoutManager);
        // Initialize Adapters
        randomMealAdapter = new RandumMealAdapter(new ArrayList<>(),this,this);
        categoryAdapter = new CategoryAdapter(new ArrayList<>(),this,this);
        randomMealsRecyclerView.setAdapter(randomMealAdapter);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        //popularMealsRecyclerView.setAdapter(popularMealAdapter);
        presenter = new HomePresenter(this, RetrofitClient.getRetrofitInstance());
        presenter.getRandumMeal();
        presenter.getCatigoryIteams();
    }

    @Override
    public void onRecipeClickListner(MealsItem meal) {

    }

    @Override
    public void showRandomMeals(List<MealsItem> meals) {
        randomMealAdapter = new RandumMealAdapter(meals,this,this);
        randomMealsRecyclerView.setAdapter(randomMealAdapter);
        randomMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPopularMeals(List<MealsItem> meals) {



    }

    @Override
    public void showCategories(List<CategoriesItem> categories) {
        Log.i(TAG, "showCategories: ");
        categoryAdapter = new CategoryAdapter(categories,this,this);
        categoriesRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCatigoryClickListner(CategoriesItem categoriesItem) {

    }
}