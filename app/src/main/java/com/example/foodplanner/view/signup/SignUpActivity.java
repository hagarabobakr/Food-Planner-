package com.example.foodplanner.view.signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.presenter.signup.SignUpPresenter;
import com.example.foodplanner.view.WelcomeActivity;
import com.example.foodplanner.view.home.HomeActivity;
import com.example.foodplanner.view.signin.SignInActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private SignUpPresenter presenter;
    private EditText etEmail, etPassword, etFullName;
    private Button btnSignUp;
    private ProgressBar progressBar;
    private CheckBox cbAgree;
    private TextView signInClickabletxt;
    private ImageView google,facebook,twitter,github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        presenter = new SignUpPresenter(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etFullName = findViewById(R.id.etFullName);
        btnSignUp = findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBar);
        google = findViewById(R.id.google_icon);
        facebook = findViewById(R.id.facebook_icon);
        twitter = findViewById(R.id.twitter_icon);
        github = findViewById(R.id.github_icon);
        cbAgree = findViewById(R.id.cbAgree);
        signInClickabletxt = findViewById(R.id.signInClickabletxt);
        signInClickabletxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpActivity.this, "SOON", Toast.LENGTH_SHORT).show();

            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpActivity.this, "SOON", Toast.LENGTH_SHORT).show();

            }
        });
        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpActivity.this, "SOON", Toast.LENGTH_SHORT).show();

            }
        });

        btnSignUp.setOnClickListener(v -> {
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

            if (!isPasswordComplex(password)) {
                Toast.makeText(this, "Password must be at least 8 characters long and include a mix of upper and lower case letters, numbers, and symbols.", Toast.LENGTH_LONG).show();
                return;
            }

            if (!cbAgree.isChecked()) {
                Toast.makeText(this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show();
                return;
            }

            presenter.signUp(email, password);
        });
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
        // Save login state
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("is_logged_in", true);
        editor.putBoolean("is_guest", false);
        editor.apply();
        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSignUpFailure(String message) {
        Toast.makeText(this, "Sign Up Failed: " + message, Toast.LENGTH_SHORT).show();
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

    private boolean isPasswordComplex(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password != null && password.matches(passwordPattern);
    }
}

