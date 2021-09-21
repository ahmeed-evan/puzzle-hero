package com.innovertech.puzzlehero.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.innovertech.puzzlehero.R;
import com.innovertech.puzzlehero.model.Leaderboard.Leaders;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder> {

    private final Context context;
    private final List<Leaders> leadersList;

    public LeaderboardAdapter(Context context, List<Leaders> leadersList) {
        this.context = context;
        this.leadersList = leadersList;
    }

    @NonNull
    @NotNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new LeaderboardViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_leaderboard, null));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LeaderboardViewHolder holder, int position) {
        Leaders leader = leadersList.get(position);
        holder.rankTV.setText(String.valueOf(leader.rank));
        holder.rankerName.setText(leader.name);

    }

    @Override
    public int getItemCount() {
        return leadersList.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rankTV)
        TextView rankTV;
        @BindView(R.id.rankerName)
        TextView rankerName;

        public LeaderboardViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
