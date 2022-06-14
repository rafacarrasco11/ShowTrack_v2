package com.example.showtrack.ui.flm.filmsrecycler;

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
import com.example.showtrack.data.model.Film;
import com.example.showtrack.data.model.recycler.RecyclerFilm;
import com.example.showtrack.data.model.serie.Season;
import com.example.showtrack.data.repository.FilmRepository;
import com.example.showtrack.ui.ShowTrackApplication;
import com.example.showtrack.ui.flm.filmgenre.FilmGenreFragment;
import com.example.showtrack.ui.srs.serieitem.SeasonAdapter;

import java.util.ArrayList;

/**
 * Adapter para la clase de listas de peliculas que aparecen en la pantalla peliculas.
 */
public class RecyclerFilmAdapter extends RecyclerView.Adapter<RecyclerFilmAdapter.ViewHolderFilms> implements FilmsAdapter.OnFilmsListener{
    private ArrayList<RecyclerFilm> recyclersList;
    private OnRecyclerFilmListener listener;

    public interface OnRecyclerFilmListener {
        void onVisitGenre(RecyclerFilm recyclerFilm, int numberGenre);

        void onVisitFilm(Film film);
    }

    public RecyclerFilmAdapter(OnRecyclerFilmListener listener) {
        this.recyclersList = new ArrayList<>();
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


        FilmsAdapter adapter = new FilmsAdapter( this );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShowTrackApplication.context(), RecyclerView.HORIZONTAL, false);

        holder.nestedRvFilms.setLayoutManager(layoutManager);
        holder.nestedRvFilms.setAdapter(adapter);


        if (recyclerFilm.getGenre() == null)
            adapter.update(FilmRepository.getInstance().cargarFilmsByList(recyclerFilm.getList(),10));
        if (recyclerFilm.getList() == null)
            adapter.update(FilmRepository.getInstance().cargarFilmsByGenre(recyclerFilm.getGenre(),10));

        holder.llTittleFilmsRv.setOnClickListener(v -> {
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


    public class ViewHolderFilms extends RecyclerView.ViewHolder{

        TextView tvTittleFilmsRv;
        RecyclerView nestedRvFilms;
        LinearLayout llTittleFilmsRv;
        ImageView btnVisitGenre;



        public ViewHolderFilms(@NonNull View itemView) {
            super(itemView);

            tvTittleFilmsRv = itemView.findViewById(R.id.tvTittleFilmsRv);
            nestedRvFilms = itemView.findViewById(R.id.nestedRvFilms);
            llTittleFilmsRv = itemView.findViewById(R.id.llTittleFilmsRv);
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
        listener.onVisitFilm(film);
    }

    @Override
    public void onChangeFilm(Film film) {
        FilmRepository.getInstance().changeFilm(film);
    }


}

