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
import com.bellalhrlux.footballapp.model.team_info.Squad;

import java.util.List;

public class PlayerInfoAdapter extends RecyclerView.Adapter<PlayerInfoAdapter.FinishedMatchViewHolder> {
    Context context;
    List<Squad> matchTodoList;


    public PlayerInfoAdapter(Context context, List<Squad> matchTodoList) {
        this.context = context;
        this.matchTodoList = matchTodoList;

    }

    @NonNull
    @Override
    public FinishedMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.raw_player_details,parent,false);
        return new FinishedMatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FinishedMatchViewHolder holder, final int position) {
        holder.tvPlayerName.setText(matchTodoList.get(position).getName());
        if(matchTodoList.get(position).getShirtNumber()==null)
        {
            holder.tvShirtNo.setText("Shirt No : N/A");
        }
        else {
            holder.tvShirtNo.setText("Shirt No : "+matchTodoList.get(position).getShirtNumber().toString());
        }
    }

    @Override
    public int getItemCount() {
        return matchTodoList.size();
    }


    class FinishedMatchViewHolder extends RecyclerView.ViewHolder{
        TextView tvPlayerName,tvShirtNo;
        public FinishedMatchViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPlayerName=itemView.findViewById(R.id.tvPlayerName);
            tvShirtNo=itemView.findViewById(R.id.tvShirtNo);



        }
    }
}
