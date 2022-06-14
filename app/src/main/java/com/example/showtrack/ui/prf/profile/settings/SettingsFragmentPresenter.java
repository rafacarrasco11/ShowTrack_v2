package com.example.showtrack.ui.prf.profile.settings;

public class SettingsFragmentPresenter implements SettingsFragmentContract.Presenter,SettingsFragmentContract.OnInteractorListener {

    private SettingsFragmentContract.View view;
    private SettingsFragmentInteractor interactor;

    public SettingsFragmentPresenter(SettingsFragmentContract.View view) {
        this.view = view;
        this.interactor = new SettingsFragmentInteractor(this);
    }

    @Override
    public void changeName(String name) {
        this.interactor.changeName(name);
    }

    @Override
    public void changePassword(String passwd, String actPasswd) {
        this.interactor.changePasswd(passwd, actPasswd);
    }

    @Override
    public void changeLanguage(String lang) {
        this.interactor.changeLang(lang);
    }

    @Override
    public void LogOut() {
        this.interactor.logOut();
    }

    @Override
    public void DeleteAccount() {
        this.interactor.delAccount();
    }



    @Override
    public void onDestroy() {
        this.view = null;
        this.interactor = null;
    }

    @Override
    public void onNameEmptyError() {
        this.view.setNameEmptyError();
    }

    @Override
    public void onNameError() {
        this.view.setNameError();
    }

    @Override
    public void onPasswordEmptyError() {
        this.view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordError() {
        this.view.setPasswordError();
    }

    @Override
    public void onActualPasswordEmptyError() {
        this.view.setActualPasswordEmptyError();
    }

    @Override
    public void onSuccessChangeName(String message) {
        this.view.onSuccessChangeName(message);
    }

    @Override
    public void onSuccessChangePassword(String message) {
        this.view.onSuccessChangePassword(message);
    }

    @Override
    public void onFailureChangePassword(String message) {
        this.view.onFailureChangePassword(message);
    }

    @Override
    public void onSuccessChangeLang(String message) {
        this.view.onSuccessChangeLang(message);
    }

    @Override
    public void onSuccessLogOut() {
        this.view.onSuccessLogOut();
    }

    @Override
    public void onSuccessDeleteAccount() {
        this.view.onSuccessDeleteAccount();

    }

}
