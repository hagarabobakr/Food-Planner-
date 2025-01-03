package com.example.foodplanner.view.home.fragments.fav;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.source.MealsLocalDataSource;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.presenter.home.fav.FavMealPresenter;
import com.example.foodplanner.view.home.details.FavMealDetailsActivity;
import com.example.foodplanner.view.home.details.MealDetailsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
        showMealDetails(mealItem);
    }

    @Override
    public void onDeletIcClickListner(MealsItem mealItem) {
        Log.i(TAG, "onDeletIcClickListner: ");

        new AlertDialog.Builder(getActivity())
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete this meal?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.removeMeal(mealItem);
                        favAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    @Override
    public void onAddToPlanClickListner(MealsItem mealItem) {
        // Show DatePickerDialog to select date
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> {
                    // Handle selected date
                    String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                    showMealTypeDialog(mealItem, date);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }
    private void showMealTypeDialog(MealsItem mealItem, String date) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose Meal Type");

        String[] mealTypes = {"breakfast", "lunch", "dinner"};
        builder.setSingleChoiceItems(mealTypes, -1, (dialog, which) -> {
            String selectedMealType = mealTypes[which];
            handleMealSelection(mealItem, date, selectedMealType);
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void handleMealSelection(MealsItem mealItem, String date, String mealType) {
        // Process the mealItem with the selected date and meal type
        String[] dateParts = date.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        MealsItem meal = new MealsItem();
        MealPlan mealPlan = mealItem.toMealPlan(mealType, day, month, year);
        presenter.insertPlanedMeal(mealPlan);
        Toast.makeText(getContext()
                , "Meal Item: " + mealItem.getStrMeal()+ "\nDate: " + date + "\nMeal Type: " + mealType
                , Toast.LENGTH_LONG).show();
        // You can also add code here to save this data or perform other actions
    }
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
    private void showMealDetails(MealsItem mealItem) {
        Intent intent = new Intent(getContext(), FavMealDetailsActivity.class);
        intent.putExtra(MEAL, (Serializable) mealItem);
        startActivity(intent);
    }

    // Method to delete a meal
    private void deleteMeal(MealsItem mealItem) {
        //presenter.removeMeal(mealItem);
        //favAdapter.notifyDataSetChanged();
    }
}