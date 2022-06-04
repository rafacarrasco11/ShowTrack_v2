package com.example.showtrack.ui.srs.seriesrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.showtrack.R;
import com.example.showtrack.data.model.api.APIClasses.APISeries;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.utils.DrawableUtil;

import java.io.IOException;
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
        try {
            holder.clBackgroundSerieView.setBackground(DrawableUtil.drawableFromUrl(APISeries.getNewBackground(this.seriesList.get(position))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.seriesList.get(position).getTittle().length() >= 21)
            holder.tvNameSerieView.setText(this.seriesList.get(position).getTittle().substring(0,18).concat("..."));
        else holder.tvNameSerieView.setText(this.seriesList.get(position).getTittle());

        holder.tvYearSerieView.setText(this.seriesList.get(position).getYearReleased());

        holder.bind(this.seriesList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return this.seriesList.size();
    }


    public class ViewHolderSeries extends RecyclerView.ViewHolder{

        LinearLayout clBackgroundSerieView;
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
