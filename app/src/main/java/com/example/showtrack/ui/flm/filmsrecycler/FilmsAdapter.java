package com.example.showtrack.ui.flm.filmsrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;

import java.util.ArrayList;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolderFilms> {
    private ArrayList<Film> filmsList;
    private OnFilmsListener listener;

    public interface OnFilmsListener{
        void onVisitFilm(Film film);
        void onChangeFilm(Film film);
    }
    
    public FilmsAdapter(String genre, FilmsAdapter.OnFilmsListener listener) {
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
        //holder.clBackgroundFilmView.setBackground(this.FilmsList.get(position).getImage());
        holder.tvNameFilmView.setText(this.filmsList.get(position).getName());
        holder.tvYearFilmView.setText(Integer.toString(this.filmsList.get(position).getYear()));

        holder.bind(this.filmsList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return this.filmsList.size();
    }


    public class ViewHolderFilms extends RecyclerView.ViewHolder{

        ConstraintLayout clBackgroundFilmView;
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

    public void update(ArrayList<Film> FilmsList) {
        this.filmsList.clear();
        this.filmsList.addAll(FilmsList);

        notifyDataSetChanged();
    }
}