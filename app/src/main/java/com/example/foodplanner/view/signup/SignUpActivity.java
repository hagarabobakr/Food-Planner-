package com.example.foodplanner.view.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.presenter.signup.SignUpPresenter;
import com.example.foodplanner.view.home.HomeActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private SignUpPresenter presenter;
    private EditText etEmail, etPassword;
    Button btnSignUp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        presenter = new SignUpPresenter(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp= findViewById(R.id.btnSignUp);
        progressBar = findViewById(R.id.progressBar);

        btnSignUp.setOnClickListener(v -> {
            String email= etEmail.getText().toString();
            String password= etPassword.getText().toString();
            if (!email.isEmpty()) {
                presenter.signUp(email, password);
            } else {
                Toast.makeText(this, "Please enter user name", Toast.LENGTH_SHORT).show();
            }
            if(!password.isEmpty()){
                if (isPasswordComplex(password)) {
                    presenter.signUp(email, password);
                } else {
                    Toast.makeText(this, "Password must be at least 8 characters long and include a mix of upper and lower case letters, numbers, and symbols.", Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
        startActivity(intent);
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

    public boolean isPasswordComplex(String password) {
        String passwordPattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password != null && password.matches(passwordPattern);
    }
}


