package com.example.foodplanner.model.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class   CategoryResponse{

	@SerializedName("categories")
	public List<CategoriesItem> categories;

	public List<CategoriesItem> getCategories(){
		return categories;
	}
}