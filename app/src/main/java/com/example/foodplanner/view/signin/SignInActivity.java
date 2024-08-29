package com.example.foodplanner.view.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodplanner.R;
import com.example.foodplanner.model.firebase.AuthRepository;
import com.example.foodplanner.presenter.signin.SignInPresenter;
import com.example.foodplanner.model.sharedpref.UserRepository;
import com.example.foodplanner.view.home.HomeActivity;
import com.example.foodplanner.view.signup.SignUpActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class SignInActivity extends AppCompatActivity implements SignInView {

    private SignInPresenter signInPresenter;
    private EditText etEmail, etPassword;
    private Button btnSignIn;
    private ProgressBar progressBar;
    private TextView signInClickabletxt;
    private ImageView google, facebook, twitter, github;

    private ActivityResultLauncher<Intent> googleSignInLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        AuthRepository authRepository = new AuthRepository(this);
        UserRepository userRepository = new UserRepository(this);

        signInPresenter = new SignInPresenter(this, authRepository, userRepository);

        google = findViewById(R.id.google_icon);
        facebook = findViewById(R.id.facebook_icon);
        twitter = findViewById(R.id.twitter_icon);
        github = findViewById(R.id.github_icon);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        progressBar = findViewById(R.id.progressBarSign);
        signInClickabletxt = findViewById(R.id.signInClickabletxt);

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

        google.setOnClickListener(v -> googleSignInLauncher.launch(signInPresenter.getGoogleSignInClient().getSignInIntent()));

        googleSignInLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult();
                if (account != null) {
                    signInPresenter.signInWithGoogle(account.getIdToken());
                }
            }
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

    private boolean isEmailValid(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        return email != null && email.matches(emailPattern);
    }
}