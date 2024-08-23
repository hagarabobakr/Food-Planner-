package com.example.foodplanner.view.signin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.model.database.AppDatabase;
import com.example.foodplanner.model.database.MealDao;
import com.example.foodplanner.model.database.data.MealPlanDao;
import com.example.foodplanner.model.database.data.RestoreManager;
import com.example.foodplanner.presenter.signin.SignInPresenter;
import com.example.foodplanner.view.home.HomeActivity;
import com.example.foodplanner.view.signup.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SignInActivity extends AppCompatActivity implements SignInView {

    private EditText etEmail, etPassword;
    private Button btnSignIn;
    private ProgressBar progressBar;
    private SignInPresenter signInPresenter;
    private TextView signInClickabletxt;
    private ImageView google, facebook, twitter, github;

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        google = findViewById(R.id.google_icon);
        facebook = findViewById(R.id.facebook_icon);
        twitter = findViewById(R.id.twitter_icon);
        github = findViewById(R.id.github_icon);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        progressBar = findViewById(R.id.progressBarSign);
        signInClickabletxt = findViewById(R.id.signInClickabletxt);


        AppDatabase database = AppDatabase.getInstance(this);
        MealDao mealDao = database.getMealDao();
        MealPlanDao mealPlanDao = database.mealPlanDao();
        RestoreManager restoreModel = new RestoreManager(mealDao, mealPlanDao);


        signInPresenter = new SignInPresenter(this, this, restoreModel);

        signInClickabletxt.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        btnSignIn.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (!isEmailValid(email)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            signInPresenter.signIn(email, password);
        });

        google.setOnClickListener(v -> signInWithGoogle());
    }

    private void signInWithGoogle() {
        Intent signInIntent = signInPresenter.getGoogleSignInClient().getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void showSignInSuccess() {
        Toast.makeText(this, "Sign in successful!", Toast.LENGTH_SHORT).show();
        // Save login state
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("is_logged_in", true);
        editor.apply();
        startActivity(new Intent(SignInActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void showSignInFailure(String message) {
        Toast.makeText(this, "Sign in failed: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    private boolean isEmailValid(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        return email != null && email.matches(emailPattern);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    signInPresenter.signInWithGoogle(account.getIdToken());
                }
            } catch (ApiException e) {
                // Handle sign-in failure
                Toast.makeText(this, "Google sign-in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}