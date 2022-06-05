package com.example.showtrack.ui.srs.seriesrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showtrack.R;
import com.example.showtrack.data.model.recycler.RecyclerSerie;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.ui.ShowTrackApplication;

import java.util.ArrayList;

public class RecyclerSerieAdapter extends RecyclerView.Adapter<RecyclerSerieAdapter.ViewHolderSeries> implements SerieAdapter.OnSeriesListener{

    private ArrayList<RecyclerSerie> recyclersList;
    private OnRecyclerSerieListener listener;

    public interface OnRecyclerSerieListener {
        void onVisitGenre(RecyclerSerie recyclerSerie, int numberGenre);

        void onVisitSerie(Serie serie);
    }

    public RecyclerSerieAdapter( OnRecyclerSerieListener listener) {
        this.recyclersList = new ArrayList<>();
        this.listener = listener;
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


        SerieAdapter adapter = new SerieAdapter( this );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowTrackApplication.context(), RecyclerView.HORIZONTAL, false);

        holder.nestedRvSeries.setLayoutManager(layoutManager);
        holder.nestedRvSeries.setAdapter(adapter);


        if (recyclerSerie.getGenre() == null) {
            adapter.update(SerieRepository.getInstance().cargarSeriesByList(recyclerSerie.getList(),10));
        }
        if (recyclerSerie.getList() == null) {
            adapter.update(SerieRepository.getInstance().cargarSeriesByGenre(recyclerSerie.getGenre(),10));
        }


        holder.llTittleSeriesRv.setOnClickListener(v -> {
            listener.onVisitGenre(this.recyclersList.get(position),position );
        });

        holder.btnVisitGenre.setOnClickListener(v -> {
            listener.onVisitGenre(this.recyclersList.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return this.recyclersList.size();
    }


    public class ViewHolderSeries extends RecyclerView.ViewHolder{

        TextView tvTittleSeriesRv;
        RecyclerView nestedRvSeries;
        LinearLayout llTittleSeriesRv;
        ImageView btnVisitGenre;


        public ViewHolderSeries(@NonNull View itemView) {
            super(itemView);

            tvTittleSeriesRv = itemView.findViewById(R.id.tvTittleSeriesRv);
            nestedRvSeries = itemView.findViewById(R.id.nestedRvSeries);
            llTittleSeriesRv = itemView.findViewById(R.id.llTittleSeriesRv);
            btnVisitGenre = itemView.findViewById(R.id.btnVisitGenre);

        }
    }

    public void update(ArrayList<RecyclerSerie> recyclersList) {
        this.recyclersList.clear();
        this.recyclersList.addAll(recyclersList);

        notifyDataSetChanged();
    }


    @Override
    public void onVisitSerie(Serie serie) {
        /*
        NO SE HIZO CON FRAGMENT DIRECTIONS POR PROBELMAS BOTENIENDO FRAGMENT
        RecyclerSeriesFragmentDirections.ActionSeriesFragmentToSerieItemFragment actionSeriesFragmentToSerieItemFragment
                = RecyclerSeriesFragmentDirections.actionSeriesFragmentToSerieItemFragment(Serie);

        NavHostFragment.findNavController().navigate(actionSeriesFragmentToSerieItemFragment);*/

        ShowTrackApplication.setSerieTemp(serie);
        listener.onVisitSerie(serie);
    }

    @Override
    public void onChangeSerie(Serie serie) {
        //SerieRepository.getInstance().changeSerie(serie);
    }


}

