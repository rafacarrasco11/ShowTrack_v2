package com.example.showtrack.ui.flm.filmsrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.api.APIClasses.APIFilms;
import com.example.showtrack.utils.DrawableUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Adapter para las peliculas, que son los items que hay en las listas de peliculas (Nested Recycler View).
 */
public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolderFilms> {
    private final ArrayList<Film> filmsList;
    private final OnFilmsListener listener;

    public interface OnFilmsListener{
        void onVisitFilm(Film film);
        void onChangeFilm(Film film);
    }
    
    public FilmsAdapter(FilmsAdapter.OnFilmsListener listener) {
        this.filmsList = new ArrayList<>();
        this.listener = listener;
    }

    
    @NonNull
    @Override
    public FilmsAdapter.ViewHolderFilms onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_view, parent, false);
        return new FilmsAdapter.ViewHolderFilms(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsAdapter.ViewHolderFilms holder, int position) {
        try {
            holder.clBackgroundFilmView.setBackground(DrawableUtil.drawableFromUrl(APIFilms.getNewBackground(this.filmsList.get(position))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (this.filmsList.get(position).getTittle().length() >= 21)
            holder.tvNameFilmView.setText(this.filmsList.get(position).getTittle().substring(0,18).concat("..."));
        else holder.tvNameFilmView.setText(this.filmsList.get(position).getTittle());

        holder.tvYearFilmView.setText(this.filmsList.get(position).getYearReleased());

        holder.bind(this.filmsList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return this.filmsList.size();
    }


    public class ViewHolderFilms extends RecyclerView.ViewHolder{

        LinearLayout clBackgroundFilmView;
        TextView tvNameFilmView;
        TextView tvYearFilmView;

        public ViewHolderFilms(@NonNull View itemView) {
            super(itemView);

            clBackgroundFilmView = itemView.findViewById(R.id.clBackgroundFilmView);
            tvNameFilmView = itemView.findViewById(R.id.tvNameFilmView);
            tvYearFilmView = itemView.findViewById(R.id.tvYearFilmView);
        }


        public void bind(Film film, OnFilmsListener listener) {
            itemView.setOnClickListener( v -> {
                listener.onVisitFilm(film);
            });

            itemView.setOnLongClickListener( v -> {
                listener.onChangeFilm(film);
                return true;
            });
        }
    }

    public void update(ArrayList<Film> filmsList) {
        this.filmsList.clear();
        this.filmsList.addAll(filmsList);

        notifyDataSetChanged();
    }
}
