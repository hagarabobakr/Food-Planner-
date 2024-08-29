package com.example.foodplanner.presenter.signin;

import com.example.foodplanner.model.firebase.AuthRepository;
import com.example.foodplanner.model.sharedpref.UserRepository;
import com.example.foodplanner.view.signin.SignInView;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class SignInPresenter {

    private AuthRepository authRepository;
    private UserRepository userRepository;
    private SignInView view;

    public SignInPresenter(SignInView view, AuthRepository authRepository, UserRepository userRepository) {
        this.view = view;
        this.authRepository = authRepository;
        this.userRepository = userRepository;
    }

    public void signIn(String email, String password) {
        view.showLoading();
        authRepository.signInWithEmail(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                view.hideLoading();
                userRepository.saveLoginState(true, false);
                view.showSignInSuccess();
            }

            @Override
            public void onFailure(String message) {
                view.hideLoading();
                view.showSignInFailure(message);
            }
        });
    }

    public void signInWithGoogle(String idToken) {
        view.showLoading();
        authRepository.signInWithGoogle(idToken, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess() {
                view.hideLoading();
                userRepository.saveLoginState(true, false);
                view.showSignInSuccess();
            }

            @Override
            public void onFailure(String message) {
                view.hideLoading();
                view.showSignInFailure(message);
            }
        });
    }
    public GoogleSignInClient getGoogleSignInClient() {
        return authRepository.getGoogleSignInClient();
    }
}