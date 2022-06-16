package com.example.showtrack.ui.prf.profile.settings;

import android.os.Handler;
import android.text.TextUtils;

import com.example.showtrack.R;
import com.example.showtrack.data.repository.UserRepository;
import com.example.showtrack.data.repository.prev.LoginRepository;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.prev.signup.SignUpContract;
import com.example.showtrack.utils.CommonUtils;

public class SettingsFragmentInteractor implements SettingsFragmentContract.OnSettingsCallback{

    private final SettingsFragmentContract.OnInteractorListener listener;
    private final SettingsFragmentContract.OnSettingsCallback callback;

    public SettingsFragmentInteractor(SettingsFragmentContract.OnInteractorListener listener) {
        this.listener = listener;
        this.callback = this;
    }

    @Override
    public void onSuccessChangeName(String message) {
        listener.onSuccessChangeName(message);
    }

    @Override
    public void onSuccessChangePassword(String message) {
        listener.onSuccessChangePassword(message);
    }

    @Override
    public void onFailureChangePassword(String message) {
        listener.onFailureChangePassword(message);
    }

    @Override
    public void onSuccessChangeLang(String message) {
        listener.onSuccessChangeLang(message);
    }

    @Override
    public void onSuccessLogOut() {
        listener.onSuccessLogOut();
    }

    @Override
    public void onSuccessDeleteAccount() {
        listener.onSuccessDeleteAccount();
    }



    public void delAccount() {
        UserRepository.getInstance().DeleteAccount(this);
    }

    public void logOut() {
        UserRepository.getInstance().LogOut(this);
    }

    public void changeLang(String lang) {
        UserRepository.getInstance().changeLanguage(lang, this);
    }

    public void changePasswd(String passwd, String actPasswd) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(passwd)) {
                    listener.onPasswordEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(actPasswd)) {
                    listener.onActualPasswordEmptyError();
                    return;
                }
                if (!CommonUtils.isPasswordValid(passwd)) {
                    listener.onPasswordError();
                    return;
                }

                UserRepository.getInstance().changePassword(passwd,actPasswd, callback);
            }
        }, 1000);


    }

    public void changeName(String name) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(name)) {
                    listener.onNameEmptyError();
                    return;
                }

                UserRepository.getInstance().changeName(name, callback);

            }
        }, 1000);
    }

}
