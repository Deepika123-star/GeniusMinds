package com.genius.minds.matches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import static com.genius.minds.Config.MyBaseUrl.MATCH_LIST;

public class Matches extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ProgressBar spinner;
    private static final String TAG = "Genius";
    List<MatchModel> productList;
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        recyclerView = findViewById(R.id.recylcerView);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
               // productList.clear();
                loadMatches();
            }
        });
        initToolbar();
    }
    @Override
    public void onRefresh() {
        // Fetching data from server
        productList.clear();
        loadMatches();
    }
    private void loadMatches() {
        mSwipeRefreshLayout.setRefreshing(true);
        String category=getIntent().getStringExtra("category");
        Log.d(TAG, "matchurl: "+MATCH_LIST+category);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, MATCH_LIST+category,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject contest = array.getJSONObject(i);
                                productList.add(new MatchModel(
                                        contest.getInt("id"),
                                        contest.getString("category"),
                                        contest.getString("series"),
                                        contest.getString("matchcode"),
                                        contest.getString("team1"),
                                        contest.getString("team2"),
                                        contest.getString("f_logo"),
                                        contest.getString("s_logo"),
                                        contest.getString("mdate"),
                                        contest.getString("mtime"),
                                        contest.getInt("subcatdisstatus")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            MatchAdapter adapter = new MatchAdapter(Matches.this, productList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            //spinner.setVisibility(View.GONE);
                            mSwipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mSwipeRefreshLayout.setRefreshing(false);
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
        getSupportActionBar().setTitle("Match List");
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