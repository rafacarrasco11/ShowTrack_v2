package com.example.showtrack.ui.prf.profile.prof;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showtrack.R;
import com.example.showtrack.data.model.user.Stat;

import java.util.ArrayList;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.ViewHolderStats> {

    private final ArrayList<Stat> statArrayList;

    public StatAdapter() {
        this.statArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public StatAdapter.ViewHolderStats onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stat_view, parent, false);
        return new StatAdapter.ViewHolderStats(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderStats holder, int position) {
        holder.tvStatStatView.setText(String.valueOf(this.statArrayList.get(position).getStat()));
        holder.tvTittleStatView.setText(this.statArrayList.get(position).getTittle());
    }

    @Override
    public int getItemCount() {
        return this.statArrayList.size();
    }

    public class ViewHolderStats extends RecyclerView.ViewHolder{

        TextView tvStatStatView;
        TextView tvTittleStatView;

        public ViewHolderStats(@NonNull View itemView) {
            super(itemView);

            tvStatStatView = itemView.findViewById(R.id.tvStatStatView);
            tvTittleStatView = itemView.findViewById(R.id.tvTittleStatView);
        }
    }

    public void update(ArrayList<Stat> statList) {
        this.statArrayList.clear();
        this.statArrayList.addAll(statList);

        notifyDataSetChanged();
    }
}