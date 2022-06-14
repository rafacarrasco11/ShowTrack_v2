package com.example.showtrack.ui.prf.profile.settings;

import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.Stat;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;

import java.util.ArrayList;

public interface SettingsFragmentContract {
    interface View extends SettingsFragmentContract.OnSettingsCallback {
        void setNameEmptyError();
        void setNameError();

        void setPasswordEmptyError();
        void setPasswordError();

        void setActualPasswordEmptyError();
    }

    interface Presenter {
        void changeName(String name);
        void changePassword(String passwd, String actPasswd);

        void changeLanguage(String lang);

        void LogOut();
        void DeleteAccount();



        void onDestroy();
    }

    interface Repository {
        void changeName(String name, SettingsFragmentContract.OnSettingsCallback callback);

        void changePassword(String passwd, String actPasswd, SettingsFragmentContract.OnSettingsCallback callback);

        void changeLanguage(String lang, SettingsFragmentContract.OnSettingsCallback callback);

        void LogOut(SettingsFragmentContract.OnSettingsCallback callback);

        void DeleteAccount(SettingsFragmentContract.OnSettingsCallback callback);
    }


    interface OnInteractorListener extends SettingsFragmentContract.OnSettingsCallback {
        void onNameEmptyError();
        void onNameError();

        void onPasswordEmptyError();
        void onPasswordError();

        void onActualPasswordEmptyError();
    }

    interface OnSettingsCallback {
        void onSuccessChangeName(String message);
        void onSuccessChangePassword(String message);
        void onFailureChangePassword(String message);
        void onSuccessChangeLang(String message);

        void onSuccessLogOut();
        void onSuccessDeleteAccount();

    }
}
