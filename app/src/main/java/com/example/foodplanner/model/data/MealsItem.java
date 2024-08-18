package com.example.foodplanner.model.data;

import com.google.gson.annotations.SerializedName;

public class MealsItem{


	@SerializedName("strMealThumb")// Assuming this is the correct field for image URLprivate
	 String strMealThumb;

	@SerializedName("strDescription")
	public String strDescription;

	@SerializedName("strIngredient")
	private String strIngredient;

	@SerializedName("strType")
	private Object strType;

	@SerializedName("idIngredient")
	private String idIngredient;

	public String getStrDescription(){
		return strDescription;
	}

	public String getStrIngredient(){
		return strIngredient;
	}

	public Object getStrType(){
		return strType;
	}

	public String getIdIngredient(){
		return idIngredient;
	}



	public String getStrMealThumb(){
		return strMealThumb;
	}
}