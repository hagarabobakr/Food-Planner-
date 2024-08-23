package com.example.foodplanner.presenter.signin;

import android.content.Context;

import com.example.foodplanner.R;
import com.example.foodplanner.model.database.data.RestoreManager;
import com.example.foodplanner.view.signin.SignInView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInPresenter {

    private FirebaseAuth mAuth;
    private SignInView signInView;
    private GoogleSignInClient mGoogleSignInClient;
    private Context context;
    private FirebaseFirestore db;
    private RestoreManager restoreModel;

    public SignInPresenter(SignInView signInView, Context context, RestoreManager restoreModel) {
        this.signInView = signInView;
        this.context = context;
        this.restoreModel = restoreModel;  // تهيئة RestoreModel
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize Google Sign-In options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
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
                        restoreModel.restoreData();  // استدعاء وظيفة الاستعادة
                    } else {
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Sign in failed";
                        signInView.showSignInFailure(errorMessage);
                    }
                });
    }

    public void signInWithGoogle(String idToken) {
        signInView.showLoading();

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    signInView.hideLoading();
                    if (task.isSuccessful()) {
                        signInView.showSignInSuccess();
                        restoreModel.restoreData();  // استدعاء وظيفة الاستعادة
                    } else {
                        String errorMessage = task.getException() != null ? task.getException().getMessage() : "Sign in failed";
                        signInView.showSignInFailure(errorMessage);
                    }
                });
    }

    public GoogleSignInClient getGoogleSignInClient() {
        return mGoogleSignInClient;
    }
}