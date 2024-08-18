package com.example.foodplanner.view.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.presenter.signin.SignInPresenter;
import com.example.foodplanner.view.home.HomeActivity;

public class SignInActivity extends AppCompatActivity implements SignInView {

    private EditText etEmail, etPassword;
    private Button btnSignIn;
    private ProgressBar progressBar;
    private SignInPresenter signInPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        progressBar = findViewById(R.id.progressBar);
        signInPresenter = new SignInPresenter(this);

        btnSignIn.setOnClickListener(view -> {
            String email= etEmail.getText().toString().trim();
            String password= etPassword.getText().toString().trim();
            signInPresenter.signIn(email, password);
        });
    }

    @Override
    public void showSignInSuccess() {
        Toast.makeText(this, "Sign in successful!", Toast.LENGTH_SHORT).show();
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
}


