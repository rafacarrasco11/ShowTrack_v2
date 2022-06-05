package com.example.showtrack.ui.prev.login;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.OnInteractorListener{

    private LoginContract.View view;
    private LoginInteractor interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.interactor = new LoginInteractor(this);
    }

    @Override
    public void login(String email, String passwd) {
        interactor.login(email, passwd);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onEmailEmptyError(String message) {
        this.view.setEmailEmptyError(message);
    }

    @Override
    public void onEmailError(String message) {
        this.view.setEmailError(message);
    }

    @Override
    public void onPasswordEmptyError(String message) {
        this.view.setPasswordEmptyError(message);
    }

    @Override
    public void onPasswordError(String message) {
        this.view.setPasswordError(message);
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
