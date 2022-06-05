package com.example.showtrack.ui.prev.login;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.ui.flm.filmgenre.FilmGenreContract;

import java.util.ArrayList;

public interface LoginContract {

    interface View extends LoginContract.OnLoginCallback {
        void setEmailEmptyError(String message);
        void setEmailError(String message);

        void setPasswordEmptyError(String message);
        void setPasswordError(String message);

        void setLoginError(String message);

        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void login(String email, String passwd);
        void onDestroy();
    }

    interface Repository {
        void login(String email, String passwd, LoginContract.OnLoginCallback callback);
    }

    interface OnInteractorListener extends LoginContract.OnLoginCallback {
        void onEmailEmptyError(String message);
        void onEmailError(String message);
        void onPasswordEmptyError(String message);
        void onPasswordError(String message);
    }

    interface OnLoginCallback {
        void onSuccess(String message);
        void onFailure(String message);
    }
}
