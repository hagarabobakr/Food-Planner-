package com.example.foodplanner.view.home.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.model.data.CategoriesItem;
import com.example.foodplanner.model.data.MealsItem;
import com.example.foodplanner.model.network.RetrofitClient;
import com.example.foodplanner.presenter.home.HomePresenter;
import com.example.foodplanner.view.home.CategoryAdapter;
import com.example.foodplanner.view.home.HomeView;
import com.example.foodplanner.view.home.OnCatigoryClickListner;
import com.example.foodplanner.view.home.OnRecipeClickListner;
import com.example.foodplanner.view.home.category.GetMealsFromAnyWhereActivity;
import com.example.foodplanner.view.home.details.MealDetailsActivity;
import com.example.foodplanner.view.home.popular.PopularMealAdapter;
import com.example.foodplanner.view.home.random.RandumMealAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class HomeFragment extends Fragment implements OnRecipeClickListner, HomeView, OnCatigoryClickListner {

    private static final String TAG = "Home Fragment";
    private static final String MEAL = "meal";

    private RecyclerView randomMealsRecyclerView;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView populerMealsRecyclerView;
    private ChipGroup chipGroup, chipGroupSearch;
    private SearchView searchView;

    private HomePresenter presenter;
    private RandumMealAdapter randomMealAdapter;
    private PopularMealAdapter populerMealAdapter;
    private CategoryAdapter categoryAdapter;

    private boolean isGuest;
    private String searchQuery = "";
    private int searchType = -1; // 0 = Country, 1 = Ingredient, 2 = Category

    // Define valid values with lowercase
    private static final Set<String> COUNTRIES = new HashSet<>(Arrays.asList(
            "american", "british", "canadian", "chinese", "croatian",
            "dutch", "egyptian", "filipino", "french", "greek", "indian",
            "irish", "italian", "jamaican", "japanese", "kenyan",
            "malaysian", "mexican", "moroccan", "polish", "portuguese",
            "russian", "spanish", "thai", "tunisian", "turkish",
            "ukrainian", "unknown", "vietnamese"
    ));

    private static final Set<String> INGREDIENTS = new HashSet<>(Arrays.asList(
            "chicken", "salmon", "beef", "pork", "avocado",
            "apple cider vinegar", "asparagus", "aubergine",
            "baby plum tomatoes", "bacon", "baking powder",
            "balsamic vinegar", "basil", "basil leaves",
            "basmati rice", "bay leaf", "bay leaves",
            "beef brisket", "beef fillet", "beef gravy",
            "beef stock", "bicarbonate of soda", "biryani masala",
            "black pepper", "black treacle", "borlotti beans",
            "bowtie pasta", "bramley apples", "brandy", "bread",
            "breadcrumbs", "broccoli", "brown lentils",
            "brown rice", "brown sugar", "butter",
            "cacao", "cajun", "canned tomatoes", "cannellini beans",
            "cardamom", "carrots", "cashew nuts", "cashews",
            "caster sugar", "cayenne pepper", "celeriac",
            "celery", "celery salt", "challots", "charlotte potatoes",
            "cheddar cheese", "cheese", "cheese curds",
            "cherry tomatoes", "chestnut mushroom",
            "chicken breast", "chicken breasts", "chicken legs",
            "chicken stock", "chicken thighs", "chickpeas",
            "chili powder", "chilled butter", "chilli",
            "chilli powder", "chinese broccoli", "chocolate chips",
            "chopped onion", "chopped parsley"
    ));

    private static final Set<String> CATEGORIES = new HashSet<>(Arrays.asList(
            "beef", "breakfast", "chicken", "dessert", "goat", "lamb",
            "miscellaneous", "pasta", "pork", "seafood", "side", "starter",
            "vegan", "vegetarian"
    ));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Check if user is a guest
        SharedPreferences prefs = requireActivity().getSharedPreferences("user_prefs", getContext().MODE_PRIVATE);
        isGuest = prefs.getBoolean("is_guest", false);

        // Initialize RecyclerViews
        randomMealsRecyclerView = view.findViewById(R.id.recycler_random_meals);
        categoriesRecyclerView = view.findViewById(R.id.recycler_categories);
        populerMealsRecyclerView = view.findViewById(R.id.recycler_popular_meals);
        chipGroup = view.findViewById(R.id.chipGroup);
        chipGroupSearch = view.findViewById(R.id.chipGroupSearch);
        searchView = view.findViewById(R.id.search_view);

        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showPopupMenu(v);
            }
        });

        // Fetch the text from SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (searchType == -1) {
                    Toast.makeText(getContext(), "Please select a search type first", Toast.LENGTH_SHORT).show();
                } else {
                    searchQuery = query.toLowerCase(Locale.ROOT);
                    handleSearchQuery(searchQuery);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuery = newText.toLowerCase(Locale.ROOT);
                return true;
            }
        });

        // Setup LayoutManagers
        randomMealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        populerMealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Initialize Adapters
        randomMealAdapter = new RandumMealAdapter(new ArrayList<>(), this, getContext());
        randomMealsRecyclerView.setAdapter(randomMealAdapter);

        categoryAdapter = new CategoryAdapter(new ArrayList<>(), this, getContext());
        categoriesRecyclerView.setAdapter(categoryAdapter);

        populerMealAdapter = new PopularMealAdapter(new ArrayList<>(), this, getContext());
        populerMealsRecyclerView.setAdapter(populerMealAdapter);

        // Initialize Presenter
        presenter = new HomePresenter(this, RetrofitClient.getRetrofitInstance());
        presenter.getRandumMeal();
        presenter.getMealsByFirstLitter("B");
        presenter.getCatigoryIteams();
        presenter.getCountries();
    }

    private void handleSearchQuery(String query) {
        Toast.makeText(getContext(), "Search Query: " + query, Toast.LENGTH_SHORT).show();
        if (searchType == 0) {
            if (COUNTRIES.contains(query)) {
                presenter.getMealsByCountry(query);
            } else {
                Toast.makeText(getContext(), "Country not found", Toast.LENGTH_SHORT).show();
            }
        } else if (searchType == 1) {
            if (INGREDIENTS.contains(query)) {
                presenter.getMealsByIngredient(query);
            } else {
                Toast.makeText(getContext(), "Ingredient not found", Toast.LENGTH_SHORT).show();
            }
        } else if (searchType == 2) {
            if (CATEGORIES.contains(query)) {
                presenter.getMealsByCategorie(query);
            } else {
                Toast.makeText(getContext(), "Category not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showPopupMenu(View anchor) {
        PopupMenu popupMenu = new PopupMenu(getContext(), anchor);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu_search, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_country) {
                searchType = 0;
                Toast.makeText(getContext(), "Searching by country", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.menu_ingredient) {
                searchType = 1;
                Toast.makeText(getContext(), "Searching by ingredient", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.menu_category) {
                searchType = 2;
                Toast.makeText(getContext(), "Searching by category", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                return false;
            }
        });

        popupMenu.show();
    }

    @Override
    public void onRecipeClickListner(MealsItem meal) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            presenter.getMealDetails(meal.getIdMeal());
        }
    }

    @Override
    public void showRandomMeals(List<MealsItem> meals) {
        randomMealAdapter = new RandumMealAdapter(meals, this, getContext());
        randomMealsRecyclerView.setAdapter(randomMealAdapter);
        randomMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPopularMeals(List<MealsItem> meals) {
        populerMealAdapter = new PopularMealAdapter(meals, this, getContext());
        populerMealsRecyclerView.setAdapter(populerMealAdapter);
        populerMealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCategories(List<CategoriesItem> categories) {
        Log.i(TAG, "showCategories: ");
        categoryAdapter = new CategoryAdapter(categories, this, getContext());
        categoriesRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMealDetails(MealsItem mealsItem) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            Intent intent = new Intent(getContext(), MealDetailsActivity.class);
            intent.putExtra(MEAL, mealsItem);
            startActivity(intent);
        }
    }

    @Override
    public void showChips(List<String> areas) {
        chipGroup.removeAllViews();
        for (String area : areas) {
            Chip chip = new Chip(getContext());
            chip.setText(area);
            chip.setTextColor(R.color.chip_ripple_color);
            chip.setRippleColorResource(R.color.blue);
            chip.setChipStrokeColorResource(R.color.blue);
            chip.setElevation(10);
            chip.setChipBackgroundColorResource(R.color.white);
            chip.setOnClickListener(v -> {
                if (isGuest) {
                    showGuestRestrictionMessage();
                } else {
                    String chipText = ((Chip) v).getText().toString();
                    presenter.getMealsByCountry(chipText);
                }
            });
            chipGroup.addView(chip);
        }
    }

    @Override
    public void showMealsByCategory(List<MealsItem> meals) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            Intent intent = new Intent(getContext(), GetMealsFromAnyWhereActivity.class);
            intent.putExtra(MEAL, (Serializable) meals);
            startActivity(intent);
        }
    }

    @Override
    public void showMealsByCountry(List<MealsItem> meals) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            Intent intent = new Intent(getContext(), GetMealsFromAnyWhereActivity.class);
            intent.putExtra(MEAL, (Serializable) meals);
            startActivity(intent);
        }
    }

    @Override
    public void showMealsByIngredient(List<MealsItem> meals) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            Intent intent = new Intent(getContext(), GetMealsFromAnyWhereActivity.class);
            intent.putExtra(MEAL, (Serializable) meals);
            startActivity(intent);
        }
    }

    @Override
    public void onCatigoryClickListner(CategoriesItem categoriesItem) {
        if (isGuest) {
            showGuestRestrictionMessage();
        } else {
            presenter.getMealsByCategorie(categoriesItem.getStrCategory());
        }
    }

    private void showGuestRestrictionMessage() {
        Toast.makeText(getContext(), "Login is required to access this feature", Toast.LENGTH_LONG).show();
    }
}