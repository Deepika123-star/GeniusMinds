package com.genius.minds.contest;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.ScorecardImageContest;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Model.ResponseAllContests.ContestListItem;
import com.genius.minds.Model.ResponseAllContests.ResponseAllContest;
import com.genius.minds.Model.ResponseGetMatchScores.ResponseGetMatchScore;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.genius.minds.Config.MyBaseUrl.LIVE_MATCH_SCORE;

public class MatchContest extends BaseActivity {

    static RecyclerView recyclerView;
    static TextView team1name, team2name, team1score, team2score, tosswinner;
    Button btnscorecard;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String teamOne,teamTwo,MatchCode,MatchSeries,categoryName,subcategoryName;
    Activity activity;
    static MatchContestAdapter matchContestAdapter;
    static List<ContestListItem> contestListItems = new ArrayList<>();
    static String  imgs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_contest);
        activity=MatchContest.this;
        team1name = findViewById(R.id.team1name);
        team2name = findViewById(R.id.team2name);
        team1score = findViewById(R.id.team1score);
        team2score = findViewById(R.id.team2score);
        tosswinner = findViewById(R.id.tosswinner);
        btnscorecard = findViewById(R.id.btnscorecard);
        recyclerView = findViewById(R.id.recylcerView);

        categoryName=getIntent().getStringExtra("category");
        subcategoryName=getIntent().getStringExtra("subcategory");
        teamOne=getIntent().getStringExtra("team_one");
        teamTwo=getIntent().getStringExtra("team_two");
        MatchCode=getIntent().getStringExtra("matchCode");
        MatchSeries=getIntent().getStringExtra("series");
//        Toast.makeText(activity, ""+MatchCode, Toast.LENGTH_SHORT).show();
        team1name.setText(teamOne);
        team2name.setText(teamTwo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
//        productList = new ArrayList<>();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override

                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                            UtilMethods.INSTANCE.matchGetAllContest(activity,categoryName,MatchCode,subcategoryName,MatchSeries);
                            UtilMethods.INSTANCE.userMatchScorePage(activity, MatchCode,"1");

//                            loadSubCategory();
                            getScoreData();

                        }else{
                            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                        }
                    }
                },3000);
            }
        });



        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.matchGetAllContest(activity,categoryName,MatchCode,subcategoryName,MatchSeries);
            UtilMethods.INSTANCE.userMatchScorePage(activity, MatchCode,"1");

            getScoreData();

        }else{
            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
        }

        initToolbar();
//        Toast.makeText(context, ""+imgs, Toast.LENGTH_SHORT).show();

        btnscorecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MatchContest.this, ScorecardImageContest.class);
                i.putExtra("img_id",imgs);
                startActivity(i);
            }
        });
    }
    private void getScoreData()
    {
        mSwipeRefreshLayout.setRefreshing(true);
        String cat=getIntent().getStringExtra("category");
        String scat=getIntent().getStringExtra("subcategory");
        String matchscore=getIntent().getStringExtra("matchcode");
        Log.d("Scoreurl",LIVE_MATCH_SCORE+cat+"&scat="+scat+"&matchcode="+matchscore);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LIVE_MATCH_SCORE+cat+"&scat="+scat+"&matchcode="+matchscore,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        showData(response);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }
    private void showData(JSONArray jsonArray){
        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
                // final String getversion=obj.getString("version");
                Log.d("First Team name",obj.getString("team1"));

                team1name.setText(" "+obj.getString("team1"));
                team2name.setText(" "+obj.getString("team2"));
                team1score.setText(" "+obj.getString("fteamscore"));
                team2score.setText(" "+obj.getString("steamscore"));
                tosswinner.setText(" "+obj.getString("batting"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

//    private void loadContests() {
//        mSwipeRefreshLayout.setRefreshing(true);
//        String category=getIntent().getStringExtra("category");
//        String subcat=getIntent().getStringExtra("subcategory");
//        String matchcode=getIntent().getStringExtra("matchcode");
//        String series=getIntent().getStringExtra("series");
//        Log.d(TAG, "matchcode: "+matchcode);
//        Log.d(TAG, "series: "+series);
//        Log.d(TAG, "matchurl: "+CONTEST_LIST+category+"&subcat="+subcat+"&series"+series+"&matchcode"+matchcode);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, CONTEST_LIST+category+"&subcat="+subcat+"&series="+series+"&matchcode="+matchcode,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//
//                            JSONArray array = new JSONArray(response);
//
//                            for (int i = 0; i < array.length(); i++) {
//
//                                JSONObject contest = array.getJSONObject(i);
//                                productList.add(new MatchContestModel(
//                                        contest.getInt("id"),
//                                        contest.getString("contest_name"),
//                                        contest.getString("fteamfee"),
//                                        contest.getString("fteamwin"),
//                                        contest.getString("steamfee"),
//                                        contest.getString("steamwin"),
//                                        contest.getString("fteambonus"),
//                                        contest.getString("steambonus"),
//                                        contest.getString("category"),
//                                        contest.getString("matchname"),
//                                        contest.getString("subcat"),
//                                        contest.getString("team1"),
//                                        contest.getString("team2"),
//                                        contest.getString("date"),
//                                        contest.getString("starttime"),
//                                        contest.getString("endtime"),
//                                        contest.getInt("spot"),
//                                        contest.getString("result"),
//                                        contest.getString("fuser"),
//                                        contest.getString("suser"),
//                                        contest.getInt("fjoin"),
//                                        contest.getInt("sjoin")
//                                ));
//                            }
//                            //creating adapter object and setting it to recyclerview
//                            MatchContestAdapter adapter = new MatchContestAdapter(MatchContest.this, productList);
//                            recyclerView.setAdapter(adapter);
//                            //spinner.setVisibility(View.GONE);
//                            adapter.notifyDataSetChanged();
//                            mSwipeRefreshLayout.setRefreshing(false);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //mSwipeRefreshLayout.setRefreshing(false);
//                    }
//                });
//        Volley.newRequestQueue(this).add(stringRequest);
//    }
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Contest Lists");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public static void matchGetAllContest(Context context, String response) {
        Type type = new TypeToken<ResponseAllContest>() {}.getType();
        ResponseAllContest responseDashBoardList = new Gson().fromJson(response, type);

        contestListItems=responseDashBoardList.getGeniusbetting().getContestList();
        if (contestListItems.size() > 0) {
            matchContestAdapter = new MatchContestAdapter(contestListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(horizontalLayoutManagaer1);
            recyclerView.setAdapter(matchContestAdapter);

        }

    }



    public static void getRefferalhistoryScoreCard(Context context, String response) {
        Type type = new TypeToken<ResponseGetMatchScore>() {}.getType();
        ResponseGetMatchScore responseGetMatchScore = new Gson().fromJson(response, type);

        team1score.setText(responseGetMatchScore.getGeniusbetting().getFteamscore());
        team2score.setText(responseGetMatchScore.getGeniusbetting().getSteamscore());
        imgs=responseGetMatchScore.getGeniusbetting().getScoreimage();


    }


}