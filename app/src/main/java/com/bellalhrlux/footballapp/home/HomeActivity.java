package com.bellalhrlux.footballapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bellalhrlux.footballapp.MainActivity;
import com.bellalhrlux.footballapp.R;
import com.bellalhrlux.footballapp.Utils;
import com.bellalhrlux.footballapp.adapters.FinishedMatchAdapter;
import com.bellalhrlux.footballapp.model.Match;
import com.bellalhrlux.footballapp.team_details.TeamDetailsActivity;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements FinishedView{
    FinishedMatchPresenter finishedMatchPresenter;
    ProgressDialog progressDialog;
    RecyclerView finishedRV;
    int getIntentVal=1;
    TextView tvFinishedMatched,tvDrawMatches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finishedRV=findViewById(R.id.finishedRV);
        tvFinishedMatched=findViewById(R.id.tvFinishedMatched);
        tvDrawMatches=findViewById(R.id.tvDrawMatched);

        setListeners();

        //getIntentVal=getIntent().getExtras().getInt("option");

        setTitleVal();


        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        finishedMatchPresenter=new FinishedMatchPresenter(this,this);
        finishedMatchPresenter.getAllFinishedMatchList(getIntentVal);
        //finishedMatchPresenter.getTeamInfoList(98);

    }

    private void setTitleVal() {
        if(getIntentVal==1)
        {
            this.setTitle("Finished Matches in 2019 season");
        }
        else{
            this.setTitle("Draw Matches");
        }
    }

    private void setListeners() {
        tvFinishedMatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFinishedMatched.setBackgroundResource(R.drawable.selected_colors);
                tvDrawMatches.setBackgroundResource(R.drawable.unselected_colors);
                tvFinishedMatched.setTypeface(tvFinishedMatched.getTypeface(), Typeface.BOLD);
                tvDrawMatches.setTypeface(tvDrawMatches.getTypeface(), Typeface.NORMAL);
                getIntentVal=1;
                finishedMatchPresenter.getAllFinishedMatchList(getIntentVal);
                setTitleVal();
            }
        });
        tvDrawMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDrawMatches.setBackgroundResource(R.drawable.selected_colors);
                tvFinishedMatched.setBackgroundResource(R.drawable.unselected_colors);

                tvDrawMatches.setTypeface(tvDrawMatches.getTypeface(), Typeface.BOLD);
                tvFinishedMatched.setTypeface(tvFinishedMatched.getTypeface(), Typeface.NORMAL);

                getIntentVal=2;
                finishedMatchPresenter.getAllFinishedMatchList(getIntentVal);
                setTitleVal();
            }
        });
    }

    void reloadAdapter(List<Match> matchList)
    {
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        finishedRV.setLayoutManager(llm);
        finishedRV.setAdapter(new FinishedMatchAdapter(this,matchList,getIntentVal,this));

    }

    @Override
    public void getFinishedMatchList(List<Match> matchList) {
        //Toast.makeText(this, "Size "+matchList.size(), Toast.LENGTH_SHORT).show();
        reloadAdapter(matchList);
    }

    @Override
    public void onItemClickedListener(Match matchObj) {

        Intent intent=new Intent(HomeActivity.this, TeamDetailsActivity.class);
        //intent.putExtra("matchObj",matchObj);
        Utils.matchInfo=matchObj;
        startActivity(intent);
}

    @Override
    public void setError(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
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
