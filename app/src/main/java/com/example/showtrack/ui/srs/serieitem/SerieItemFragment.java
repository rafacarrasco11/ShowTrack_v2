package com.example.showtrack.ui.srs.serieitem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.showtrack.R;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.databinding.FragmentSerieItemBinding;
import com.example.showtrack.ui.ShowTrackApplication;

public class SerieItemFragment extends Fragment implements SerieItemContract.View {

    private FragmentSerieItemBinding binding;
    private SerieItemPresenter presenter;

    private Serie serie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.serie = ShowTrackApplication.getSerieTemp();
        this.presenter = new SerieItemPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSerieItemBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        if (!cargarPelicula()) {
            NavHostFragment.findNavController(this).navigateUp();
        }

        binding.btnAddSerieSerieItem.setOnClickListener(v -> {
            presenter.addSerie(this.serie);
        });

        binding.btnBackSerieItem.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
        });
    }


    @Override
    public void onSuccessAddSerie ( String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

        binding.btnAddSerieSerieItem.setImageDrawable(AppCompatResources.getDrawable(getContext(),R.drawable.ic_check_item_added));
        binding.btnAddSerieSerieItem.setBackgroundColor(getActivity().getColor(R.color.greenCheck));
    }

    @Override
    public void onSuccessRemoveSerie (String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private boolean cargarPelicula () {
        if (this.serie != null) {
            binding.imageSerieItem.setImageDrawable(serie.getPoster());
            binding.collapsingToolbarSerieItem.setTitle(serie.getTittle());
            binding.tittleSerieItem.setText(serie.getTittle());
            binding.textSerieItem.setText(serie.makePlot());

            return true;
        }

        return false;
    }


}