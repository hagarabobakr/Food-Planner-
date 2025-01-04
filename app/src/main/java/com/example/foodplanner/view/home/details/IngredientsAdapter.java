package com.example.foodplanner.view.home.details;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private final List<Pair<String, String>> ingredients;
    public IngredientsAdapter(List<Pair<String, String>> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String, String> ingredientAndMeasurement = ingredients.get(position);
        holder.ingredientText.setText(ingredientAndMeasurement.first+" :");
        holder.ingredientAmount.setText(ingredientAndMeasurement.second);
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredientAndMeasurement.first + ".png";
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.food_hor_mok_svgrepo_com)
                .error(R.drawable.food_hor_mok_svgrepo_com)
                .into(holder.ingredientImage);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView ingredientText;
        private final TextView ingredientAmount;
        private final ImageView ingredientImage;

        public ViewHolder(View view) {
            super(view);
            ingredientText = view.findViewById(R.id.ingredient_text);
            ingredientAmount = view.findViewById(R.id.Ingredients_amounts);
            ingredientImage = view.findViewById(R.id.imageView3);
        }
    }
}
