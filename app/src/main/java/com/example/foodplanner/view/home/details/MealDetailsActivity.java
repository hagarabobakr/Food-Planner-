package com.example.foodplanner.view.home.details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealsItem;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MealDetailsActivity extends AppCompatActivity {
    private static final String MEAL = "meal";

    private ImageView image;
    private TextView name, country, details;
    private RecyclerView recyclerView;
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_details);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        image = findViewById(R.id.meal_image);
        name = findViewById(R.id.recipeTitle2);
        country = findViewById(R.id.recipeCountry2);
        details = findViewById(R.id.steps_details);
        recyclerView = findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        MealsItem meal = (MealsItem) intent.getSerializableExtra(MEAL);

        if (meal != null) {
            Glide.with(this)
                    .load(meal.getStrMealThumb())
                    .into(image);
            name.setText(meal.getStrMeal());
            details.setText(meal.getStrInstructions());


            List<String> ingredients = new ArrayList<>();
            ingredients.add(meal.getStrIngredient1());
            ingredients.add(meal.getStrIngredient2());
            ingredients.add(meal.getStrIngredient3());
            ingredients.add(meal.getStrIngredient4());
            ingredients.add(meal.getStrIngredient5());
            ingredients.add(meal.getStrIngredient6());
            ingredients.add(meal.getStrIngredient7());
            ingredients.add(meal.getStrIngredient8());
            ingredients.add(meal.getStrIngredient9());
            ingredients.add(meal.getStrIngredient10());
            ingredients.add(meal.getStrIngredient11());
            ingredients.add(meal.getStrIngredient12());
            ingredients.add(meal.getStrIngredient13());
            ingredients.add(meal.getStrIngredient14());
            ingredients.add(meal.getStrIngredient15());
            ingredients.add(meal.getStrIngredient16());
            ingredients.add(meal.getStrIngredient17());
            ingredients.add(meal.getStrIngredient18());
            ingredients.add(meal.getStrIngredient19());
            ingredients.add(meal.getStrIngredient20());
            ingredients.removeIf(String::isEmpty);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new IngredientsAdapter(ingredients));
            // Configure YouTube Player
            getLifecycle().addObserver(youTubePlayerView);
            youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    if (meal.getStrYoutube() != null) {
                        String videoId = extractYouTubeVideoId(meal.getStrYoutube());
                        youTubePlayer.cueVideo(videoId, 0);
                    }
                }
            });
    }}

        // Helper method to extract YouTube video ID from URL
        public String extractYouTubeVideoId(String url) {
            String videoId = "";
            if (url != null && url.contains("v=")) {
                int startIndex = url.indexOf("v=") + 2;
                int endIndex = url.indexOf("&", startIndex);
                if (endIndex == -1) {
                    endIndex = url.length();
                }
                videoId = url.substring(startIndex, endIndex);
            }
            return videoId;
        }
}