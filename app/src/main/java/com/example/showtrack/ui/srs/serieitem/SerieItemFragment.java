package com.example.showtrack.ui.srs.serieitem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.showtrack.R;
import com.example.showtrack.data.model.api.APIClasses.APISeries;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.databinding.FragmentSerieItemBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.utils.DrawableUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para el fragmento donde se enseña informacion comlpeta de una serie, desde aqui se puede añadir como vista en la base de datos.
 *
 * Ademas en este fragmento se muestra la informacion de los capitulos en Listas.
 * Primero se genera una lista con todas las temporadas y luego para cada una genere otra lista con los episodios.
 * Esto se hace con un recycler view y un nested recycler view.
 *
 * Funciona con un modelo Vista-Presentador.
 */
public class SerieItemFragment extends Fragment implements SerieItemContract.View {

    private FragmentSerieItemBinding binding;
    private SerieItemPresenter presenter;

    private Serie serie;

    private SeasonAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            this.serie = APISeries.getSerieFullInfo(ShowTrackApplication.getSerieTemp());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.presenter = new SerieItemPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSerieItemBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        if (!cargarSerie()) {
            NavHostFragment.findNavController(this).navigateUp();
        }

        binding.btnAddSerieFilmItem.setOnClickListener(v -> {
            presenter.addSerie(this.serie);
        });

        binding.btnBackSerieItem.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
        });

        binding.btnDeleteSerieFilmItem.setOnClickListener(v -> {
            presenter.removeSerie(this.serie);
        });

        initRvSeasons();
        adapter.update((ArrayList<Season>) this.serie.getSeasons());
    }

    private void initRvSeasons() {
        adapter = new SeasonAdapter(this.serie, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvSeasons.setLayoutManager(layoutManager);
        binding.rvSeasons.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    @Override
    public void onSuccessAddSerie(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        binding.btnAddSerieFilmItem.setVisibility(View.GONE);
        binding.btnDeleteSerieFilmItem.setVisibility(View.VISIBLE);

        String content = this.serie.getTittle();
        Bundle bundle = new Bundle();
        bundle.putSerializable("serie", this.serie);

        ShowTrackApplication.newNotification(
                bundle,
                getActivity(),
                R.id.serieItemFragment,
                R.drawable.ic_watched,
                ShowTrackApplication.context().getString(R.string.serieAddedTitle_notification),
                content
        );


        binding.btnAddSerieFilmItem.setVisibility(View.GONE);
        binding.btnDeleteSerieFilmItem.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessRemoveSerie(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        binding.btnAddSerieFilmItem.setVisibility(View.VISIBLE);
        binding.btnDeleteSerieFilmItem.setVisibility(View.GONE);
    }


    private boolean cargarSerie() {
        try {
            binding.imageSerieItem.setImageDrawable(DrawableUtil.drawableFromUrl(serie.getPoster()));
            //binding.collapsingToolbarSerieItem.setTitle(Serie.getTittle());
            binding.tittleSerieItem.setText(serie.getTittle());
            binding.textYearReleasedSerieItem.setText(serie.getYearReleased());
            binding.textRuntimeSerieItem.setText(serie.getTime());
            binding.textYearGenreSerieItem.setText(serie.getGenre());
            binding.textRatingSerieItem.setText( serie.getImdbRating());
            binding.textDirectorSerieItem.setText(serie.getDirector());
            binding.textActorsSerieItem.setText(serie.getActors());
            binding.textWritersSerieItem.setText(serie.getWriters());
            binding.textPLotSerieItem.setText(serie.getPlot());
            binding.textAwardsSerieItem.setText(serie.getAwards());

            if (SerieRepository.getInstance().isWatched(this.serie, ShowTrackApplication.getUserTemp())) {
                binding.btnAddSerieFilmItem.setVisibility(View.GONE);
                binding.btnDeleteSerieFilmItem.setVisibility(View.VISIBLE);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}