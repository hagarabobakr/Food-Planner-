package com.example.foodplanner.model.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_table")
public class MealItemEntity {
    @PrimaryKey(autoGenerate = true)
    private int id; // Local database id

    private String idMeal; // Real API id for the meal

    private String strMeal;
    private String strMealThumb;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String ingredients;
    private String strYoutube;

    // Constructor
    public MealItemEntity(int id,String idMeal,String strMeal, String strMealThumb, String strCategory, String strArea,
                          String strInstructions, String ingredients, String strYoutube) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.ingredients = ingredients;
        this.strYoutube = strYoutube;
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }
}

