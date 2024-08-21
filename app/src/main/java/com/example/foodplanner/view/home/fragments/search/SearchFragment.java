package com.example.foodplanner.view.home.fragments.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.RetrofitClient;
import com.example.foodplanner.presenter.home.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
  /*  RadioGroup radioGroup;
    RadioButton categoryButton, countryButton, ingredientButton;
    SearchView searchView;
    RecyclerView recyclerView;
    HomePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radioGroup = view.findViewById(R.id.radioGroup);
        categoryButton = view.findViewById(R.id.categoryRadioButton);
        countryButton = view.findViewById(R.id.countryRadioButton2);
        ingredientButton = view.findViewById(R.id.ingredientRadioButton3);
      //  presenter = new HomePresenter(this, RetrofitClient.getRetrofitInstance());
        //searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                int selected = radioGroup.getCheckedRadioButtonId();
                if (selected == categoryButton.getId()) {
                    presenter.searchByCategory(query);
                } else if (selected == countryButton.getId()) {
                    presenter.searchByCountry(query);
                } else if (selected == ingredientButton.getId()) {
                    presenter.searchByIngredient(query);
                } else {
                    Toast.makeText(getContext(), "Please select a search type", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle real-time search if needed
                return false;
            }
        });
    }



    @Override
    public void searchResault(List<MealsItem> meals) {

    }

    @Override
    public void onSearchFailure(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }*/
}