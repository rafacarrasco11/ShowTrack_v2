package com.example.showtrack.ui.srs.serieitem;

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
import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmsrecycler.RecyclerFilmAdapter;

import java.util.ArrayList;
import java.util.List;


public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.ViewHolderSeasons> {

    private List<Season> seasonList;

    public SeasonAdapter() {
        this.seasonList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolderSeasons onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.season_view, parent, false);
        return new SeasonAdapter.ViewHolderSeasons(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSeasons holder, int position) {
        holder.tvTitleSeasonNumber.setText(ShowTrackApplication.getContext().getString(R.string.seasonItemTitle).concat(String.valueOf(position+1)));

        EpisodeAdapter episodeAdapter = new EpisodeAdapter();
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(ShowTrackApplication.context(), RecyclerView.VERTICAL, false);

        holder.rvEpisodes.setLayoutManager(linearLayoutManager);
        holder.rvEpisodes.setAdapter(episodeAdapter);


        holder.imgBtnSeeEpisodes.setOnClickListener(v -> {
            holder.rvEpisodes.setVisibility(View.VISIBLE);
            episodeAdapter.update((ArrayList<Episode>) this.seasonList.get(position).getEpisodes());

            holder.imgBtnSeeEpisodes.setVisibility(View.GONE);
            holder.imgBtnCloseEpisodes.setVisibility(View.VISIBLE);
        });

        holder.imgBtnCloseEpisodes.setOnClickListener(v -> {
            holder.rvEpisodes.setVisibility(View.GONE);

            holder.imgBtnSeeEpisodes.setVisibility(View.VISIBLE);
            holder.imgBtnCloseEpisodes.setVisibility(View.GONE);
        });

        if (this.seasonList.get(position).getEpisodes().size() == 0) {
            holder.imgBtnSeeEpisodes.setVisibility(View.GONE);
            holder.imgBtnCloseEpisodes.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.seasonList.size();
    }

    public class ViewHolderSeasons extends RecyclerView.ViewHolder {

        TextView tvTitleSeasonNumber;
        RecyclerView rvEpisodes;
        ImageView imgBtnSeeEpisodes;
        ImageView imgBtnCloseEpisodes;

        public ViewHolderSeasons(@NonNull View itemView) {
            super(itemView);
            tvTitleSeasonNumber = itemView.findViewById(R.id.tvTitleSeasonNumber);
            rvEpisodes = itemView.findViewById(R.id.rvEpisodes);
            imgBtnSeeEpisodes = itemView.findViewById(R.id.imgBtnSeeEpisodes);
            imgBtnCloseEpisodes = itemView.findViewById(R.id.imgBtnCloseEpisodes);
        }
    }


    public void update(ArrayList<Season> rvList) {
        this.seasonList.clear();
        this.seasonList.addAll(rvList);

        notifyDataSetChanged();
    }
    
}
