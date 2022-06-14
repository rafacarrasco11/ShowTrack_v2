package com.example.showtrack.ui.prev.signup;

import android.os.Bundle;

import com.example.showtrack.R;
import com.example.showtrack.databinding.ActivitySignUpBinding;
import com.example.showtrack.ui.prev.signup.SignUpContract.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

/**
 * Clase para la Activity SIGN UP.
 *
 * En esta actividad se realiza el registro en la aplicacion usando Firebase.
 *
 * Se utiliza el modelo Vista Presentador, por el cual se cotnrolan los errores y nos devuelve un callback con lar respuesta al registro.
 */
public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    private ActivitySignUpBinding binding;
    private SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignUpSignUp.setOnClickListener( v -> {
            showProgress();
            this.presenter.signUp(
                    binding.tieEmailSignUp.getText().toString(),
                    binding.tiePasswdSignUp.getText().toString(),
                    binding.tieConfirmPasswdSignUp.getText().toString(),
                    binding.tieUserSignUp.getText().toString()
            );
        });

        binding.btnSignInSignUp.setOnClickListener(v -> onBackPressed());

        hideProgress();

        //binding.btnSignUpWGoogleSignUp


        this.presenter = new SignUpPresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    @Override
    public void setEmailEmptyError() {
        binding.tilEmailSignUp.setError(getApplicationContext().getString(R.string.tilEmailSignUp_EmptyError));
        hideProgress();
    }

    @Override
    public void setEmailFormatError() {
        binding.tilEmailSignUp.setError(getApplicationContext().getString(R.string.tilEmailSignUp_FormatError));
        hideProgress();
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPasswdSignUp.setError(getApplicationContext().getString(R.string.tilPasswdSignUp_EmptyError));
        hideProgress();
    }

    @Override
    public void setPasswdFormatError() {
        binding.tilPasswdSignUp.setError(getApplicationContext().getString(R.string.tilPasswdSignUp_FormatError));
        hideProgress();
    }

    @Override
    public void setPasswordEqualsError() {
        binding.tilConfirmPasswdSignUp.setError(getApplicationContext().getString(R.string.tilConfirmPasswdSignUp_EqualsError));
        hideProgress();
    }

    @Override
    public void setUserNameFormatError() {
        binding.tilUserSignUp.setError(getApplicationContext().getString(R.string.tilUserSignUp_FormatError));
        hideProgress();
    }

    @Override
    public void setUserNameEmptyError() {
        binding.tilUserSignUp.setError(getApplicationContext().getString(R.string.tilUserSignUp_EmptyError));
        hideProgress();
    }

    @Override
    public void showProgress() {
        this.binding.pbSignUp.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        this.binding.pbSignUp.setVisibility(android.view.View.GONE);
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        hideProgress();
        onBackPressed();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        hideProgress();
    }
}