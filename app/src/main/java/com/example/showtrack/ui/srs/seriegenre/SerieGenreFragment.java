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
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.databinding.FragmentSerieGenreBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.srs.seriesrecycler.SerieAdapter;

import java.util.ArrayList;

public class SerieGenreFragment extends Fragment implements SerieGenreContract.View, SerieAdapter.OnSeriesListener {

    private FragmentSerieGenreBinding binding;
    private SerieGenrePresenter presenter;

    private SerieAdapter adapterL;
    private SerieAdapter adapterR;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new SerieGenrePresenter(this);
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

        initRvLeft();
        initRvRight();

        presenter.cargarSeriesRvLeft(ShowTrackApplication.getGenreTemp());
        presenter.cargarSeriesRvRight(ShowTrackApplication.getGenreTemp());

        binding.tvTittleSerieGenre.setText(getString(R.string.tvTittleSerieGenre_tittleText) + ShowTrackApplication.getGenreTemp());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    private void initRvRight() {
        adapterR = new SerieAdapter(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvRightSerieGenre.setLayoutManager(layoutManager);
        binding.rvRightSerieGenre.setAdapter(adapterR);
    }

    private void initRvLeft() {
        adapterL = new SerieAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        binding.rvLeftSerieGenre.setLayoutManager(layoutManager);
        binding.rvLeftSerieGenre.setAdapter(adapterL);
    }

    @Override
    public void onVisitSerie(Serie serie) {
        ShowTrackApplication.setSerieTemp(serie);
        NavHostFragment.findNavController(this).navigate(R.id.action_serieGenreFragment_to_serieItemFragment);
    }

    @Override
    public void onChangeSerie(Serie serie) {

    }

    @Override
    public void onSuccessCargarSeriesRvLeft(ArrayList<Serie> rvList) {
        adapterL.update(rvList);
    }

    @Override
    public void onSuccessCargarSeriesRvRight(ArrayList<Serie> rvList) {
        adapterR.update(rvList);
    }
}