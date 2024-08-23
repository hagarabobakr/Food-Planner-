package com.example.foodplanner.presenter.signup;

import com.example.foodplanner.view.signup.SignUpView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPresenter {
    private FirebaseAuth auth;
    private SignUpView view;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
        auth = FirebaseAuth.getInstance();
    }
    public void signUp(String email, String password) {
        view.showLoading();
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    view.hideLoading();
                    if (task.isSuccessful()) {
                        view.onSignUpSuccess();
                    } else {
                        view.onSignUpFailure(task.getException().getMessage());
                    }
                });
    }
    public void signUpWithGoogle(AuthCredential credential) {
        view.showLoading();
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    view.hideLoading();
                    if (task.isSuccessful()) {
                        view.onSignUpSuccess();
                    } else {
                        view.onSignUpFailure(task.getException().getMessage());
                    }
                });
    }
}
