package com.example.showtrack.data.repository.prev;

import androidx.annotation.NonNull;

import com.example.showtrack.R;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.prev.login.LoginContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepository implements LoginContract.Repository {

    private static LoginRepository instance;

    public static LoginRepository getInstance() {
        if (instance == null)
            instance = new LoginRepository();

        return instance;
    }

    @Override
    public void login(String email, String passwd, LoginContract.OnLoginCallback callback) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email,passwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            callback.onSuccess(String.valueOf(ShowTrackApplication.getContext().getString(R.string.Login_succesLogin)));
                        else
                            callback.onFailure(String.valueOf(ShowTrackApplication.getContext().getString(R.string.signIn_failure_login)));
                    }
                });
    }
}
