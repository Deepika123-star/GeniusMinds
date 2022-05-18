package com.genius.minds.mycontest.live;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.genius.minds.Config.MyBaseUrl.LIVE_MATCH_LIST;
import static com.genius.minds.Config.MyBaseUrl.MATCH_LIST;

public class LiveMatches extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener  {

    private ProgressBar spinner;
    private static final String TAG = "Genius";
    List<LiveMatchModel> productList;
    RecyclerView recyclerView;
    SessionManager session;
    SwipeRefreshLayout mSwipeRefreshLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_matches);
        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this);
        recyclerView = findViewById(R.id.recylcerView);
//        String category=getIntent().getStringExtra("category");
//        Toast.makeText(this, ""+category, Toast.LENGTH_SHORT).show();
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
        User user = SharedPrefManager.getInstance(this).getUser();
        final String emailuser = user.getEmail();
        String category=getIntent().getStringExtra("category");

        Log.d(TAG, "matchurl: "+MATCH_LIST+category);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LIVE_MATCH_LIST+category+"&email="+emailuser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject contest = array.getJSONObject(i);
                                productList.add(new LiveMatchModel(
                                        contest.getInt("id"),
                                        contest.getString("category"),
                                        contest.getString("series"),
                                        contest.getString("matchcode"),
                                        contest.getString("team1"),
                                        contest.getString("team2"),
                                        contest.getString("f_logo"),
                                        contest.getString("s_logo"),
                                        contest.getString("mdate"),
                                        contest.getString("mtime")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            LiveMatchAdapter adapter = new LiveMatchAdapter(LiveMatches.this, productList);
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
        getSupportActionBar().setTitle("Live Match List");
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