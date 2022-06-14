package com.example.showtrack.ui.prf.profile.prof;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.Genres;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.model.user.Stat;
import com.example.showtrack.data.model.user.User;
import com.example.showtrack.data.repository.UserRepository;
import com.example.showtrack.databinding.FragmentProfileBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmsrecycler.FilmsAdapter;
import com.example.showtrack.ui.srs.seriesrecycler.SerieAdapter;
import com.example.showtrack.utils.CommonUtils;
import com.example.showtrack.utils.DrawableUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Clase para la PANTALLA PERFIL
 *
 * En este fragmento podemos encontrar nuestra series y nuestras peliculas que hemos añadido.
 *
 * Ademas nos muestra un RecylerView con una lista de estadicticas en funcion a las series y peliculas que hemos visto.
 *
 * Tambien podemos encontrar unos botones en el fondo de la pantalla donde podremos eliminar todas las series o eliminar todas las peliculas con solo pulsarlo.
 *
 * Ademas hay un boton para navegar a la pantalla About Us.
 *
 * Utiliza el modelo Vista presentador el cual controla los errores y los resultados de las operaciones.
 *
 */
public class ProfileFragment extends Fragment implements ProfileContract.View, FilmsAdapter.OnFilmsListener, SerieAdapter.OnSeriesListener  {
    private FragmentProfileBinding binding;
    private ProfilePresenter presenter;

    private FilmsAdapter adapterFilms;
    private SerieAdapter adapterSeries;

    private StatAdapter adapterStats;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ShowTrackApplication.setContext(getContext());

        this.presenter = new ProfilePresenter(this);
        try {
            ShowTrackApplication.getUserTemp().generateStats();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        ShowTrackApplication.setLastFragment(this);

        showDataFilms();
        showDataSeries();

        initRvFilms();
        initRvSeries();
        initRvStats();

        try {
            setProfileInformation();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        binding.btnDeleteAllFilms.setOnClickListener(v -> {
            presenter.deleteAllFilms();
            presenter.cargarFilmsRv();
            presenter.cargarStatsRv();
        });

        binding.btnDeleteAllSeries.setOnClickListener(v -> {
            presenter.deleteAllSeries();
            presenter.cargarSeriesRv();
            presenter.cargarStatsRv();

        });

        setUserGenres();
    }

    private void setUserGenres() {
        List<String> genres = UserRepository.getInstance().getUserSeriesAndFilmsGenres();

        if (genres.size() == 0 ) {
            ShowTrackApplication.getUserTemp().setGenreOne(Genres.Action.name());
            ShowTrackApplication.getUserTemp().setGenreTwo(Genres.Comedy.name());
            ShowTrackApplication.getUserTemp().setGenreThree(Genres.Drama.name());
        } else if (genres.size() == 1 ) {
            ShowTrackApplication.getUserTemp().setGenreOne(genres.get(0));
            ShowTrackApplication.getUserTemp().setGenreTwo(Genres.Comedy.name());
            ShowTrackApplication.getUserTemp().setGenreThree(Genres.Drama.name());
        } else if (genres.size() == 2 ) {
            ShowTrackApplication.getUserTemp().setGenreOne(genres.get(0));
            ShowTrackApplication.getUserTemp().setGenreTwo(genres.get(1));
            ShowTrackApplication.getUserTemp().setGenreThree(Genres.Drama.name());
        } else {
            ShowTrackApplication.getUserTemp().setGenreOne(genres.get(0));
            ShowTrackApplication.getUserTemp().setGenreTwo(genres.get(1));
            ShowTrackApplication.getUserTemp().setGenreThree(genres.get(genres.size()-1));
        }

    }


    private void setProfileInformation() throws IOException, ExecutionException, InterruptedException {
        presenter.cargarFilmsRv();
        presenter.cargarSeriesRv();
        presenter.cargarStatsRv();

        binding.tvUserNameProfileFragment.setText(ShowTrackApplication.getUserTemp().getUsername());
        if (ShowTrackApplication.getFilmTemp() != null || ShowTrackApplication.getSerieTemp() != null) {
            if (ShowTrackApplication.getFilmTemp() == null)
                binding.llBackgroundProfileFragment.setBackground(DrawableUtil.drawableFromUrl(ShowTrackApplication.getSerieTemp().getPoster()));
            else
                binding.llBackgroundProfileFragment.setBackground(DrawableUtil.drawableFromUrl(ShowTrackApplication.getFilmTemp().getPoster()));
        }

        binding.btnAboutUsSettingsFragment.setOnClickListener(v -> {
            goAboutUs();
        });

        if (UserRepository.getInstance().getProfilePhoto() != null)
            binding.imgAvatarProfileFragment.setImageBitmap(CommonUtils.StringToBitMap(UserRepository.getInstance().getProfilePhoto()));
    }

    public void goAboutUs() {
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_aboutUsFragment);
    }


    private void initRvStats() {
        adapterStats = new StatAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);

        binding.rvStatsProfileFragment.setLayoutManager(layoutManager);
        binding.rvStatsProfileFragment.setAdapter(adapterStats);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                goSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
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
    public void onCargarSeriesRvNoData() {
        noDataSeries();
    }

    @Override
    public void onCargarFilmsRvNoData() {
        noDataFilms();
    }

    @Override
    public void onSuccessCargarStatsRv(ArrayList<Stat> rvList) {
        adapterStats.update(rvList);
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

    private void goSettings() {
        NavHostFragment.findNavController(this).navigate(R.id.settingsFragment);
    }

    @Override
    public void onSuccessDeleteSeries(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessDeleteFilms(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDataFilms() {
        binding.rvFilmProfileFragment.setVisibility(View.VISIBLE);
        binding.llNoDataFilms.setVisibility(View.GONE);
    }

    @Override
    public void showDataSeries() {
        binding.rvSerieProfileFragment.setVisibility(View.VISIBLE);
        binding.llNoDataSeries.setVisibility(View.GONE);
    }

    @Override
    public void noDataFilms() {
        binding.rvFilmProfileFragment.setVisibility(View.GONE);
        binding.llNoDataFilms.setVisibility(View.VISIBLE);

    }

    @Override
    public void noDataSeries() {
        binding.rvSerieProfileFragment.setVisibility(View.GONE);
        binding.llNoDataSeries.setVisibility(View.VISIBLE);
    }
}