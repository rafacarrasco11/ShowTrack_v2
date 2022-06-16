package com.example.showtrack.ui.prev.signup;

import android.os.Handler;
import android.text.TextUtils;

import com.example.showtrack.data.repository.prev.SignUpRepository;
import com.example.showtrack.utils.CommonUtils;

public class SignUpInteractor implements SignUpContract.OnSignUpCallback{

    private final SignUpContract.OnInteractorListener listener;
    private final SignUpContract.OnSignUpCallback callback;

    public SignUpInteractor(SignUpContract.OnInteractorListener listener) {
        this.listener = listener;
        this.callback = this;
    }

    public void signUp(String email,String passwd, String conPasswd, String userName) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(email)) {
                    listener.onEmailEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(passwd)) {
                    listener.onPasswordEmptyError();
                    return;
                }
                if (TextUtils.isEmpty(userName)) {
                    listener.onUserNameEmptyError();
                    return;
                }
                if (!CommonUtils.isPasswordValid(passwd)) {
                    listener.onPasswdFormatError();
                    return;
                }
                if (!CommonUtils.isEmailValid(email)) {
                    listener.onEmailFormatError();
                    return;
                }
                if (!CommonUtils.isUserNameValid(userName)) {
                    listener.onUserNameFormatError();
                    return;
                }
                if (!passwd.equals(conPasswd)) {
                    listener.onPasswordEqualsError();
                    return;
                }


                SignUpRepository.getInstance().signUp(email,passwd, conPasswd, userName, callback);
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
