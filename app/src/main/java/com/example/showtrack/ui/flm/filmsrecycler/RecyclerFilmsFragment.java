package com.example.showtrack.ui.flm.filmsrecycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.databinding.FragmentRecyclerFilmsBinding;
import com.example.showtrack.ui.ShowTrackApplication;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Fragmento PRINCIPAL de la aplicaion. PANTALLA PELICULAS.
 *
 * Aqui podemos observar las difernetes listas de peliuclas las cuales se componen de un recycler view de peliculas.
 * Estas listas son cargadas en otro Recycler View.
 *
 * Se usa el modelo Vista-Presentador.
 */
public class RecyclerFilmsFragment extends Fragment implements RecyclerFilmsContract.View, RecyclerFilmAdapter.OnRecyclerFilmListener {

    private FragmentRecyclerFilmsBinding binding;
    private RecyclerFilmAdapter adapter;
    private RecyclerFilmsFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new RecyclerFilmsFragmentPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecyclerFilmsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        //Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();

        initAdapterFilmsRv();

        presenter.cargarFilmsRv();

        ShowTrackApplication.setContext(getContext());
        FilmRepository.getInstance();

        ShowTrackApplication.setLastFragment(this);
    }


    private void initAdapterFilmsRv() {
        adapter = new RecyclerFilmAdapter( this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvFilms.setLayoutManager(layoutManager);
        binding.rvFilms.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.series_films_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                goFilmSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Fragment newInstance(Bundle bundle) {
        RecyclerFilmsFragment fragment = new RecyclerFilmsFragment();

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    @Override
    public void onSuccessCargarFilmsRv(ArrayList<RecyclerFilm> rvList) {
        adapter.update(rvList);
    }


    @Override
    public void onVisitGenre(RecyclerFilm recyclerFilm, int numberGenre) {
        ShowTrackApplication.setGenreTemp(recyclerFilm.getGenre());
        ShowTrackApplication.setGenreTemp(recyclerFilm.getGenre());
        ShowTrackApplication.setRecyclerFilmTemp(recyclerFilm);
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_filmGenreFragment);
    }

    @Override
    public void onVisitFilm(Film film) {
        ShowTrackApplication.setFilmTemp(film);
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_filmItemFragment);
    }

    //#region NAVEGACION

    public void goFilmSearch() { NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_filmSearchFragment); }

    //#endregion


}