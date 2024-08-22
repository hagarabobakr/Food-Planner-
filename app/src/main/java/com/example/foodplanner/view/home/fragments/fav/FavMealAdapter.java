package com.example.foodplanner.view.home.fragments.fav;

import android.content.Context;
import android.util.Log;
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
import com.example.foodplanner.model.database.MealItemEntity;

import java.util.List;

public class FavMealAdapter extends RecyclerView.Adapter<FavMealAdapter.MealViewHolder> {

    private static final String TAG = "FavMealAdapter";
    private final List<MealsItem> favMeals;
    private OnFavRecipeClickListner listner;
    private Context context;

    public FavMealAdapter(List<MealsItem> meals, OnFavRecipeClickListner _listner, Context _context) {
        listner = _listner;
        context =_context;
        this.favMeals = meals;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal_buttons, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealsItem mealItem = favMeals.get(position);
        Glide.with(context)
                .load(mealItem.getStrMealThumb())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(holder.mealImage);

        holder.mealName.setText(mealItem.getStrMeal());
        holder.btnAddToPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listner.onFavRecipeClickListner(mealItem);
            }
        });

        holder.deleteIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: succes click");
                listner.onDeletIcClickListner(mealItem);
            }
        });
    }
//listner.onFavRecipeClickListner(mealItem);
    @Override
    public int getItemCount() {
        return favMeals.size();
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {
        private  ImageView mealImage,deleteIc;
        private  TextView mealName;
        private Button btnAddToPlan;
        MealViewHolder(View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.meal_image);
            mealName = itemView.findViewById(R.id.meal_name);
            deleteIc = itemView.findViewById(R.id.delet_ic);
            btnAddToPlan = itemView.findViewById(R.id.btn_add_to_fav);
             }
    }
}
