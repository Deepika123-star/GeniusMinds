package com.genius.minds.mycontest.complete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.R;
import com.genius.minds.utills.ScorecardImage;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.genius.minds.Config.MyBaseUrl.COMPLETE_CONTEST;
import static com.genius.minds.Config.MyBaseUrl.LIVE_MATCH_SCORE;

public class CompleteContest extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener  {

    List<CompleteContestModel> productList;
    RecyclerView recyclerView;
    private ProgressBar spinner;
    SessionManager session;
    TextView team1name,team2name,team1score,team2score,tosswinner;
    Button btnscorecard;
    SwipeRefreshLayout mSwipeRefreshLayout ;
            String categorys,subcategorys,matchcodes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_contest);
        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this);


        categorys=getIntent().getStringExtra("category");
        subcategorys=getIntent().getStringExtra("subcategory");
        matchcodes=getIntent().getStringExtra("matchcode");
        team1name=findViewById(R.id.team1name);
        team2name=findViewById(R.id.team2name);
        team1score=findViewById(R.id.team1score);
        team2score=findViewById(R.id.team2score);
        tosswinner=findViewById(R.id.tosswinner);
        btnscorecard=findViewById(R.id.btnscorecard);
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        mSwipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        productList = new ArrayList<>();
        //spinner.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                productList.clear();
                getScoreData();
                loadCategory();
            }
        });
        Thread timer = new Thread() { //new thread
            public void run() {
                boolean b = true;
                try {
                    do {
                        sleep(60000);
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                productList.clear();
                                getScoreData();
                                loadCategory();
                            }
                        });
                    }
                    while (b == true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                }
            };
        };
        timer.start();
        initToolbar();
        btnscorecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CompleteContest.this, ScorecardImage.class);
//                i.putExtra("category",getIntent().getStringExtra("category"));
//                i.putExtra("subcat",getIntent().getStringExtra("subcategory"));
//                i.putExtra("matchcode",getIntent().getStringExtra("matchcode"));
                  i.putExtra("category_new",categorys);
                  i.putExtra("SubCategory_new",subcategorys);
                  i.putExtra("matchcodes_new",matchcodes);



                startActivity(i);
            }
        });

    }
    @Override
    public void onRefresh() {
        // Fetching data from server
        productList.clear();
        getScoreData();
        loadCategory();
    }
    private void getScoreData()
    {

        String cat=getIntent().getStringExtra("category");
        String scat=getIntent().getStringExtra("subcategory");
        String matchscore=getIntent().getStringExtra("matchcode");
        Log.d("Scoreurl",LIVE_MATCH_SCORE+cat+"&scat="+scat+"&matchcode="+matchscore);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LIVE_MATCH_SCORE+cat+"&scat="+scat+"&matchcode="+matchscore,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        showData(response);
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
    private void loadCategory() {
        mSwipeRefreshLayout.setRefreshing(true);
        User user = SharedPrefManager.getInstance(this).getUser();
        String category=getIntent().getStringExtra("category");
        String subcat=getIntent().getStringExtra("subcategory");
        String matchcode=getIntent().getStringExtra("matchcode");
        String series=getIntent().getStringExtra("series");
        final String emailuser = user.getEmail();
        Log.d("Finalurl",COMPLETE_CONTEST+emailuser+"&category="+category+"&subcat="+subcat);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, COMPLETE_CONTEST+emailuser+"&category="+category+"&subcat="+subcat+"&series="+series+"&matchcode="+matchcode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject contest = array.getJSONObject(i);
                                productList.add(new CompleteContestModel(
                                        contest.getInt("id"),
                                        contest.getInt("contestid"),
                                        contest.getString("useremail"),
                                        contest.getString("selectedteam"),
                                        contest.getString("contest_name"),
                                        contest.getString("fteamfee"),
                                        contest.getString("fteamwin"),
                                        contest.getString("steamfee"),
                                        contest.getString("steamwin"),
                                        contest.getString("fteambonus"),
                                        contest.getString("steambonus"),
                                        contest.getString("category"),
                                        contest.getString("matchname"),
                                        contest.getString("subcat"),
                                        contest.getString("team1"),
                                        contest.getString("team2"),
                                        contest.getString("date"),
                                        contest.getString("starttime"),
                                        contest.getString("endtime"),
                                        contest.getString("result"),
                                        contest.getString("status"),
                                        contest.getString("fuser"),
                                        contest.getString("suser"),
                                        contest.getString("totalPriceMoney")


                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            CompleteContestAdapter adapter = new CompleteContestAdapter(CompleteContest.this, productList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            mSwipeRefreshLayout.setRefreshing(false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Complete Match Contest");
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
}