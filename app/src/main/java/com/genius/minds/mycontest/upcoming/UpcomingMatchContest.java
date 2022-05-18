package com.genius.minds.mycontest.upcoming;

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

import static com.genius.minds.Config.MyBaseUrl.UPCOMING_CONTEST;

public class UpcomingMatchContest extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener  {

    private static final String TAG ="Genius" ;
    List<UpcomingModel> productList;
    RecyclerView recyclerView;
    private ProgressBar spinner;
    SessionManager session;
    SwipeRefreshLayout mSwipeRefreshLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_match_contest);
        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this);

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
                // productList.clear();
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

    }
    @Override
    public void onRefresh() {
        // Fetching data from server
        productList.clear();
        loadCategory();
    }
    private void loadCategory() {
        mSwipeRefreshLayout.setRefreshing(true);
        User user = SharedPrefManager.getInstance(this).getUser();
        String category=getIntent().getStringExtra("category");
        String subcat=getIntent().getStringExtra("subcategory");
        String matchcode=getIntent().getStringExtra("matchcode");
        String series=getIntent().getStringExtra("series");
        Log.d(TAG, "matchcode: "+matchcode);
        Log.d(TAG, "series: "+series);

        final String emailuser = user.getEmail();
        Log.d("Finalurl",UPCOMING_CONTEST+emailuser+"&category="+category+"&subcat="+subcat+"&series="+series+"&matchcode="+matchcode);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, UPCOMING_CONTEST+emailuser+"&category="+category+"&subcat="+subcat+"&series="+series+"&matchcode="+matchcode,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject contest = array.getJSONObject(i);
                                productList.add(new UpcomingModel(
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
                                       // contest.getInt("spot")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            UpcomingAdapter adapter = new UpcomingAdapter(UpcomingMatchContest.this, productList);
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
        getSupportActionBar().setTitle("Upcoming Match Contest");
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