package com.example.showtrack.ui.srs.seriesrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.showtrack.R;
import com.example.showtrack.data.model.serie.Serie;

import java.util.ArrayList;

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.ViewHolderSeries> {
    private ArrayList<Serie> seriesList;
    private OnSeriesListener listener;

    public interface OnSeriesListener{
        void onVisitSerie(Serie serie);
        void onChangeSerie(Serie serie);
    }
    
    public SerieAdapter(SerieAdapter.OnSeriesListener listener) {
        this.seriesList = new ArrayList<>();
        this.listener = listener;
    }

    
    @NonNull
    @Override
    public SerieAdapter.ViewHolderSeries onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serie_view, parent, false);
        return new SerieAdapter.ViewHolderSeries(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SerieAdapter.ViewHolderSeries holder, int position) {
        //holder.clBackgroundSerieView.setBackground(this.SeriesList.get(position).getImage());
        holder.tvNameSerieView.setText(this.seriesList.get(position).getTittle());
        holder.tvYearSerieView.setText(Integer.toString(this.seriesList.get(position).getSerieYear()));

        holder.bind(this.seriesList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return this.seriesList.size();
    }


    public class ViewHolderSeries extends RecyclerView.ViewHolder{

        ConstraintLayout clBackgroundSerieView;
        TextView tvNameSerieView;
        TextView tvYearSerieView;

        public ViewHolderSeries(@NonNull View itemView) {
            super(itemView);

            clBackgroundSerieView = itemView.findViewById(R.id.clBackgroundSerieView);
            tvNameSerieView = itemView.findViewById(R.id.tvNameSerieView);
            tvYearSerieView = itemView.findViewById(R.id.tvYearSerieView);
        }


        public void bind(Serie serie, OnSeriesListener listener) {
            itemView.setOnClickListener( v -> {
                listener.onVisitSerie(serie);
            });

            itemView.setOnLongClickListener( v -> {
                listener.onChangeSerie(serie);
                return true;
            });
        }
    }

    public void update(ArrayList<Serie> seriesList) {
        this.seriesList.clear();
        this.seriesList.addAll(seriesList);

        notifyDataSetChanged();
    }
}
