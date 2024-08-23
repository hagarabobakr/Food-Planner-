package com.example.foodplanner.view.home.fragments.logout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.foodplanner.R;
import com.example.foodplanner.model.database.AppDatabase;
import com.example.foodplanner.model.database.BackupManager;
import com.example.foodplanner.model.database.MealDao;
import com.example.foodplanner.model.database.data.MealPlanDao;
import com.example.foodplanner.presenter.logout.LogOutPresenter;
import com.example.foodplanner.view.WelcomeActivity;


public class LogOutFragment extends Fragment implements LogOutView {

    private LogOutPresenter logOutPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_out, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnLogOut = view.findViewById(R.id.btnLogOut);


        AppDatabase database = AppDatabase.getInstance(getContext());
        MealDao mealDao = database.getMealDao();
        MealPlanDao mealPlanDao = database.mealPlanDao();
        BackupManager backupModel = new BackupManager(mealDao, mealPlanDao);
        logOutPresenter = new LogOutPresenter(backupModel, this);

        btnLogOut.setOnClickListener(v -> showConfirmDialog());
    }

    private void showConfirmDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirm Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", (dialog, which) -> logOutPresenter.onLogout())
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void clearLoginState() {
        SharedPreferences prefs = getActivity().getSharedPreferences("user_prefs", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("is_logged_in", false);
        editor.apply();
    }

    @Override
    public void navigateToWelcomeActivity() {
        Intent intent = new Intent(getActivity(), WelcomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}