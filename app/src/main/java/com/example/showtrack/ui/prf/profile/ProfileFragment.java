package com.example.showtrack.ui.prf.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.showtrack.R;
import com.example.showtrack.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                goSettings();
                return true;
            case R.id.action_aboutus:
                goAboutUs();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Fragment newInstance(Bundle bundle) {
        ProfileFragment fragment = new ProfileFragment();

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    public void goAboutUs() {
        NavHostFragment.findNavController(this).navigate(R.id.aboutUsFragment);
    }

    public void goSettings() {
        NavHostFragment.findNavController(this).navigate(R.id.settingsFragment);
    }
}