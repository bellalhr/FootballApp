package com.bellalhrlux.footballapp.networks;

import com.bellalhrlux.footballapp.model.MatchTodo;
import com.bellalhrlux.footballapp.model.team_info.TeamInfoTodo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("players/2019/matches?status=FINISHED")
    Call<MatchTodo> getFinishedMatchListApi(
            @Header("X-Auth-Token") String authKey
    );
    @GET("teams/{team_id}")
    Call<TeamInfoTodo> getTeamInfoApi(
            @Header("X-Auth-Token") String authKey,
            @Path("team_id") int teamId
    );

}
