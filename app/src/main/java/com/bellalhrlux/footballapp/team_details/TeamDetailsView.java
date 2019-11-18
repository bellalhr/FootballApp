package com.bellalhrlux.footballapp.team_details;

import com.bellalhrlux.footballapp.model.team_info.Squad;

import java.util.List;

public interface TeamDetailsView {
    void getTeamInfoList(List<Squad> squadList);
    void setError(String msg);
    void showDialog();
    void hideDialog();
}
