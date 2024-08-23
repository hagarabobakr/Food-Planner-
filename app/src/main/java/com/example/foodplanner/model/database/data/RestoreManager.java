package com.example.foodplanner.model.database.data;

import android.util.Log;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.MealDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.concurrent.Executors;

public class RestoreManager {
    private final MealDao mealDao;
    private final MealPlanDao mealPlanDao;
    private final FirebaseFirestore firestore;
    private final FirebaseAuth auth;

    public RestoreManager(MealDao mealDao, MealPlanDao mealPlanDao) {
        this.mealDao = mealDao;
        this.mealPlanDao = mealPlanDao;
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
    }

    public void restoreData() {
        String userId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : null;
        if (userId == null) {
            Log.e("RestoreManager", "User is not authenticated");
            return;
        }

        restoreMeals(userId);
        restoreMealPlans(userId);
    }

    private void restoreMeals(String userId) {
        CollectionReference collectionRef = firestore.collection("users")
                .document(userId)
                .collection("Meals");

        collectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Executors.newSingleThreadExecutor().execute(() -> {
                    mealDao.clearTable(); // Clear local table
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        MealsItem item = document.toObject(MealsItem.class);
                        mealDao.insertAll(item);
                    }
                });
            } else {
                Log.e("MealsRestore", "Error getting documents: ", task.getException());
            }
        });
    }

    private void restoreMealPlans(String userId) {
        CollectionReference collectionRef = firestore.collection("users")
                .document(userId)
                .collection("MealPlans");

        collectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Executors.newSingleThreadExecutor().execute(() -> {
                    mealPlanDao.clearTable(); // Clear local table
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        MealPlan item = document.toObject(MealPlan.class);
                        mealPlanDao.insertAll(item);
                    }
                });
            } else {
                Log.e("MealPlansRestore", "Error getting documents: ", task.getException());
            }
        });
    }
}