package com.example.showtrack.ui.prev.login;

import android.text.TextUtils;
import android.os.Handler;

import com.example.showtrack.R;
import com.example.showtrack.data.repository.prev.LoginRepository;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.utils.CommonUtils;


public class LoginInteractor implements LoginContract.OnLoginCallback {

    private LoginContract.OnInteractorListener listener;
    private LoginContract.OnLoginCallback callback;

    public LoginInteractor(LoginContract.OnInteractorListener listener) {
        this.listener = listener;
        callback = this;
    }

    public void login(String email, String passwd) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(email)) {
                    listener.onEmailEmptyError(ShowTrackApplication.getContext().getString(R.string.Login_emailEmptyError));
                    return;
                }
                if (TextUtils.isEmpty(passwd)) {
                    listener.onPasswordEmptyError(ShowTrackApplication.getContext().getString(R.string.Login_passwdEmptyError));
                    return;
                }

                LoginRepository.getInstance().login(email,passwd, callback);
            }
        }, 1000);
    }

    @Override
    public void onSuccess(String message) {
        this.listener.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        this.listener.onFailure(message);
    }
}
