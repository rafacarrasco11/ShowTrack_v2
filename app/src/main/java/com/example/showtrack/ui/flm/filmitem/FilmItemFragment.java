package com.example.showtrack.ui.flm.filmitem;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.api.APIClasses.APIFilms;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.data.repository.UserRepository;
import com.example.showtrack.databinding.FragmentFilmItemBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.utils.DrawableUtil;

import java.io.IOException;

/**
 * Clase para el fragmento donde se enseña informacion comlpeta de una pelicula, desde aqui se puede añadir com vista en la base de datos.
 *
 * Funciona con un modelo Vista-Presentador.
 */
public class FilmItemFragment extends Fragment implements FilmItemContract.View {

    private FragmentFilmItemBinding binding;
    private FilmItemPresenter presenter;

    private Film film;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.film = APIFilms.getFilmFullInfo(ShowTrackApplication.getFilmTemp());
        this.presenter = new FilmItemPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFilmItemBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        if (!cargarPelicula()) {
            NavHostFragment.findNavController(this).navigateUp();
        }

        binding.btnAddFilmFilmItem.setOnClickListener(v -> {
            presenter.addFilm(this.film);
        });

        binding.btnBackFilmItem.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
        });

        binding.btnDeleteFilmFilmItem.setOnClickListener(v -> {
            presenter.removeFilm(this.film);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    @Override
    public void onSuccessAddFilm(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        String content = this.film.getTittle();
        Bundle bundle = new Bundle();
        bundle.putSerializable("film", this.film);

        ShowTrackApplication.newNotification(
                bundle,
                getActivity(),
                R.id.filmItemFragment,
                R.drawable.ic_watched,
                ShowTrackApplication.context().getString(R.string.filmAddedTitle_notification),
                content
        );

        binding.btnAddFilmFilmItem.setVisibility(View.GONE);
        binding.btnDeleteFilmFilmItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessRemoveFilm(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        binding.btnAddFilmFilmItem.setVisibility(View.VISIBLE);
        binding.btnDeleteFilmFilmItem.setVisibility(View.GONE);
    }

    private boolean cargarPelicula() {
        try {
            binding.imageFilmItem.setImageDrawable(DrawableUtil.drawableFromUrl(film.getPoster()));
            //binding.collapsingToolbarFilmItem.setTitle(film.getTittle());
            binding.tittleFilmItem.setText(film.getTittle());
            binding.textYearReleasedFilmItem.setText(film.getYearReleased());
            binding.textRuntimeFilmItem.setText(film.getTime());
            binding.textYearGenreFilmItem.setText(film.getGenre());
            binding.textRatingFilmItem.setText( film.getImdbRating());
            binding.textDirectorFilmItem.setText(film.getDirector());
            binding.textActorsFilmItem.setText(film.getActors());
            binding.textWritersFilmItem.setText(film.getWriters());
            binding.textPLotFilmItem.setText(film.getPlot());
            binding.textAwardsFilmItem.setText(film.getAwards());

            if (FilmRepository.getInstance().isWatched(this.film, ShowTrackApplication.getUserTemp())) {
                binding.btnAddFilmFilmItem.setVisibility(View.GONE);
                binding.btnDeleteFilmFilmItem.setVisibility(View.VISIBLE);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}