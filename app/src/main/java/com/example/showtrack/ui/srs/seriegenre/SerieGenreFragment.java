package com.example.showtrack.ui.srs.seriegenre;

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
import com.example.showtrack.data.model.recycler.RecyclerSerie;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.databinding.FragmentSerieGenreBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.srs.seriesrecycler.RecyclerSerieFragment;
import com.example.showtrack.ui.srs.seriesrecycler.SerieAdapter;

import java.util.ArrayList;

/**
 * Clase para el fragmento donde se enseña una lista en recycler view con series sobre un genero.
 *
 * Funciona con un modelo Vista-Presentador.
 */
public class SerieGenreFragment extends Fragment implements SerieGenreContract.View, SerieAdapter.OnSeriesListener {

    private FragmentSerieGenreBinding binding;
    private SerieGenrePresenter presenter;

    private SerieAdapter adapter;

    private RecyclerSerie recyclerSerie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new SerieGenrePresenter(this);

        this.recyclerSerie = ShowTrackApplication.getRecyclerSerieTemp();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSerieGenreBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        initRv();

        if (this.recyclerSerie.getGenre() == null)
            presenter.cargarSeriesRvByList(this.recyclerSerie.getList());
        else if (this.recyclerSerie.getList() == null)
            presenter.cargarSeriesRvByGenre(this.recyclerSerie.getGenre());

        binding.tvTittleSerieGenre.setText( this.recyclerSerie.getTittle());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    private void initRv() {
        adapter = new SerieAdapter(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);

        binding.rvSerieGenre.setLayoutManager(layoutManager);
        binding.rvSerieGenre.setAdapter(adapter);
    }


    @Override
    public void onVisitSerie(Serie serie) {
        ShowTrackApplication.setSerieTemp(serie);
        NavHostFragment.findNavController(this).navigate(R.id.action_serieGenreFragment_to_serieItemFragment);
    }

    @Override
    public void onChangeSerie(Serie serie) {
//
    }

    @Override
    public void onSuccessCargarSeriesRv(ArrayList<Serie> rvList) {
        adapter.update(rvList);
    }
}