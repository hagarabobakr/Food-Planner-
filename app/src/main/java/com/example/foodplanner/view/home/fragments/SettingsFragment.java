package com.example.foodplanner.view.home.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.foodplanner.R;


public class SettingsFragment extends Fragment {

    private CheckBox cbNotifications, cbDarkMode;
    private TextView tvChangeLanguage, tvContactSupport, tvShareApp;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cbNotifications = view.findViewById(R.id.cbNotifications);
        cbDarkMode = view.findViewById(R.id.cbDarkMode);
        tvChangeLanguage = view.findViewById(R.id.tvChangeLanguage);
        tvContactSupport = view.findViewById(R.id.tvContactSupport);
        tvShareApp = view.findViewById(R.id.tvShareApp);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("settings", getContext().MODE_PRIVATE);

        // Load saved preferences
        cbNotifications.setChecked(sharedPreferences.getBoolean("notifications_enabled", true));
        cbDarkMode.setChecked(sharedPreferences.getBoolean("dark_mode_enabled", false));

        cbNotifications.setOnCheckedChangeListener((buttonView, isChecked) ->
                sharedPreferences.edit().putBoolean("notifications_enabled", isChecked).apply()
        );

        cbDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sharedPreferences.edit().putBoolean("dark_mode_enabled", isChecked).apply();
            // Code to switch app theme mode
        });

        tvChangeLanguage.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        });

        tvContactSupport.setOnClickListener(v -> {
            // Open email client with pre-filled support email address
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(android.net.Uri.parse("mailto:support@foodplanner.com"));
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        });

        tvShareApp.setOnClickListener(v -> {
            // Share app with friends
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this app");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "I’m using Food Planner, a great app for planning weekly meals. Download it from the Google Play Store!");
            startActivity(Intent.createChooser(shareIntent, "Share App"));
        });
    }
}