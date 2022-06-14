package com.example.showtrack.data.repository.prev;

import androidx.annotation.NonNull;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.dao.UserDao;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.prev.signup.SignUpContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Clase repositorio de Registro
 *
 * Aqui se relizan las operaciones para registrarse y conectar con Firebase.
 * Devuelve un callback con el resultado de la operacion (MVP)
 */
public class SignUpRepository implements SignUpContract.Repository {

    private static SignUpRepository instance;

    private static UserDao userDao;

    public static SignUpRepository getInstance() {
        if (instance == null)
            instance = new SignUpRepository();

        userDao = ShowTrackDatabase.getDatabase().userDao();
        return instance;
    }

    @Override
    public void signUp(String email, String passwd, String conPasswd, String userName, SignUpContract.OnSignUpCallback callback) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email,passwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User newUser = new User();

                            newUser.setEmail(email);
                            newUser.setUsername(userName);
                            newUser.setGenreOne(Genres.Thriller.name());
                            newUser.setGenreThree(Genres.Comedy.name());
                            newUser.setGenreTwo(Genres.Action.name());

                            ShowTrackDatabase.databaseWriteExecutor.submit(() -> userDao.insert(newUser));

                            callback.onSuccess(ShowTrackApplication.context().getString(R.string.signUp_success));
                        }
                        else
                            callback.onFailure(ShowTrackApplication.context().getString(R.string.signUp_failure));
                    }
                });
    }
}
