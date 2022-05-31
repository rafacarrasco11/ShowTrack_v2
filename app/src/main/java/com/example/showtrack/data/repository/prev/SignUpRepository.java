package com.example.showtrack.data.repository.prev;

import androidx.annotation.NonNull;

import com.example.showtrack.R;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.prev.signup.SignUpContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpRepository implements SignUpContract.Repository {

    private static SignUpRepository instance;

    public static SignUpRepository getInstance() {
        if (instance == null)
            instance = new SignUpRepository();

        return instance;
    }

    @Override
    public void signUp(String email, String passwd, String conPasswd, String userName, SignUpContract.OnSignUpCallback callback) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email,passwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                            callback.onSuccess(ShowTrackApplication.context().getString(R.string.signUp_success));
                        else
                            callback.onFailure(ShowTrackApplication.context().getString(R.string.signUp_failure));
                    }
                });
    }
}
