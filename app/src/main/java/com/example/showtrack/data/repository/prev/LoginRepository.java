package com.example.showtrack.data.repository.prev;

import androidx.annotation.NonNull;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.dao.UserDao;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.data.repository.UserRepository;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.prev.login.LoginContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

/**
 * Clase repositorio de Login
 *
 * Aqui se relizan las operaciones para logear y conectar con Firebase.
 * Ademas cunado se logea se pone el usuario logeado como "principal" es decir que cada vez que añadamos serie o pelicula se añadira a este.
 * Esto se consigue con la clase Application
 *
 * Devuelve un callback con el resultado de la operacion (MVP)
 */
public class LoginRepository implements LoginContract.Repository {

    private static LoginRepository instance;

    private static UserDao userDao;

    public static LoginRepository getInstance() {
        if (instance == null)
            instance = new LoginRepository();

        userDao = ShowTrackDatabase.getDatabase().userDao();
        return instance;
    }

    @Override
    public void login(String email, String passwd, LoginContract.OnLoginCallback callback) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email,passwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            try {
                                List<User> users = ShowTrackDatabase.databaseWriteExecutor.submit(() -> userDao.getUsers()).get();

                                for ( User user : users) {
                                    if (user.getEmail().equalsIgnoreCase(email)) {
                                        callback.onSuccess(ShowTrackApplication.context().getString(R.string.Login_succesLogin));
                                        ShowTrackApplication.setUserTemp(user);
                                        return;
                                    }
                                }

                                User user = new User();

                                user.setEmail(email);
                                user.setUsername(email);
                                user.setGenreOne(Genres.Thriller.name());
                                user.setGenreThree(Genres.Comedy.name());
                                user.setGenreTwo(Genres.Action.name());

                                ShowTrackDatabase.databaseWriteExecutor.submit(() -> userDao.insert(user));
                                ShowTrackApplication.setUserTemp(user);

                                ShowTrackApplication.setFilmTemp(null);
                                ShowTrackApplication.setSerieTemp(null);
                                ShowTrackApplication.setGenreTemp(null);


                                callback.onSuccess(String.valueOf(ShowTrackApplication.context().getString(R.string.Login_succesLogin)));
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                            callback.onFailure(ShowTrackApplication.context().getString(R.string.signIn_failure_login));
                    }
                });

    }
}
