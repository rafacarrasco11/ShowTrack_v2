package com.example.showtrack.ui.srs.serieitem;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showtrack.data.model.serie.Episode;
import com.example.showtrack.data.model.serie.Season;

import java.util.ArrayList;
import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolderSeasons> {

    private List<Episode> episodesList;

    @NonNull
    @Override
    public ViewHolderSeasons onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSeasons holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderSeasons extends RecyclerView.ViewHolder{
        public ViewHolderSeasons(@NonNull View itemView) {
            super(itemView);
        }
    }


    public void update(ArrayList<Episode> rvList) {
        this.episodesList.clear();
        this.episodesList.addAll(rvList);

        notifyDataSetChanged();
    }
}
