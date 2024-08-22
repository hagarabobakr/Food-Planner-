package com.example.foodplanner.view.home.fragments.fav;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealItemEntity;
import com.example.foodplanner.model.database.MealsLocalDataSource;
import com.example.foodplanner.presenter.home.fav.FavMealPresenter;
import com.example.foodplanner.view.home.details.MealDetailsActivity;
import com.example.foodplanner.view.home.random.RandumMealAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment implements OnFavRecipeClickListner,FavView {
    private static final String TAG ="FavoriteFragment" ;
    private RecyclerView recyclerView;
    private FavMealAdapter favAdapter;
    private static final String MEAL = "meal";
    FavMealPresenter presenter;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.fav_recycler_view);
        // Setup LayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        // Initialize Adapters
        favAdapter = new FavMealAdapter(new ArrayList<>(),this,getContext());

        presenter = new FavMealPresenter(this, MealsLocalDataSource.getInstance(getContext()));
        presenter.getLocalMeals();

    }


    @Override
    public void onFavRecipeClickListner(MealsItem mealItem) {

    }

    @Override
    public void onDeletIcClickListner(MealsItem mealItem) {
        Log.i(TAG, "onDeletIcClickListner: ");
        presenter.removeMeal(mealItem);
        favAdapter.notifyDataSetChanged();
    }





       /* // Create an AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose an action");
        builder.setMessage("What would you like to do with this meal?");

        // Add "View Details" button
        builder.setPositiveButton("View Details", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Show meal details
                showMealDetails(mealItem);
            }
        });

        // Add "Delete" button
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete the meal
                deleteMeal(mealItem);
            }
        });

        // Add "Cancel" button
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();*/


    @Override
    public void showLocalData(LiveData<List<MealsItem>> allMeals) {

        allMeals.observe(this, new Observer<List<MealsItem>>() {
            @Override
            public void onChanged(List<MealsItem> mealItemEntities) {
                favAdapter = new FavMealAdapter(mealItemEntities,FavoriteFragment.this,getContext());
                recyclerView.setAdapter(favAdapter);
                favAdapter.notifyDataSetChanged();
            }
        });
    }
    // Method to show meal details
    private void showMealDetails(MealItemEntity mealItem) {
        Intent intent = new Intent(getContext(), MealDetailsActivity.class);
        intent.putExtra(MEAL, (Serializable) mealItem);
        startActivity(intent);
    }

    // Method to delete a meal
    private void deleteMeal(MealItemEntity mealItem) {
        //presenter.removeMeal(mealItem);
        //favAdapter.notifyDataSetChanged();
    }
}