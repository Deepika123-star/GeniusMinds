package com.genius.minds.Activity.LiveBetting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.genius.minds.Activity.ScorecardImageContest;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Adapter.AdapterLiveContestBetting;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Model.ResponseGetAllMatchLiveContest.ContestListItem;
import com.genius.minds.Model.ResponseGetAllMatchLiveContest.ResponseGetAllMatchLiveContests;
import com.genius.minds.Model.ResponseGetMatchScores.ResponseGetMatchScore;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LiveBettingActivity extends BaseActivity {
    static RecyclerView rec_liveBetting;
    static TextView team1name, team2name, team1score, team2score, tosswinner;
    Button btnscorecard;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String teamOne, teamTwo, MatchCode, MatchSeries, categoryName, logoOne, logoTwo, MatchTime;
    Activity activity;
    static AdapterLiveContestBetting adapterLiveContestBetting;
    static List<ContestListItem> matchListItems = new ArrayList<>();
    static String imgs;
    CircleImageView team1, team2;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_betting);
        activity = LiveBettingActivity.this;
        rec_liveBetting = findViewById(R.id.rec_liveBetting);
        this.mHandler = new Handler();
        m_Runnable.run();
        team1name = findViewById(R.id.team1name);
        team2name = findViewById(R.id.team2name);
        team1score = findViewById(R.id.team1score);
        team2score = findViewById(R.id.team2score);
        tosswinner = findViewById(R.id.tosswinner);
        btnscorecard = findViewById(R.id.btnscorecard);
        team1 = findViewById(R.id.f_logo);
        team2 = findViewById(R.id.s_logo);
        categoryName = getIntent().getStringExtra("categoryNew");
        teamOne = getIntent().getStringExtra("newteam_one");
        teamTwo = getIntent().getStringExtra("newteam_two");
        MatchCode = getIntent().getStringExtra("matchcodeone");
        MatchSeries = getIntent().getStringExtra("series1");
        logoOne = getIntent().getStringExtra("logo11");
        logoTwo = getIntent().getStringExtra("logo12");
        MatchTime = getIntent().getStringExtra("matchtime1");
        Picasso.get().load(logoOne).placeholder(R.drawable.dummy).into(team1);
        Picasso.get().load(logoTwo).placeholder(R.drawable.dummy).into(team2);

        team1name.setText(teamOne);
        team2name.setText(teamTwo);
        tosswinner.setText(MatchSeries);

        btnscorecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LiveBettingActivity.this, ScorecardImageContest.class);
                i.putExtra("img_id", imgs);
                startActivity(i);
            }
        });


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        initToolbar();
        /*tested*/
        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)) {
            UtilMethods.INSTANCE.liveAllMatchLists(activity, MatchCode, MatchSeries);
            UtilMethods.INSTANCE.userMatchScorePage(activity, MatchCode, "2");
            mSwipeRefreshLayout.setRefreshing(false);

        } else {
            UtilMethods.INSTANCE.Error(activity, getResources().getString(R.string.NOCONN));
        }
    }

    private final Runnable m_Runnable = new Runnable() {
        public void run() {
            /*tesded 2*/
            // UtilMethods.INSTANCE.liveAllMatchLists(activity,MatchCode,MatchSeries);
            //  Toast.makeText(LiveBettingActivity.this,"in runnable",Toast.LENGTH_SHORT).show();
            LiveBettingActivity.this.mHandler.postDelayed(m_Runnable, 30000);
        }

    };

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Live Betting Contest");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public static void allLiveMatchListData(Context context, String response) {
        Type type = new TypeToken<ResponseGetAllMatchLiveContests>() {
        }.getType();
        ResponseGetAllMatchLiveContests responseDashBoardList = new Gson().fromJson(response, type);

        matchListItems = responseDashBoardList.getGeniusbetting().getContestList();
        if (matchListItems.size() > 0) {
            adapterLiveContestBetting = new AdapterLiveContestBetting(matchListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rec_liveBetting.setLayoutManager(horizontalLayoutManagaer1);
            rec_liveBetting.setAdapter(adapterLiveContestBetting);

        }

    }

    public static void getRefferalhistoryScoreCards(Context context, String response) {
        Type type = new TypeToken<ResponseGetMatchScore>() {
        }.getType();
        ResponseGetMatchScore responseGetMatchScore = new Gson().fromJson(response, type);

        team1score.setText(responseGetMatchScore.getGeniusbetting().getFteamscore());
        team2score.setText(responseGetMatchScore.getGeniusbetting().getSteamscore());
        imgs = responseGetMatchScore.getGeniusbetting().getScoreimage();
    }

}