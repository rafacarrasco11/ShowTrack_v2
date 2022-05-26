package com.example.showtrack.ui.srs.seriesrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showtrack.R;
import com.example.showtrack.data.model.recycler.RecyclerSerie;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.ui.ShowTrackApplication;

import java.util.ArrayList;

public class RecyclerSerieAdapter extends RecyclerView.Adapter<RecyclerSerieAdapter.ViewHolderSeries> {
    private ArrayList<RecyclerSerie> recyclersList;

    public RecyclerSerieAdapter() {
        this.recyclersList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerSerieAdapter.ViewHolderSeries onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_serie_view, parent, false);
        return new RecyclerSerieAdapter.ViewHolderSeries(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerSerieAdapter.ViewHolderSeries holder, int position) {

        RecyclerSerie recyclerSerie = this.recyclersList.get(position);

        holder.tvTittleSeriesRv.setText(recyclerSerie.getTittle());


        SeriesAdapter adapter = new SeriesAdapter(recyclerSerie.getGenre());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowTrackApplication.context(), RecyclerView.HORIZONTAL, false);

        holder.nestedRvSeries.setLayoutManager(layoutManager);
        holder.nestedRvSeries.setAdapter(adapter);


        adapter.update(SerieRepository.getInstance().cargarSeries(recyclerSerie.getGenre()));
    }

    @Override
    public int getItemCount() {
        return this.recyclersList.size();
    }


    public class ViewHolderSeries extends RecyclerView.ViewHolder{

        TextView tvTittleSeriesRv;
        RecyclerView nestedRvSeries;


        public ViewHolderSeries(@NonNull View itemView) {
            super(itemView);

            tvTittleSeriesRv = itemView.findViewById(R.id.tvTittleSeriesRv);
            nestedRvSeries = itemView.findViewById(R.id.nestedRvSeries);
        }
    }

    public void update(ArrayList<RecyclerSerie> recyclersList) {
        this.recyclersList.clear();
        this.recyclersList.addAll(recyclersList);

        notifyDataSetChanged();
    }
}
