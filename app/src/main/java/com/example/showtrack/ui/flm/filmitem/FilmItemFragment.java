package com.example.showtrack.ui.flm.filmitem;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.databinding.FragmentFilmItemBinding;
import com.example.showtrack.ui.ShowTrackApplication;

public class FilmItemFragment extends Fragment implements FilmItemContract.View {

    private FragmentFilmItemBinding binding;
    private FilmItemPresenter presenter;

    private Film film;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.film = ShowTrackApplication.getFilmTemp();
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
        if (!cargarPelicula())
            goBack();

        binding.btnAddSerieFilmItem.setOnClickListener(v -> {
            presenter.addFilm(this.film);
        });
    }

    @Override
    public void onSuccessAddFilm ( String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        binding.btnAddSerieFilmItem.setImageDrawable(AppCompatResources.getDrawable(getContext(),R.drawable.ic_check_item_added));
        binding.btnAddSerieFilmItem.setBackgroundColor(getActivity().getColor(R.color.greenCheck));
    }

    @Override
    public void onSuccessRemoveFilm (String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    private void goBack () {
        NavHostFragment.findNavController(this).navigate(R.id.filmGenreFragment);
    }

    private boolean cargarPelicula () {
        if (this.film != null) {
            binding.imageFilmItem.setImageDrawable(film.getPoster());
            binding.collapsingToolbarFilmItem.setTitle(film.getTittle());
            binding.tittleFilmItem.setText(film.getTittle());
            binding.textFilmItem.setText(film.makePlot());
        }

        return false;
    }


}