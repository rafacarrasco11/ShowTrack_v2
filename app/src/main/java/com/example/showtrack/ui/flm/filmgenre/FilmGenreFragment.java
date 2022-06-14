package com.example.showtrack.ui.flm.filmgenre;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.databinding.FragmentFilmGenreBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmsrecycler.FilmsAdapter;

import java.util.ArrayList;

/**
 * Clase para el fragmento donde se enseña una lista en recycler view con peliculas sobre un genero.
 *
 * Funciona con un modelo Vista-Presentador.
 */
public class FilmGenreFragment extends Fragment implements FilmGenreContract.View, FilmsAdapter.OnFilmsListener {

    private FragmentFilmGenreBinding binding;
    private FilmGenrePresenter presenter;

    private FilmsAdapter adapter;

    private RecyclerFilm recyclerFilm;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new FilmGenrePresenter(this);
        this.recyclerFilm = ShowTrackApplication.getRecyclerFilmTemp();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFilmGenreBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        initRv();

        if (this.recyclerFilm.getGenre() == null)
            presenter.cargarFilmsRvByList(this.recyclerFilm.getList());
        else if (this.recyclerFilm.getList() == null)
            presenter.cargarFilmsRvByGenre(this.recyclerFilm.getGenre());

            binding.tvTittleFilmGenre.setText( this.recyclerFilm.getTittle());
     }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    private void initRv() {
        adapter = new FilmsAdapter(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2, RecyclerView.VERTICAL, false);

        binding.rvFilmGenre.setLayoutManager(layoutManager);
        binding.rvFilmGenre.setAdapter(adapter);
    }


    @Override
    public void onVisitFilm(Film film) {
        ShowTrackApplication.setFilmTemp(film);
        NavHostFragment.findNavController(this).navigate(R.id.action_filmGenreFragment_to_filmItemFragment);
    }

    @Override
    public void onChangeFilm(Film film) {
//
    }

    @Override
    public void onSuccessCargarFilmsRv(ArrayList<Film> rvList) {
        adapter.update(rvList);
    }
}