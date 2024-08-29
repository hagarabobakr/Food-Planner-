package com.example.foodplanner.presenter.logout;

import com.example.foodplanner.model.database.BackupManager;
import com.example.foodplanner.model.sharedpref.UserRepository;
import com.example.foodplanner.view.home.fragments.logout.LogOutView;
import com.google.firebase.auth.FirebaseAuth;

public class LogOutPresenter {
    private BackupManager backupModel;
    private LogOutView logOutView;
    private UserRepository userRepository;

    public LogOutPresenter(UserRepository userRepository, BackupManager backupModel, LogOutView logOutView) {
        this.userRepository = userRepository;
        this.backupModel = backupModel;
        this.logOutView = logOutView;
    }

    public void onLogout() {
        backupModel.backupData();
        userRepository.clearLoginState();
        FirebaseAuth.getInstance().signOut();
        logOutView.navigateToWelcomeActivity();
    }
}
