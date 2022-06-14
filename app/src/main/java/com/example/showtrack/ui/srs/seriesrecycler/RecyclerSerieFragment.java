package com.example.showtrack.ui.srs.seriesrecycler;

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
import com.example.showtrack.data.model.recycler.RecyclerSerie;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.databinding.FragmentRecyclerSeriesBinding;
import com.example.showtrack.ui.ShowTrackApplication;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Fragmento PRINCIPAL de la aplicaion. PANTALLA SERIES.
 *
 * Aqui podemos observar las diferentes listas de series las cuales se componen de un recycler view de series.
 * Estas listas son cargadas en otro Recycler View.
 *
 * Se usa el modelo Vista-Presentador.
 */
public class RecyclerSerieFragment extends Fragment implements RecyclerSerieContract.View, RecyclerSerieAdapter.OnRecyclerSerieListener {

    private FragmentRecyclerSeriesBinding binding;
    private RecyclerSerieAdapter adapter;
    private RecyclerSerieFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new RecyclerSerieFragmentPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRecyclerSeriesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        //Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).show();

        initAdapterSeriesRv();

        presenter.cargarSeriesRv();

        ShowTrackApplication.setContext(getContext());
        SerieRepository.getInstance();

        ShowTrackApplication.setLastFragment(this);
    }



    private void initAdapterSeriesRv() {
        adapter = new RecyclerSerieAdapter( this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvSeries.setLayoutManager(layoutManager);
        binding.rvSeries.setAdapter(adapter);
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
                goSerieSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static Fragment newInstance(Bundle bundle) {
        RecyclerSerieFragment fragment = new RecyclerSerieFragment();

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    @Override
    public void onSuccessCargarSeriesRv(ArrayList<RecyclerSerie> rvList) {
        adapter.update(rvList);
    }


    @Override
    public void onVisitGenre(RecyclerSerie serie, int numberGenre) {
        ShowTrackApplication.setGenreTemp(serie.getGenre());
        ShowTrackApplication.setGenreTemp(serie.getGenre());
        ShowTrackApplication.setRecyclerSerieTemp(serie);
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_serieGenreFragment);
    }

    @Override
    public void onVisitSerie(Serie serie) {
        ShowTrackApplication.setSerieTemp(serie);
        NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_serieItemFragment);
    }

    //#region NAVEGACION

    public void goSerieSearch() { NavHostFragment.findNavController(this).navigate(R.id.action_dashboardFragment_to_serieSearchFragment); }

    //#endregion


}
