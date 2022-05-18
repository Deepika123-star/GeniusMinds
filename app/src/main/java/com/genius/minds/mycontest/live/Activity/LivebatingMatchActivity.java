package com.genius.minds.mycontest.live.Activity;

import static com.genius.minds.Config.MyBaseUrl.LIST_LIVE_BATING_MATCH;
import static com.genius.minds.Config.MyBaseUrl.LIVE_MATCH_LIST;
import static com.genius.minds.Config.MyBaseUrl.MATCH_LIST;
import static com.genius.minds.Config.MyBaseUrl.UPCOMING_MATCH_LIST_LIVE_BATING;

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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.R;
import com.genius.minds.mycontest.live.Adapter.LivebatingMatchAdapter;
import com.genius.minds.mycontest.live.LiveMatchAdapter;
import com.genius.minds.mycontest.live.LiveMatchModel;
import com.genius.minds.mycontest.live.LiveMatches;
import com.genius.minds.mycontest.upcoming.Model.LivebatingMatchModel;
import com.genius.minds.mycontest.upcoming.livebating.UpcommingLivebatingAdapter;
import com.genius.minds.mycontest.upcoming.livebating.UpcommingLivebatingModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LivebatingMatchActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener  {

    private ProgressBar spinner;
    private static final String TAG = "Genius";
    List<LivebatingMatchModel> productList= new ArrayList<>();
    RecyclerView recyclerView;
    SessionManager session;
    SwipeRefreshLayout mSwipeRefreshLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livebating_match);


        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this);
        recyclerView = findViewById(R.id.recylcerView);
//        String category=getIntent().getStringExtra("category");
//        Toast.makeText(this, ""+category, Toast.LENGTH_SHORT).show();
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
        mSwipeRefreshLayout.setRefreshing(false);
        // Fetching data from server
        productList.clear();
        loadMatches();
    }
    private void loadMatches() {
        mSwipeRefreshLayout.setRefreshing(true);
        User user = SharedPrefManager.getInstance(this).getUser();
        final String emailuser = user.getEmail();
        String category=getIntent().getStringExtra("category");

        JSONObject json = new JSONObject();
        try {
            json.put("category_name", category);
            json.put("email", emailuser);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LIST_LIVE_BATING_MATCH, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject object = response.getJSONObject("geniusbetting");

                            JSONArray jsonArray = object.getJSONArray("MatchList");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                LivebatingMatchModel model = new LivebatingMatchModel();
                                model.setId(jsonObject.getString("id "));
                                model.setSeries(jsonObject.getString("series"));
                                model.setMatchcode(jsonObject.getString("matchcode"));
                                model.setTeam1(jsonObject.getString("team1"));
                                model.setTeam2(jsonObject.getString("team2"));
                                model.setF_logo(jsonObject.getString("f_logo"));
                                model.setS_logo(jsonObject.getString("s_logo"));
                                model.setMdate(jsonObject.getString("mdate"));
                                model.setMtime(jsonObject.getString("mtime"));
                                model.setCategory(jsonObject.getString("category"));
                                productList.clear();
                                productList.add(model);
                            }
                            //creating adapter object and setting it to recyclerview
                            LivebatingMatchAdapter adapter = new LivebatingMatchAdapter(getApplicationContext(), productList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            mSwipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);

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