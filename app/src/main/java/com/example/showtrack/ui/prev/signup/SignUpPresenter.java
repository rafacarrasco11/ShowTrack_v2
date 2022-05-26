package com.example.showtrack.ui.prev.signup;

public class SignUpPresenter implements SignUpContract.Presenter, SignUpContract.OnInteractorListener{

    private SignUpContract.View view;
    private SignUpInteractor interactor;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.interactor = new SignUpInteractor(this);
    }

    @Override
    public void signUp(String email, String passwd, String conPasswd, String userName) {
        this.interactor.signUp(email, passwd, conPasswd, userName);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onEmailEmptyError() {
        this.view.setEmailEmptyError();
    }

    @Override
    public void onEmailFormatError() {
        this.view.setEmailFormatError();
    }

    @Override
    public void onPasswordEmptyError() {
        this.view.setPasswordEmptyError();
    }

    @Override
    public void onPasswdFormatError() {
        this.view.setPasswdFormatError();
    }

    @Override
    public void onPasswordEqualsError() {
        this.view.setPasswordEqualsError();
    }

    @Override
    public void onUserNameFormatError() {
        this.view.setUserNameFormatError();
    }

    @Override
    public void onUserNameEmptyError() {
        this.view.setUserNameEmptyError();
    }

    @Override
    public void onSuccess(String message) {
        this.view.onSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        this.view.onFailure(message);
    }
}
