package com.example.showtrack.ui.flm.filmgenre;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.databinding.FragmentFilmGenreBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmsrecycler.FilmsAdapter;
import com.example.showtrack.ui.flm.filmsrecycler.RecyclerFilmAdapter;
import com.example.showtrack.ui.flm.filmsrecycler.RecyclerFilmsFragmentPresenter;

import java.util.ArrayList;

public class FilmGenreFragment extends Fragment implements FilmGenreContract.View, FilmsAdapter.OnFilmsListener {

    private FragmentFilmGenreBinding binding;
    private FilmGenrePresenter presenter;

    private FilmsAdapter adapterL;
    private FilmsAdapter adapterR;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new FilmGenrePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFilmGenreBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRvLeft();
        initRvRight();

        presenter.cargarFilmsRvLeft(ShowTrackApplication.getGenreTemp());
        presenter.cargarFilmsRvRight(ShowTrackApplication.getGenreTemp());
     }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    private void initRvRight() {
        adapterR = new FilmsAdapter(ShowTrackApplication.getGenreTemp(),this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvRightFilmGenre.setLayoutManager(layoutManager);
        binding.rvRightFilmGenre.setAdapter(adapterR);
    }

    private void initRvLeft() {
        adapterL = new FilmsAdapter(ShowTrackApplication.getGenreTemp(),this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvLeftFilmGenre.setLayoutManager(layoutManager);
        binding.rvLeftFilmGenre.setAdapter(adapterL);
    }

    @Override
    public void onVisitFilm(Film film) {
        ShowTrackApplication.setFilmTemp(film);
        NavHostFragment.findNavController(this).navigate(R.id.filmItemFragment);
    }

    @Override
    public void onChangeFilm(Film film) {

    }

    @Override
    public void onSuccessCargarFilmsRvLeft(ArrayList<Film> rvList) {
        adapterL.update(rvList);
    }

    @Override
    public void onSuccessCargarFilmsRvRight(ArrayList<Film> rvList) {
        adapterR.update(rvList);
    }
}