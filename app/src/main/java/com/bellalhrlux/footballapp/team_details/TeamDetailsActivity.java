package com.bellalhrlux.footballapp.team_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bellalhrlux.footballapp.R;
import com.bellalhrlux.footballapp.Utils;
import com.bellalhrlux.footballapp.adapters.FinishedMatchAdapter;
import com.bellalhrlux.footballapp.adapters.PlayerInfoAdapter;
import com.bellalhrlux.footballapp.model.Match;
import com.bellalhrlux.footballapp.model.team_info.Squad;

import java.util.List;

public class TeamDetailsActivity extends AppCompatActivity implements TeamDetailsView{
    TextView tvTeamOne,tvTeamTwo;
    TextView tvLeagueName,tvHomeTeam,tvAwayTeam,tvScore;
    RecyclerView playerInfoRV;
    Match matchObj;
    ProgressDialog progressDialog;
    TeamDetailsPresenter teamDetailsPresenter;
    int teamId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        this.setTitle("Team Details");
        tvTeamOne=findViewById(R.id.tvTeamOne);
        tvTeamTwo=findViewById(R.id.tvTeamTwo);

        tvLeagueName=findViewById(R.id.tvLeagueName);
        tvHomeTeam=findViewById(R.id.tvHomeTeam);
        tvAwayTeam=findViewById(R.id.tvAwayTeam);
        tvScore=findViewById(R.id.tvScore);
        playerInfoRV=findViewById(R.id.playerInfoRV);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        matchObj= Utils.matchInfo;

        teamId=matchObj.getHomeTeam().getId();

        teamDetailsPresenter=new TeamDetailsPresenter(this,this);
        teamDetailsPresenter.getTeamInfoList(teamId);



        setLintener();

        setItems();
    }

    void reloadAdapter(List<Squad> matchList)
    {
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        playerInfoRV.setLayoutManager(llm);
        playerInfoRV.setAdapter(new PlayerInfoAdapter(this,matchList));

    }

    private void setItems() {
        tvLeagueName.setText(matchObj.getCompetition().getName());
        tvHomeTeam.setText(matchObj.getHomeTeam().getName());
        tvAwayTeam.setText(matchObj.getAwayTeam().getName());

        int homeTeamScore=matchObj.getScore().getFullTime().getHomeTeam();
        int awayTeamScore=matchObj.getScore().getFullTime().getAwayTeam();

        tvScore.setText(homeTeamScore+" : "+awayTeamScore);
    }

    private void setLintener() {
        tvTeamOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTeamOne.setBackgroundColor(Color.parseColor("#01B7AD"));
                tvTeamTwo.setBackgroundColor(Color.parseColor("#FFFFFF"));

                tvTeamOne.setTextColor(Color.parseColor("#FFFFFF"));
                tvTeamTwo.setTextColor(Color.parseColor("#000000"));

                teamId=matchObj.getHomeTeam().getId();
                teamDetailsPresenter.getTeamInfoList(teamId);
            }
        });
        tvTeamTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTeamTwo.setBackgroundColor(Color.parseColor("#01B7AD"));
                tvTeamOne.setBackgroundColor(Color.parseColor("#FFFFFF"));

                tvTeamTwo.setTextColor(Color.parseColor("#FFFFFF"));
                tvTeamOne.setTextColor(Color.parseColor("#000000"));

                teamId=matchObj.getAwayTeam().getId();
                teamDetailsPresenter.getTeamInfoList(teamId);
            }
        });
    }

    @Override
    public void getTeamInfoList(List<Squad> squadList) {
        reloadAdapter(squadList);
    }

    @Override
    public void setError(String msg) {

    }

    @Override
    public void showDialog() {
        progressDialog.show();
    }

    @Override
    public void hideDialog() {
        progressDialog.hide();
    }
}
