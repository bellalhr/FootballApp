package com.bellalhrlux.footballapp.home;

import com.bellalhrlux.footballapp.model.Match;

import java.util.List;

public interface FinishedView {
    void getFinishedMatchList(List<Match> matchList);
    void onItemClickedListener(Match matchObj);
    void setError(String msg);
    void showDialog();
    void hideDialog();
}
