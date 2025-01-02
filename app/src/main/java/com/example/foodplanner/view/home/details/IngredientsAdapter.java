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
    private final List<String> ingredients;

    public IngredientsAdapter(List<String> ingredients) {
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
        holder.ingredientText.setText(ingredients.get(position));
        String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredients.get(position) + ".png";
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.ingredientImage);

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView ingredientText;
        private final ImageView ingredientImage;

        public ViewHolder(View view) {
            super(view);
            ingredientText = view.findViewById(R.id.ingredient_text);
            ingredientImage = view.findViewById(R.id.imageView3);
        }
    }
}
