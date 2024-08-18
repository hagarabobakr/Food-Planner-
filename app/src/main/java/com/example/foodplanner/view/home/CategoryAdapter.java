package com.example.foodplanner.view.home;

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
import com.example.foodplanner.model.data.CategoriesItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
private List<CategoriesItem> categories;
    private OnCatigoryClickListner catigoryListner;
    private Context context;

    public CategoryAdapter(List<CategoriesItem> _categories, OnCatigoryClickListner _catigoryListner, Context _context) {
        catigoryListner = _catigoryListner;
        this.categories = _categories;
        context = _context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.catigory_item,parent,false);
        CategoryAdapter.CategoryViewHolder vh = new CategoryAdapter.CategoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
    CategoriesItem categoriesItem = categories.get(position);
        Glide.with(context)
                .load(categoriesItem.getStrCategoryThumb())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(holder.imageView);

        holder.txtView.setText(categoriesItem.getStrCategory());
        holder.itemView.setOnClickListener(v -> {
            if (catigoryListner != null) {
                catigoryListner.onCatigoryClickListner(categoriesItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        // Define view components here
        private ImageView imageView;
        private TextView txtView;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.catigory_img);
            txtView = itemView.findViewById(R.id.catigory_name);
        }
    }
}
