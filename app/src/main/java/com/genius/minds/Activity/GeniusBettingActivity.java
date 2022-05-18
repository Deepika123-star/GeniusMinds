package com.genius.minds.Activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.R;
import com.genius.minds.mycontest.live.Adapter.AdapterLive;
import com.genius.minds.mycontest.live.Adapter.ModelLive.LiveModel;
import com.genius.minds.mycontest.live.LiveCategoryModel;

import java.util.ArrayList;
import java.util.List;

public class GeniusBettingActivity extends BaseActivity {
    RecyclerView recyclerView,rec_live;
    SessionManager session;
    SwipeRefreshLayout mSwipeRefreshLayout ;
    List<LiveCategoryModel> productList;

    String [] subcategory={"Cricket"};
    String [] category={"T20 WORLD CUP"};
    String [] matechDateTime={"India Vs Pakistan 1/29/2022"};
    String [] firstTeam={"INDIA"};
    String [] SecondTeam={"PAKISTAN"};
    String [] contestnaem={"Cricket"};
    String [] date={"1/29/2022"};
    String [] fullScore={"VIEW FULL SCORECARD"};
    AdapterLive adapterLive;
    ArrayList<LiveModel> liveModels;
    ImageView iv_backLive;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genius_betting);
        activity= GeniusBettingActivity.this;
        rec_live = findViewById(R.id.rec_live);
        iv_backLive = findViewById(R.id.iv_backLive);
        iv_backLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RecyclerView.LayoutManager vertical = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
        rec_live.setLayoutManager(vertical);
        rec_live.setItemAnimator(new DefaultItemAnimator());
        liveModels = new ArrayList<>();
        for (int i = 0; i < category.length; i++) {
            LiveModel ab = new LiveModel(category[i],subcategory[i],matechDateTime[i],firstTeam[i],SecondTeam[i],contestnaem[i],date[i],fullScore[i]);
            liveModels.add(ab);
        }
        adapterLive = new AdapterLive(liveModels,activity);
        rec_live.setAdapter(adapterLive);


    }
}