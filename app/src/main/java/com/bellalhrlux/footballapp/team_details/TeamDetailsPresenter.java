package com.bellalhrlux.footballapp.team_details;

import android.content.Context;
import android.widget.Toast;

import com.bellalhrlux.footballapp.Utils;
import com.bellalhrlux.footballapp.home.FinishedView;
import com.bellalhrlux.footballapp.model.Match;
import com.bellalhrlux.footballapp.model.MatchTodo;
import com.bellalhrlux.footballapp.model.team_info.TeamInfoTodo;
import com.bellalhrlux.footballapp.networks.ApiClient;
import com.bellalhrlux.footballapp.networks.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamDetailsPresenter {
    TeamDetailsView finishedView;
    Context context;
    ApiInterface apiInterface;

    public TeamDetailsPresenter(TeamDetailsView finishedView, Context context) {
        this.finishedView = finishedView;
        this.context = context;
        if(apiInterface==null)
        {
            apiInterface= ApiClient.getClient().create(ApiInterface.class);
        }
    }


    public void getTeamInfoList(int teamId)
    {
        finishedView.showDialog();
        apiInterface.getTeamInfoApi(Utils.API_KEY,teamId)
                .enqueue(new Callback<TeamInfoTodo>() {
                    @Override
                    public void onResponse(Call<TeamInfoTodo> call, Response<TeamInfoTodo> response) {
                        if(response.isSuccessful())
                        {
                            //Toast.makeText(context, ""+response.body().getSquad().size(), Toast.LENGTH_SHORT).show();
                            finishedView.getTeamInfoList(response.body().getSquad());
                            finishedView.hideDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<TeamInfoTodo> call, Throwable t) {
                       // Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        finishedView.setError(t.getMessage());
                        finishedView.hideDialog();
                    }
                });
    }
}
