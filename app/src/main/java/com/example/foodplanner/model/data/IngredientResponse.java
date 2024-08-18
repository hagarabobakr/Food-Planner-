package com.example.foodplanner.model.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class IngredientResponse{

	@SerializedName("meals")
	private List<IngredientsItem> meals;

	public List<IngredientsItem> getMeals(){
		return meals;
	}
}