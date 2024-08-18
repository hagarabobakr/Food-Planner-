package com.example.foodplanner.view.signin;

public interface SignInView {
    void showSignInSuccess();
    void showSignInFailure(String message);
    void showLoading();
    void hideLoading();
}
