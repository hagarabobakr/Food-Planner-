package com.example.foodplanner.view.home.category;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.RetrofitClient;
import com.example.foodplanner.presenter.home.details.GetMealsFromAnyWherePresenter;
import com.example.foodplanner.view.home.OnRecipeClickListner;
import com.example.foodplanner.view.home.details.MealDetailsActivity;

import java.util.List;

public class GetMealsFromAnyWhereActivity extends AppCompatActivity implements OnRecipeClickListner, GetMealFromAnyWhereView {
    private static final String MEAL = "meal";
    private GetMealsFromAnyWherePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meals_by_category);
        presenter = new GetMealsFromAnyWherePresenter(this, RetrofitClient.getRetrofitInstance());
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MEAL)) {
            List<MealsItem> meals = (List<MealsItem>) intent.getSerializableExtra(MEAL);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(gridLayoutManager);

            MealsAdapter adapter = new MealsAdapter(meals,this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onRecipeClickListner(MealsItem meal) {
        presenter.getMealDetails(meal.getIdMeal());
    }

    @Override
    public void showMealDetails(MealsItem mealsItem) {
        Intent intent = new Intent(this, MealDetailsActivity.class);
        intent.putExtra(MEAL, mealsItem);
        startActivity(intent);
    }

    @Override
    public void showError(String noMealDetailsFound) {
        Toast.makeText(this,noMealDetailsFound,Toast.LENGTH_LONG).show();
    }
}