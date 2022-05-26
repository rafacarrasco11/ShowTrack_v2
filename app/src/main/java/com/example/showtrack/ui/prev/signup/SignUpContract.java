package com.example.showtrack.ui.prev.signup;

public interface SignUpContract {

    interface View extends SignUpContract.OnSignUpCallback {
        void setEmailEmptyError();
        void setEmailFormatError();

        void setPasswordEmptyError();
        void setPasswdFormatError();
        void setPasswordEqualsError();

        void setUserNameFormatError();
        void setUserNameEmptyError();

        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void signUp(String email, String passwd, String conPasswd, String userName);
        void onDestroy();
    }

    interface Repository {
        void signUp(String email, String passwd, String conPasswd, String userName, SignUpContract.OnSignUpCallback callback);
    }

    interface OnInteractorListener extends SignUpContract.OnSignUpCallback {
        void onEmailEmptyError();
        void onEmailFormatError();

        void onPasswordEmptyError();
        void onPasswdFormatError();
        void onPasswordEqualsError();

        void onUserNameFormatError();
        void onUserNameEmptyError();
    }

    interface OnSignUpCallback {
        void onSuccess(String message);
        void onFailure(String message);
    }
}
