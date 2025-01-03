package com.example.foodplanner.view.home.details;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.foodplanner.model.source.MealsLocalDataSource;
import com.example.foodplanner.presenter.home.details.MealDetailsPresenter;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class FavMealDetailsActivity extends AppCompatActivity implements MealDetailsView {
    private static final String MEAL = "meal";
    private ImageView image;
    private TextView name, country, details;
    private RecyclerView recyclerView;
    private YouTubePlayerView youTubePlayerView;
    private Button addToFav;
    private MealDetailsPresenter presenter;
    MealsLocalDataSource localDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fav_meal_details);
        localDataSource = new MealsLocalDataSource(this);
        presenter = new MealDetailsPresenter(this, localDataSource);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        image = findViewById(R.id.meal_image);
        addToFav = findViewById(R.id.add_to_favorite);
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
            List<Pair<String, String>> ingredients = new ArrayList<>();
            ingredients.add(new Pair<>(meal.getStrIngredient1(),meal.getStrMeasure1()));
            ingredients.add(new Pair<>(meal.getStrIngredient2(),meal.getStrMeasure2()));
            ingredients.add(new Pair<>(meal.getStrIngredient3(),meal.getStrMeasure3()));
            ingredients.add(new Pair<>(meal.getStrIngredient4(),meal.getStrMeasure4()));
            ingredients.add(new Pair<>(meal.getStrIngredient5(),meal.getStrMeasure5()));
            ingredients.add(new Pair<>(meal.getStrIngredient6(),meal.getStrMeasure6()));
            ingredients.add(new Pair<>(meal.getStrIngredient7(),meal.getStrMeasure7()));
            ingredients.add(new Pair<>(meal.getStrIngredient8(),meal.getStrMeasure8()));
            ingredients.add(new Pair<>(meal.getStrIngredient9(),meal.getStrMeasure9()));
            ingredients.add(new Pair<>(meal.getStrIngredient10(),meal.getStrMeasure10()));
            ingredients.add(new Pair<>(meal.getStrIngredient11(),meal.getStrMeasure11()));
            ingredients.add(new Pair<>(meal.getStrIngredient12(),meal.getStrMeasure12()));
            ingredients.add(new Pair<>(meal.getStrIngredient13(),meal.getStrMeasure13()));
            ingredients.add(new Pair<>(meal.getStrIngredient14(),meal.getStrMeasure14()));
            ingredients.add(new Pair<>(meal.getStrIngredient15(),meal.getStrMeasure15()));
            ingredients.add(new Pair<>(meal.getStrIngredient16(),meal.getStrMeasure16()));
            ingredients.add(new Pair<>(meal.getStrIngredient17(),meal.getStrMeasure17()));
            ingredients.add(new Pair<>(meal.getStrIngredient18(),meal.getStrMeasure18()));
            ingredients.add(new Pair<>(meal.getStrIngredient19(),meal.getStrMeasure19()));
            ingredients.add(new Pair<>(meal.getStrIngredient20(),meal.getStrMeasure20()));
            ingredients.removeIf(pair ->
                    pair.first == null || pair.first.isEmpty() || pair.second == null || pair.second.isEmpty());

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
            // Handle Add to Favorites button click
            addToFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showFavoriteAddedMessage();
                }
            });
        }
    }
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


    public void showFavoriteAddedMessage() {
        Toast.makeText(this, "This meal is already added to favorites!", Toast.LENGTH_SHORT).show();
    }
}