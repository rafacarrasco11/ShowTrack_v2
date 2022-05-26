package com.example.showtrack.ui.srs.seriesrecycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.example.showtrack.databinding.FragmentRecyclerSeriesBinding;

import java.util.ArrayList;


public class RecyclerSerieFragment extends Fragment implements SeriesContract.View {

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

        initAdapterSeriesRv();

        presenter.cargarSeriesRv();
    }

    private void initAdapterSeriesRv() {
        adapter = new RecyclerSerieAdapter();
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
}