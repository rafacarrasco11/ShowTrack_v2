package com.example.showtrack.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.showtrack.R;
import com.example.showtrack.databinding.FragmentDashboardBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmsrecycler.RecyclerFilmsFragment;
import com.example.showtrack.ui.hm.HomeFragment;
import com.example.showtrack.ui.prf.profile.prof.ProfileFragment;
import com.example.showtrack.ui.srs.seriesrecycler.RecyclerSerieFragment;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShowTrackApplication.setLastFragment(HomeFragment.newInstance(null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNavigation();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFragment(ShowTrackApplication.getLastFragment());
    }


    public void loadFragment(Fragment newInstance) {
        if (newInstance != null) {
            getChildFragmentManager().beginTransaction().replace(R.id.dashboardContent, newInstance).commit();
        }
    }


    private void initNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.actionDashboardHome:
                    loadFragment(HomeFragment.newInstance(null));
                    ShowTrackApplication.setLastFragment(HomeFragment.newInstance(null));
                    break;
                case R.id.actionDashboardFilms:
                    loadFragment(RecyclerFilmsFragment.newInstance(null));
                    ShowTrackApplication.setLastFragment(RecyclerFilmsFragment.newInstance(null));
                    break;
                case R.id.actionDashboardSeries:
                    loadFragment(RecyclerSerieFragment.newInstance(null));
                    ShowTrackApplication.setLastFragment(RecyclerSerieFragment.newInstance(null));
                    break;
                case R.id.actionDashboardProfile:
                    loadFragment(ProfileFragment.newInstance(null));
                    ShowTrackApplication.setLastFragment(ProfileFragment.newInstance(null));
                    break;
            }

            return true;
        });
    }

}