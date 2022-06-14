package com.example.showtrack.ui.hm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.showtrack.data.model.New;
import com.example.showtrack.databinding.FragmentHomeBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.dashboard.DashboardFragment;

import java.util.ArrayList;

/**
 * PANTALLA HOME.
 *
 * Esta es la clase para el fragmento home, donde se muestran las noticias.
 *
 * Estas noticias se muestran en un recycler view usando el adaptor para noticias creado.
 * Se añaden 5 noticias buscadas por keyword: cine, pelicula y serie.
 */
public class HomeFragment extends Fragment implements HomeContract.View, NewsAdapter.OnNewsListener {

    private FragmentHomeBinding binding;
    private NewsAdapter adapterNews;
    private HomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new HomePresenter(this);
        ShowTrackApplication.setContext(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tvTittleUserNameHomeFragment.setText(ShowTrackApplication.getUserTemp().getUsername());

        initRvNews();
        this.presenter.cargarRvNews();
        ShowTrackApplication.setLastFragment(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    private void initRvNews() {
        adapterNews = new NewsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false );

        binding.rvNews.setLayoutManager(linearLayoutManager);
        binding.rvNews.setAdapter(adapterNews);
    }

    public static Fragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    @Override
    public void onSuccessCargarRvNews(ArrayList<New> rvList) {
        adapterNews.update(rvList);
    }

    @Override
    public void onClickNew(New neW) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(neW.getUrl()));
        startActivity(browserIntent);
    }
}