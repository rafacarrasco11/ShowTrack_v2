package com.example.showtrack.ui.flm.filmitem;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.databinding.FragmentFilmItemBinding;
import com.example.showtrack.ui.ShowTrackApplication;

public class FilmItemFragment extends Fragment implements FilmItemContract.View {

    private FragmentFilmItemBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFilmItemBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!cargarPelicula())
            goBack();
    }

    @Override
    public void onSuccessAddFilm (Film film){

    }

    @Override
    public void onSuccessRemoveFilm (Film film){

    }


    private void goBack () {
        NavHostFragment.findNavController(this).navigate(R.id.filmGenreFragment);
    }

    private boolean cargarPelicula () {
        Film film = ShowTrackApplication.getFilmTemp();

        if (film != null) {
            binding.imageFilmItem.setImageDrawable(film.getImage());
            binding.collapsingToolbarFilmItem.setTitle(film.get);
            binding.imageFilmItem.setImageDrawable(film.getImage());
            binding.imageFilmItem.setImageDrawable(film.getImage());
        }

        return false;
    }
}