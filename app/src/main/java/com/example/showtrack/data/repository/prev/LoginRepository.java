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
import java.util.concurrent.ExecutionException;

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
                                    if (user.getEmail().equals(email)) {
                                        callback.onSuccess(String.valueOf(R.string.Login_succesLogin));
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
                                callback.onSuccess(String.valueOf(R.string.Login_succesLogin));
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                            callback.onFailure(String.valueOf(R.string.signIn_failure_login));
                    }
                });

    }
}
