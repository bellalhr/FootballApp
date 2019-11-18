package com.bellalhrlux.footballapp.home;

import android.content.Context;
import android.widget.Toast;

import com.bellalhrlux.footballapp.Utils;
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

public class FinishedMatchPresenter {
    FinishedView finishedView;
    Context context;
    ApiInterface apiInterface;

    public FinishedMatchPresenter(FinishedView finishedView, Context context) {
        this.finishedView = finishedView;
        this.context = context;
        if(apiInterface==null)
        {
            apiInterface= ApiClient.getClient().create(ApiInterface.class);
        }
    }

    public void getAllFinishedMatchList(final int selectedOption)
    {
        finishedView.showDialog();
        apiInterface.getFinishedMatchListApi(Utils.API_KEY)
                .enqueue(new Callback<MatchTodo>() {
                    @Override
                    public void onResponse(Call<MatchTodo> call, Response<MatchTodo> response) {
                        if(response.isSuccessful())
                        {
                            if(selectedOption==2)
                            {
                                finishedView.getFinishedMatchList(getOnlyDrawTheMatches(response.body().getMatches()));
                            }
                            else {
                                finishedView.getFinishedMatchList(response.body().getMatches());
                            }
                            //Log.e("sdfsdfdsf",response.body().getCount()+"");

                        }
                        else{
                            finishedView.setError(response.message());
                            //Log.e("sdfsdfdsf","error one");
                        }
                        finishedView.hideDialog();
                    }

                    @Override
                    public void onFailure(Call<MatchTodo> call, Throwable t) {
                        finishedView.setError(t.getMessage());
                        finishedView.hideDialog();

                        //Log.e("sdfsdfdsf","error two");
                    }
                });
    }

    private List<Match>  getOnlyDrawTheMatches(List<Match> matches) {
        List<Match> matchList=new ArrayList<>();
        for (int i=0;i<matches.size();i++)
        {
            if(matches.get(i).getScore().getWinner().equals("DRAW"))
            {
                matchList.add(matches.get(i));
            }
        }

        return matchList;
    }

    public void getTeamInfoList(int teamId)
    {
        apiInterface.getTeamInfoApi(Utils.API_KEY,teamId)
                .enqueue(new Callback<TeamInfoTodo>() {
                    @Override
                    public void onResponse(Call<TeamInfoTodo> call, Response<TeamInfoTodo> response) {
                        if(response.isSuccessful())
                        {
                            Toast.makeText(context, ""+response.body().getSquad().size(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TeamInfoTodo> call, Throwable t) {
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
