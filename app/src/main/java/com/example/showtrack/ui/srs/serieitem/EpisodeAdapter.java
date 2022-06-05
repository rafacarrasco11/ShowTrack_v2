package com.example.showtrack.ui.srs.serieitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showtrack.R;
import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Season;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolderEpisodes> {

    private List<Episode> episodesList;

    public EpisodeAdapter() {
        this.episodesList = new ArrayList<>();
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

        if (this.episodesList.get(position).getTitle().length() >= 24)
            holder.tvTittleEpisode.setText(this.episodesList.get(position).getTitle().substring(0,21).concat("..."));
        else
            holder.tvTittleEpisode.setText(this.episodesList.get(position).getTitle());

        holder.tvTittleEpisodeRating.setText(this.episodesList.get(position).getImdbRating());
    }

    @Override
    public int getItemCount() {
        return this.episodesList.size();
    }

    public class ViewHolderEpisodes extends RecyclerView.ViewHolder{

        TextView tvTittleEpisode;
        ImageButton imgBtnEpisodeWatched;
        TextView tvNumberEpisode;
        TextView tvTittleEpisodeRating;

        public ViewHolderEpisodes(@NonNull View itemView) {
            super(itemView);

            tvTittleEpisode = itemView.findViewById(R.id.tvTittleEpisode);
            imgBtnEpisodeWatched = itemView.findViewById(R.id.imgBtnEpisodeWatched);
            tvNumberEpisode = itemView.findViewById(R.id.tvNumberEpisode);
            tvTittleEpisodeRating = itemView.findViewById(R.id.tvTittleEpisodeRating);

        }

    }


    public void update(ArrayList<Episode> rvList) {
        this.episodesList.clear();
        this.episodesList.addAll(rvList);

        notifyDataSetChanged();
    }
}
