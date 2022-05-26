package com.example.showtrack.ui.flm.filmsrecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showtrack.R;
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.ui.ShowTrackApplication;

import java.util.ArrayList;

public class RecyclerFilmAdapter extends RecyclerView.Adapter<RecyclerFilmAdapter.ViewHolderFilms> implements FilmsAdapter.OnFilmsListener{
    private ArrayList<RecyclerFilm> recyclersList;
    private RecyclerFilmsFragment fragment;
    private OnRecyclerFilmListener listener;

    public interface OnRecyclerFilmListener {
        void onVisitGenre(String genre);
    }

    public RecyclerFilmAdapter(RecyclerFilmsFragment fragment, OnRecyclerFilmListener listener) {
        this.recyclersList = new ArrayList<>();
        this.fragment = fragment;
        this.listener =listener;
    }

    @NonNull
    @Override
    public RecyclerFilmAdapter.ViewHolderFilms onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_film_view, parent, false);
        return new RecyclerFilmAdapter.ViewHolderFilms(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerFilmAdapter.ViewHolderFilms holder, int position) {

        RecyclerFilm recyclerFilm = this.recyclersList.get(position);

        holder.tvTittleFilmsRv.setText(recyclerFilm.getTittle());


        FilmsAdapter adapter = new FilmsAdapter(recyclerFilm.getGenre(), this );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowTrackApplication.context(), RecyclerView.HORIZONTAL, false);

        holder.nestedRvFilms.setLayoutManager(layoutManager);
        holder.nestedRvFilms.setAdapter(adapter);



        adapter.update(FilmRepository.getInstance().cargarFilms(recyclerFilm.getGenre()));

        holder.btnVisitGenre.setOnClickListener(v -> {
            listener.onVisitGenre(this.recyclersList.get(position).getGenre());
        });
    }

    @Override
    public int getItemCount() {
        return this.recyclersList.size();
    }


    public class ViewHolderFilms extends RecyclerView.ViewHolder{

        TextView tvTittleFilmsRv;
        RecyclerView nestedRvFilms;
        ImageView btnVisitGenre;


        public ViewHolderFilms(@NonNull View itemView) {
            super(itemView);

            tvTittleFilmsRv = itemView.findViewById(R.id.tvTittleFilmsRv);
            nestedRvFilms = itemView.findViewById(R.id.nestedRvFilms);
            btnVisitGenre = itemView.findViewById(R.id.btnVisitGenre);

        }
    }

    public void update(ArrayList<RecyclerFilm> recyclersList) {
        this.recyclersList.clear();
        this.recyclersList.addAll(recyclersList);

        notifyDataSetChanged();
    }


    @Override
    public void onVisitFilm(Film film) {
        /*
        NO SE HIZO CON FRAGMENT DIRECTIONS POR PROBELMAS BOTENIENDO FRAGMENT
        RecyclerFilmsFragmentDirections.ActionFilmsFragmentToFilmItemFragment actionFilmsFragmentToFilmItemFragment
                = RecyclerFilmsFragmentDirections.actionFilmsFragmentToFilmItemFragment(film);

        NavHostFragment.findNavController().navigate(actionFilmsFragmentToFilmItemFragment);*/

        ShowTrackApplication.setFilmTemp(film);
        NavHostFragment.findNavController(fragment).navigate(R.id.filmItemFragment);
    }

    @Override
    public void onChangeFilm(Film film) {

    }


}

