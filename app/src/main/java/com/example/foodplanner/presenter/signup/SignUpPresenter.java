package com.example.foodplanner.presenter.signup;

import com.example.foodplanner.model.firebase.AuthRepository;
import com.example.foodplanner.model.sharedpref.UserRepository;
import com.example.foodplanner.view.signup.SignUpView;

public class SignUpPresenter {
    private AuthRepository authRepository;
    private UserRepository userRepository;
    private SignUpView view;

    public SignUpPresenter(SignUpView view, AuthRepository authRepository, UserRepository userRepository) {
        this.view = view;
        this.authRepository = authRepository;
        this.userRepository = userRepository;
    }

    public void signUp(String email, String password) {
        view.showLoading();
        authRepository.signUpWithEmail(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                view.hideLoading();
                view.onSignUpSuccess();
            }

            @Override
            public void onFailure(String message) {
                view.hideLoading();
                view.onSignUpFailure(message);
            }
        });
    }

    public void signUpWithGoogle(String idToken) {
        view.showLoading();
        authRepository.signInWithGoogle(idToken, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                view.hideLoading();
                view.onSignUpSuccess();
            }

            @Override
            public void onFailure(String message) {
                view.hideLoading();
                view.onSignUpFailure(message);
            }
        });
    }
}
