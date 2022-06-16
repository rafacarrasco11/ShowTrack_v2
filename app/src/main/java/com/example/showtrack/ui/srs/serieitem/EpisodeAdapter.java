package com.example.showtrack.ui.srs.serieitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showtrack.R;
import com.example.showtrack.data.model.api.APIClasses.APISeries;
import com.example.showtrack.data.model.database.ShowTrackDatabase;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.repository.SerieRepository;
import com.example.showtrack.data.repository.UserRepository;
import com.example.showtrack.ui.ShowTrackApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolderEpisodes> {

    private final List<Episode> episodesList;
    private final OnEpisodeListener listener;

    public interface OnEpisodeListener {
        void addEpisode(Episode episode);
        void deleteEpisode(Episode episode);
    }

    public EpisodeAdapter(OnEpisodeListener listener) {
        this.episodesList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolderEpisodes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_view, parent, false);
        return new EpisodeAdapter.ViewHolderEpisodes(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEpisodes holder, int position) {
        /*Episodio asdasd asdda...*/

        holder.tvNumberEpisode.setText(String.valueOf(position + 1));

        if (this.episodesList.get(position).getTittle().length() >= 24)
            holder.tvTittleEpisode.setText(this.episodesList.get(position).getTittle().substring(0,21).concat("..."));
        else
            holder.tvTittleEpisode.setText(this.episodesList.get(position).getTittle());

        holder.tvTittleEpisodeRating.setText(this.episodesList.get(position).getImdbRating());

        if (SerieRepository.getInstance().isEpisodeWatched(this.episodesList.get(position), ShowTrackApplication.getUserTemp())) {
            holder.imgBtnEpisodeNoWatched.setVisibility(View.GONE);
            holder.imgBtnEpisodeWatched.setVisibility(View.VISIBLE);
        }


        holder.imgBtnEpisodeNoWatched.setOnClickListener(v -> {
            holder.imgBtnEpisodeNoWatched.setVisibility(View.GONE);
            holder.imgBtnEpisodeWatched.setVisibility(View.VISIBLE);

            listener.addEpisode(this.episodesList.get(position));
        });

        holder.imgBtnEpisodeWatched.setOnClickListener(v -> {
            listener.deleteEpisode(this.episodesList.get(position));

            holder.imgBtnEpisodeNoWatched.setVisibility(View.VISIBLE);
            holder.imgBtnEpisodeWatched.setVisibility(View.GONE);
        });

        try {
            Episode episode = APISeries.getEpisodeFullInfo(this.episodesList.get(position));
            this.episodesList.set(this.episodesList.indexOf(this.episodesList.get(position)), episode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return this.episodesList.size();
    }

    public class ViewHolderEpisodes extends RecyclerView.ViewHolder{

        TextView tvTittleEpisode;
        ImageButton imgBtnEpisodeWatched;
        ImageButton imgBtnEpisodeNoWatched;
        TextView tvNumberEpisode;
        TextView tvTittleEpisodeRating;

        public ViewHolderEpisodes(@NonNull View itemView) {
            super(itemView);

            tvTittleEpisode = itemView.findViewById(R.id.tvTittleEpisode);
            imgBtnEpisodeWatched = itemView.findViewById(R.id.imgBtnEpisodeWatched);
            tvNumberEpisode = itemView.findViewById(R.id.tvNumberEpisode);
            tvTittleEpisodeRating = itemView.findViewById(R.id.tvTittleEpisodeRating);
            imgBtnEpisodeNoWatched = itemView.findViewById(R.id.imgBtnEpisodeNoWatched);

        }
    }


    public void update(ArrayList<Episode> rvList) {
        this.episodesList.clear();
        this.episodesList.addAll(rvList);

        notifyDataSetChanged();
    }
}
