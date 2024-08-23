package com.example.foodplanner.presenter.logout;

import com.example.foodplanner.model.database.BackupManager;
import com.example.foodplanner.view.home.fragments.logout.LogOutView;
import com.google.firebase.auth.FirebaseAuth;

public class LogOutPresenter {
    private BackupManager backupModel;
    private LogOutView logOutView;

    public LogOutPresenter(BackupManager backupModel, LogOutView logOutView) {
        this.backupModel = backupModel;
        this.logOutView = logOutView;
    }

    public void onLogout() {
        backupModel.backupData();
        logOutView.clearLoginState();
        FirebaseAuth.getInstance().signOut();
        logOutView.navigateToWelcomeActivity();
    }
}
