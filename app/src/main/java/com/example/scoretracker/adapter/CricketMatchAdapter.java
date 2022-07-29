package com.example.scoretracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scoretracker.R;
import com.example.scoretracker.databinding.CricketMatchItemBinding;
import com.example.scoretracker.model.CricketMatch;

import java.util.ArrayList;
import java.util.List;

public class CricketMatchAdapter extends RecyclerView.Adapter<CricketMatchAdapter.CricketMatchViewHolder> {


    class CricketMatchViewHolder extends RecyclerView.ViewHolder{

        final CricketMatchItemBinding binding;

        public CricketMatchViewHolder(CricketMatchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

    private List<CricketMatch> matches;
    private Context context;

    public CricketMatchAdapter(Context context, List<CricketMatch> matches) {
        super();
        this.context = context;
        this.matches = matches == null ? new ArrayList<>() : matches;
    }

    public void setMatches(List<CricketMatch> matches) {
        this.matches = matches;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CricketMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CricketMatchItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cricket_match_item, parent, false);
        return new CricketMatchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CricketMatchViewHolder holder, int position) {
        CricketMatch match = matches.get(position);
        holder.binding.setMatch(match);
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
