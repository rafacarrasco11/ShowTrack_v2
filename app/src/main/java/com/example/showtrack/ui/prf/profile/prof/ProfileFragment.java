package com.example.showtrack.ui.prf.profile.prof;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.databinding.FragmentProfileBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmsrecycler.FilmsAdapter;
import com.example.showtrack.ui.srs.seriesrecycler.SerieAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements ProfileContract.View, FilmsAdapter.OnFilmsListener, SerieAdapter.OnSeriesListener  {
    private FragmentProfileBinding binding;
    private ProfilePresenter presenter;

    private FilmsAdapter adapterFilms;
    private SerieAdapter adapterSeries;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new ProfilePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRvFilms();
        initRvSeries();

        presenter.cargarFilmsRv();
        presenter.cargarSeriesRv();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter = null;
    }

    private void initRvFilms() {
        adapterFilms = new FilmsAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

        binding.rvFilmProfileFragment.setLayoutManager(layoutManager);
        binding.rvFilmProfileFragment.setAdapter(adapterFilms);
    }

    private void initRvSeries() {
        adapterSeries = new SerieAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

        binding.rvSerieProfileFragment.setLayoutManager(layoutManager);
        binding.rvSerieProfileFragment.setAdapter(adapterSeries);
    }

    public static Fragment newInstance(Bundle bundle) {
        ProfileFragment fragment = new ProfileFragment();

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    @Override
    public void onVisitFilm(Film film) {
        ShowTrackApplication.setFilmTemp(film);
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_filmItemFragment);
    }

    @Override
    public void onChangeFilm(Film film) {
//
    }

    @Override
    public void onSuccessCargarSeriessRv(ArrayList<Serie> rvList) {
        adapterSeries.update(rvList);
    }

    @Override
    public void onSuccessCargarFilmsRv(ArrayList<Film> rvList) {
        adapterFilms.update(rvList);
    }

    @Override
    public void onVisitSerie(Serie serie) {
        ShowTrackApplication.setSerieTemp(serie);
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_serieItemFragment);
    }

    @Override
    public void onChangeSerie(Serie serie) {
//
    }

}