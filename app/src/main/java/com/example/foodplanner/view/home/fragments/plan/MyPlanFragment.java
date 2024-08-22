package com.example.foodplanner.view.home.fragments.plan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.model.database.data.MealPlan;

import java.util.List;


public class MyPlanFragment extends Fragment implements MyPlanView {




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
    public void showLocalData(LiveData<List<MealPlan>> allPlanedMeals) {

    }
}