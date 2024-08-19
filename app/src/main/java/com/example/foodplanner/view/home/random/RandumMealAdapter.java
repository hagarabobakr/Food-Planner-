package com.example.foodplanner.view.home.random;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.view.home.OnRecipeClickListner;

import java.util.List;

public class RandumMealAdapter extends RecyclerView.Adapter<RandumMealAdapter.MealViewHolder>{
    private List<MealsItem> meals;
    private OnRecipeClickListner listner;
    private Context context;

    public RandumMealAdapter(List<MealsItem> meals, OnRecipeClickListner _listner, Context _context) {
        listner = _listner;
        this.meals = meals;
        context = _context;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.home_card,parent,false);
        RandumMealAdapter.MealViewHolder vh = new RandumMealAdapter.MealViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealsItem meal = meals.get(position);
        Glide.with(context)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(holder.imageView);

        holder.txtView.setText(meal.getStrMeal());
        holder.itemView.setOnClickListener(v -> {
            if (listner != null) {
                listner.onRecipeClickListner(meal);
            }
        });


    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    class MealViewHolder extends RecyclerView.ViewHolder {
    // Define view components here
    private ImageView imageView;
    private TextView txtView;
    public MealViewHolder(@NonNull View itemView) {
    super(itemView);
    // Initialize view components here
        imageView = itemView.findViewById(R.id.recipe_img);
        txtView = itemView.findViewById(R.id.description_txt);
}


            }
}
