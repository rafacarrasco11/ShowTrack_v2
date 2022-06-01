package com.example.showtrack.ui.flm.filmsearch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.databinding.FragmentFilmSearchBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmsrecycler.FilmsAdapter;

import java.util.ArrayList;

public class FilmSearchFragment extends Fragment implements FilmSearchContract.View, FilmsAdapter.OnFilmsListener {

    private FragmentFilmSearchBinding binding;
    private FilmSearchPresenter presenter;
    private FilmsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new FilmSearchPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFilmSearchBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        binding.btnSearchFilmSearch.setOnClickListener(v -> {
            presenter.search(binding.tieFilmSearch.getText().toString());
            showProgress();
        });

        initRvSearch();
        hideProgress();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter = null;
    }

    private void initRvSearch() {
        adapter = new FilmsAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvFilmsSearched.setLayoutManager(layoutManager);
        binding.rvFilmsSearched.setAdapter(adapter);
    }

    @Override
    public void setSearchTextEmptyError() {
        binding.tilFilmSearch.setError(getString(R.string.tilFilmSearch_emptyError));
    }

    @Override
    public void showProgress() {
        binding.pbFilmSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.pbFilmSearch.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSuccessSearchFilm(ArrayList<Film> rvList) {
        adapter.update(rvList);
        hideProgress();
    }

    @Override
    public void onFailureSearchFilm(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        hideProgress();
    }

    @Override
    public void onVisitFilm(Film film) {
        ShowTrackApplication.setFilmTemp(film);
        NavHostFragment.findNavController(this).navigate(R.id.action_filmSearchFragment_to_filmItemFragment);
    }

    @Override
    public void onChangeFilm(Film film) {

    }
}