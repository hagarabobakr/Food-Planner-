package com.example.foodplanner.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_DURATION = 2500; // 30 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Find the ImageView and TextView
        ImageView imageView= findViewById(R.id.imageView);
        TextView textView= findViewById(R.id.textView);
// Load animations
        Animation fadeInImage= AnimationUtils.loadAnimation(this, R.anim.fade_in_image);
        Animation fadeInText= AnimationUtils.loadAnimation(this, R.anim.fade_in_image);
// Set initial visibility
        imageView.setVisibility(ImageView.INVISIBLE);
        textView.setVisibility(TextView.INVISIBLE);
        // Start the image animation
        imageView.postDelayed(() -> {
            imageView.setVisibility(ImageView.VISIBLE);
            imageView.startAnimation(fadeInImage);
        }, 500); // Delay before starting image animation
        // Start the text animation after the image animation
        textView.postDelayed(() -> {
            textView.setVisibility(TextView.VISIBLE);
            textView.startAnimation(fadeInText);
        }, 1500);

        // Delayed transition to the next activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish(); // Close the Splash Screen
            }
        }, SPLASH_SCREEN_DURATION);
    }
}