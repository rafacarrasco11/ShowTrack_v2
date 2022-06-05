package com.example.showtrack.ui.srs.seriesearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.showtrack.R;

import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.databinding.FragmentSerieSearchBinding;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.srs.seriesrecycler.SerieAdapter;

import java.util.ArrayList;

public class SerieSearchFragment extends Fragment implements SerieSearchContract.View, SerieAdapter.OnSeriesListener {

    private FragmentSerieSearchBinding binding;
    private SerieSearchPresenter presenter;
    private SerieAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = new SerieSearchPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSerieSearchBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        binding.btnSearchSerieSearch.setOnClickListener(v -> {
            showProgress();
            presenter.search(binding.tieSerieSearch.getText().toString());
        });

        initRvSearch();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    private void initRvSearch() {
        adapter = new SerieAdapter(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);

        binding.rvSeriesSearched.setLayoutManager(layoutManager);
        binding.rvSeriesSearched.setAdapter(adapter);
    }

    @Override
    public void setSearchTextEmptyError() {
        binding.tilSerieSearch.setError(getString(R.string.tilSerieSearch_emptyError));
    }

    @Override
    public void showProgress() {
        //binding.pbSerieSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        //binding.pbSerieSearch.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onSuccessSearchSerie(ArrayList<Serie> rvList) {
        adapter.update(rvList);
        hideProgress();
    }

    @Override
    public void onFailureSearchSerie(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        hideProgress();
    }

    @Override
    public void onVisitSerie(Serie Serie) {
        ShowTrackApplication.setSerieTemp(Serie);
        NavHostFragment.findNavController(this).navigate(R.id.action_serieSearchFragment_to_serieItemFragment);
    }

    @Override
    public void onChangeSerie(Serie Serie) {

    }
}