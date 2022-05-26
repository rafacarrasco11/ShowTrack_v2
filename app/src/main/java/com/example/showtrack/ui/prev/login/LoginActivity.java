package com.example.showtrack.ui.prev.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.showtrack.MainActivity;
import com.example.showtrack.data.model.User;
import com.example.showtrack.databinding.ActivityLoginBinding;
import com.example.showtrack.ui.prev.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private ActivityLoginBinding binding;
    private LoginPresenter presenter;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getPreferences(MODE_PRIVATE);


        if (PreferenceManager.getDefaultSharedPreferences(this).getString("checked", "false").toString().equals("true"))
            binding.chkRembMeLogin.setChecked(true);

        if (!PreferenceManager.getDefaultSharedPreferences(this).getString(User.TAG, "none").equals("none"))
            binding.tieUserLogin.setText(PreferenceManager.getDefaultSharedPreferences(this).getString(User.TAG, "none"));

        hideProgress();

        binding.btnSignUpLogin.setOnClickListener(v -> {
            goSignUp();
        });

        binding.btnSignInLogin.setOnClickListener( v -> {
            showProgress();
            presenter.login(binding.tieUserLogin.getText().toString(),binding.tiePaswdLogin.getText().toString());
        });

        presenter = new LoginPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null;
    }

    private void goSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void goApp() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void setEmailEmptyError(String message) {
        binding.tilUserLogin.setError(message);
        hideProgress();
    }

    @Override
    public void setEmailError(String message) {
        binding.tilUserLogin.setError(message);
        hideProgress();
    }

    @Override
    public void setPasswordEmptyError(String message) {
        binding.tilPaswdLogin.setError(message);
        hideProgress();
    }

    @Override
    public void setPasswordError(String message) {
        binding.tilPaswdLogin.setError(message);
        hideProgress();
    }

    @Override
    public void setLoginError(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        hideProgress();
    }

    @Override
    public void showProgress() {
        binding.pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(String message) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        if (binding.chkRembMeLogin.isChecked()) {
            editor.putString(User.TAG, binding.tieUserLogin.getText().toString());
            editor.apply();

            editor.putString("checked","true");
            editor.apply();
        }
        hideProgress();

        goApp();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        hideProgress();
    }
}