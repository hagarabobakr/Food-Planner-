package com.example.foodplanner.view.home.fragments.plan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.source.MealsLocalDataSource;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.presenter.home.plan.MyPlanPresenter;

import java.util.ArrayList;
import java.util.List;


public class MyPlanFragment extends Fragment implements MyPlanView,OnPlanedRecipeClickListner {
    MyPlanPresenter myPlanPresenter;
    private CalendarView calendarView;
    private int selectedYear;
    private int selectedMonth;
    private int selectedDay;
    private RecyclerView recyclerView;
    private PlanMealAdapter planMealAdapter;
    private static final String MEAL = "meal";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.planMealRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        planMealAdapter = new PlanMealAdapter(new ArrayList<>(),getContext(),this);
        recyclerView.setAdapter(planMealAdapter);
        calendarView = view.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedYear = year;
                selectedMonth = month + 1; // month is 0-based
                selectedDay = dayOfMonth;

                myPlanPresenter.getMealsByDate(selectedDay,selectedMonth,selectedYear);
            }
        });
        myPlanPresenter = new MyPlanPresenter(this, MealsLocalDataSource.getInstance(getContext()));
    }

    @Override
    public void showLocalData(LiveData<List<MealPlan>> allPlanedMeals) {

    }

    @Override
    public void showMealsByDate(LiveData<List<MealPlan>> mealsByDate) {
        mealsByDate.observe(getViewLifecycleOwner(), new Observer<List<MealPlan>>() {
            @Override
            public void onChanged(@Nullable List<MealPlan> mealPlans) {
                if (mealPlans != null && !mealPlans.isEmpty()) {
                    planMealAdapter = new PlanMealAdapter(mealPlans,getContext(),MyPlanFragment.this);
                    recyclerView.setAdapter(planMealAdapter);
                    planMealAdapter.notifyDataSetChanged();

                }
            }
        });
    }

    @Override
    public void onPlanedRecipeClickListner(MealsItem mealItem) {

    }
}

