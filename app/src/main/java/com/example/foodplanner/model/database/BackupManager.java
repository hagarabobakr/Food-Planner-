package com.example.foodplanner.model.database;

import android.util.Log;

import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.model.database.data.MealPlanDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;
import java.util.concurrent.Executors;

public class BackupManager {
    private final MealDao mealDao;
    private final MealPlanDao mealPlanDao;
    private final FirebaseFirestore firestore;
    private final FirebaseAuth auth;

    public BackupManager(MealDao mealDao, MealPlanDao mealPlanDao) {
        this.mealDao = mealDao;
        this.mealPlanDao = mealPlanDao;
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
    }

    public void backupData() {
        String userId = auth.getCurrentUser() != null ? auth.getCurrentUser().getUid() : null;
        if (userId == null) {
            Log.e("BackupManager", "User is not authenticated");
            return;
        }

        backupMeals(userId);
        backupMealPlans(userId);
    }

    private void backupMeals(String userId) {
        CollectionReference collectionRef = firestore.collection("users")
                .document(userId)
                .collection("Meals");

        collectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                WriteBatch batch = firestore.batch();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    batch.delete(document.getReference());
                }
                batch.commit().addOnCompleteListener(deleteTask -> {
                    if (deleteTask.isSuccessful()) {
                        Executors.newSingleThreadExecutor().execute(() -> {
                            List<MealsItem> items = mealDao.getAllMealsList();
                            for (MealsItem item : items) {
                                collectionRef.document(item.getIdMeal()).set(item)
                                        .addOnSuccessListener(aVoid -> Log.d("MealsBackup", "Data backed up successfully - Meal : " + item.getIdMeal()))
                                        .addOnFailureListener(e -> Log.e("MealsBackup", "Error backing up data", e));
                            }
                        });
                    } else {
                        Log.e("MealsBackup", "Error deleting old data", deleteTask.getException());
                    }
                });
            } else {
                Log.e("MealsBackup", "Error getting documents for deletion: ", task.getException());
            }
        });
    }

    private void backupMealPlans(String userId) {
        CollectionReference collectionRef = firestore.collection("users")
                .document(userId)
                .collection("MealPlans");

        collectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                WriteBatch batch = firestore.batch();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    batch.delete(document.getReference());
                }
                batch.commit().addOnCompleteListener(deleteTask -> {
                    if (deleteTask.isSuccessful()) {
                        Executors.newSingleThreadExecutor().execute(() -> {
                            List<MealPlan> items = mealPlanDao.getAllMealPlansList();
                            for (MealPlan item : items) {
                                collectionRef.document(String.valueOf(item.getMealPlanId())).set(item)
                                        .addOnSuccessListener(aVoid -> Log.d("MealPlansBackup", "Data backed up successfully - MealPlan : " + item.getMealPlanId()))
                                        .addOnFailureListener(e -> Log.e("MealPlansBackup", "Error backing up data", e));
                            }
                        });
                    } else {
                        Log.e("MealPlansBackup", "Error deleting old data", deleteTask.getException());
                    }
                });
            } else {
                Log.e("MealPlansBackup", "Error getting documents for deletion: ", task.getException());
            }
        });
    }
}
