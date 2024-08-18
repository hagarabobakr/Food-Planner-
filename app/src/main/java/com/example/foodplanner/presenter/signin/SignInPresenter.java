package com.example.foodplanner.presenter.signin;

import com.example.foodplanner.view.signin.SignInView;
import com.google.firebase.auth.FirebaseAuth;

public class SignInPresenter {
    private FirebaseAuth mAuth;
    private SignInView signInView;

    public SignInPresenter(SignInView signInView) {
        mAuth = FirebaseAuth.getInstance();
        this.signInView = signInView;
    }
    public void signIn(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            signInView.showSignInFailure("Email or Password cannot be empty");
            return;
        }

        signInView.showLoading();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    signInView.hideLoading();
                    if (task.isSuccessful()) {
                        signInView.showSignInSuccess();
                    } else {
                        signInView.showSignInFailure(task.getException().getMessage());
                    }
                });
}
}
