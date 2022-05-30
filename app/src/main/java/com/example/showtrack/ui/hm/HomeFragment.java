package com.example.showtrack.ui.hm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.example.showtrack.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupNews();
    }

    private void setupNews() {
        try {

            String str_tag = "News_Fragment";
            String str_addToBackStack = "News_Fragment";;
            Bundle bundle_news=new Bundle();




        } catch (Exception e)
        {

        }
    }

    public static Fragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }

}