package com.bellalhrlux.footballapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bellalhrlux.footballapp.R;
import com.bellalhrlux.footballapp.home.FinishedView;
import com.bellalhrlux.footballapp.model.Match;
import com.bellalhrlux.footballapp.model.MatchTodo;

import java.util.List;

public class FinishedMatchAdapter extends RecyclerView.Adapter<FinishedMatchAdapter.FinishedMatchViewHolder> {
    Context context;
    List<Match> matchTodoList;
    FinishedView finishedView;
    int selectedPos;

    public FinishedMatchAdapter(Context context, List<Match> matchTodoList,int selectedPos, FinishedView finishedView) {
        this.context = context;
        this.matchTodoList = matchTodoList;
        this.finishedView = finishedView;
        this.selectedPos = selectedPos;
    }

    @NonNull
    @Override
    public FinishedMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.raw_finished_match,parent,false);
        return new FinishedMatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinishedMatchViewHolder holder, final int position) {
        holder.tvLeagueName.setText(matchTodoList.get(position).getCompetition().getName());
        holder.tvHomeTeam.setText(matchTodoList.get(position).getHomeTeam().getName());
        holder.tvAwayTeam.setText(matchTodoList.get(position).getAwayTeam().getName());
        int homeTeamScore=matchTodoList.get(position).getScore().getFullTime().getHomeTeam();
        int awayTeamScore=matchTodoList.get(position).getScore().getFullTime().getAwayTeam();
        holder.tvScore.setText(homeTeamScore+" : "+awayTeamScore);

        if(selectedPos==2)
        {
            holder.eventDateLL.setVisibility(View.VISIBLE);
            holder.tvStartTime.setText("Start : "+matchTodoList.get(position).getSeason().getStartDate());
            holder.tvEndTime.setText("End : "+matchTodoList.get(position).getSeason().getEndDate());

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishedView.onItemClickedListener(matchTodoList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchTodoList.size();
    }


    class FinishedMatchViewHolder extends RecyclerView.ViewHolder{
        TextView tvLeagueName,tvHomeTeam,tvAwayTeam,tvScore,tvStartTime,tvEndTime;
        LinearLayout eventDateLL;
        public FinishedMatchViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLeagueName=itemView.findViewById(R.id.tvLeagueName);
            tvHomeTeam=itemView.findViewById(R.id.tvHomeTeam);
            tvAwayTeam=itemView.findViewById(R.id.tvAwayTeam);
            tvScore=itemView.findViewById(R.id.tvScore);
            eventDateLL=itemView.findViewById(R.id.eventDateLL);
            tvStartTime=itemView.findViewById(R.id.tvStartTime);
            tvEndTime=itemView.findViewById(R.id.tvEndTime);

        }
    }
}
