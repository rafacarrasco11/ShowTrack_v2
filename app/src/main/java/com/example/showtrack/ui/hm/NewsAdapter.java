package com.example.showtrack.ui.hm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showtrack.R;
import com.example.showtrack.data.model.New;
import com.example.showtrack.utils.DrawableUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolderNews> {

    private final ArrayList<New> newsList;
    private final NewsAdapter.OnNewsListener listener;

    public interface OnNewsListener{
        void onClickNew(New neW);
    }

    public NewsAdapter(NewsAdapter.OnNewsListener listener) {
        this.newsList = new ArrayList<>();
        this.listener = listener;
    }


    @NonNull
    @Override
    public NewsAdapter.ViewHolderNews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_view, parent, false);
        return new NewsAdapter.ViewHolderNews(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolderNews holder, int position) {
        try {
            holder.imgNewView.setImageDrawable(DrawableUtil.drawableFromUrl(this.newsList.get(position).getUrlToImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (this.newsList.get(position).getTitle().length() >= 61)
            this.newsList.get(position).setTitle(this.newsList.get(position).getTitle().substring(0, 58).concat("..."));

        holder.tvTittleNewView.setText(this.newsList.get(position).getTitle());

        if (this.newsList.get(position).getDescription().length() >= 74)
            this.newsList.get(position).setDescription(this.newsList.get(position).getDescription().substring(0, 70).concat("..."));

        holder.tvSubtittleNewView.setText(this.newsList.get(position).getDescription());

        Date dateStr = null;
        try {
        SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", new Locale("es", "ES"));
        String date = this.newsList.get(position).getPublishedAt().replaceAll("\\+0([0-9]){1}\\:00", "+0$100");
        dateStr = ISO8601DATEFORMAT.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tvReleasedNewView.setText(dateStr.toString().substring(0,16));

        holder.bind(this.newsList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return this.newsList.size();
    }


    public class ViewHolderNews extends RecyclerView.ViewHolder{

        ImageView imgNewView;
        TextView tvTittleNewView;
        TextView tvSubtittleNewView;
        TextView tvReleasedNewView;

        public ViewHolderNews(@NonNull View itemView) {
            super(itemView);

            imgNewView = itemView.findViewById(R.id.imgNewView);
            tvTittleNewView = itemView.findViewById(R.id.tvTittleNewView);
            tvSubtittleNewView = itemView.findViewById(R.id.tvSubtittleNewView);
            tvReleasedNewView = itemView.findViewById(R.id.tvReleasedNewView);
        }


        public void bind(New neW, OnNewsListener listener) {
            itemView.setOnClickListener( v -> {
                listener.onClickNew(neW);
            });
        }
    }

    public void update(ArrayList<New> newsList) {
        this.newsList.clear();
        this.newsList.addAll(newsList);

        notifyDataSetChanged();
    }
}


