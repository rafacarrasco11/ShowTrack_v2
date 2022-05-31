package com.example.showtrack.data.repository;

import com.example.showtrack.data.model.user.Stat;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.ui.prf.profile.prof.ProfileContract;

import java.util.ArrayList;

public class UserRepository implements ProfileContract.UserRepository {
    private static UserRepository instance;
    private User user;

    private UserRepository() {
        this.user = new User("Rafa");
    }


    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    @Override
    public void cargarStatsRv(ProfileContract.OnProfileGenreCallback callback) {
        callback.onSuccessCargarStatsRv((ArrayList<Stat>) user.getMyStats());
    }
}
