package com.example.foodplanner.view.signup;

public interface SignUpView {
    void onSignUpSuccess();
    void onSignUpFailure(String message);
    void showLoading();
    void hideLoading();
}
