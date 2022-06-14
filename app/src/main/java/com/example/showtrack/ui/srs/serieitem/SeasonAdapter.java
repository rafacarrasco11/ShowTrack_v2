package com.example.showtrack.ui.srs.serieitem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showtrack.R;
import com.example.showtrack.data.model.dao.EpisodeDao;
import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.model.serie.Serie;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.data.repository.UserRepository;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmsrecycler.RecyclerFilmAdapter;

import java.util.ArrayList;
import java.util.List;


public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.ViewHolderSeasons> implements EpisodeAdapter.OnEpisodeListener {

    private List<Season> seasonList;
    private Activity activity;
    private Serie serie;

    public SeasonAdapter(Serie serie, FragmentActivity activity) {
        this.seasonList = new ArrayList<>();
        this.activity = activity;
        this.serie =serie;
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

        EpisodeAdapter episodeAdapter = new EpisodeAdapter(this);
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

    @Override
    public void addEpisode(Episode episode) {
        UserRepository.getInstance().addEpisode(episode);

        String content = serie.getTittle() + "\t" + ShowTrackApplication.context().getString(R.string.addEpisode_notification) + episode.getEpisodeNumber() + ".  " + episode.getTittle();

        ShowTrackApplication.newNotification(
                activity,
                R.drawable.ic_watched,
                ShowTrackApplication.context().getString(R.string.episodeAddedTitle_notification),
                content
        );
    }

    @Override
    public void deleteEpisode(Episode episode) {
        UserRepository.getInstance().removeEpisode(episode);
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
