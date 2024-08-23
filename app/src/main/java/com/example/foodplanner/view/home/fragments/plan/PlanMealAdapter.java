package com.example.foodplanner.view.home.fragments.plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.database.data.MealPlan;
import com.example.foodplanner.view.home.fragments.fav.FavMealAdapter;

import java.util.List;

public class PlanMealAdapter extends RecyclerView.Adapter<PlanMealAdapter.PlanMealViewHolder>{
    private Context context;
    private final List<MealPlan> planMealsList;

    public PlanMealAdapter(List<MealPlan> planMealsList, Context context) {
        this.planMealsList = planMealsList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlanMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new PlanMealAdapter.PlanMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanMealViewHolder holder, int position) {

        MealPlan mealPlan = planMealsList.get(position);
        Glide.with(context)
                .load(mealPlan.getStrMealThumb())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(holder.mealImage);

        holder.mealName.setText(mealPlan.getStrMeal());
    }

    @Override
    public int getItemCount() {
        return planMealsList.size();
    }

    static class PlanMealViewHolder extends RecyclerView.ViewHolder {
        private ImageView mealImage,deleteIc;
        private TextView mealName;
        PlanMealViewHolder(View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_image);
            mealName = itemView.findViewById(R.id.meal_name);
        }
    }
}
