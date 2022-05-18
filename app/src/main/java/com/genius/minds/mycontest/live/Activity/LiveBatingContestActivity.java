package com.genius.minds.mycontest.live.Activity;

import static com.genius.minds.Config.MyBaseUrl.COMPLETE_MATCH_LIST_LIVE_BATING_MATCH;
import static com.genius.minds.Config.MyBaseUrl.LIVE_BATING_CONTEST;
import static com.genius.minds.Config.MyBaseUrl.LIVE_SUB_CAT_LIST;
import static com.genius.minds.Config.MyBaseUrl.MATCH_LIST;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseSubCategories.ResponseSubCategory;
import com.genius.minds.Model.ResponseSubCategories.SubcategoryListItem;
import com.genius.minds.R;
import com.genius.minds.mycontest.complete.Adapter.CompleteLivebatingMatchAdapter;
import com.genius.minds.mycontest.complete.Model.CompleteLivebatingMatchModel;
import com.genius.minds.mycontest.live.Adapter.NewAdapterLiveCategory;

import com.genius.minds.mycontest.live.LiveBatingContestModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class LiveBatingContestActivity extends BaseActivity {

    private static final String TAG = "Genius";
    static RecyclerView recyclerView,rec_newLiveSubCategory;
    SessionManager session;
    CircleImageView team1,team2;
    private ProgressBar spinner;
    TextView teamname,timeleft,series,tv_Date;
    SwipeRefreshLayout mSwipeRefreshLayout ;

    String teamOne,categoryName,seriesName,matchCode,matchTime,matchDate,logoOne,logoTwo,type;
    Activity activity;
    static NewAdapterLiveCategory adapterSubCategory;
    static List<LiveBatingContestModel> subcategoryListItems=new ArrayList<>();
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_bating_contest);
        activity= LiveBatingContestActivity.this;
        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this);
        recyclerView = findViewById(R.id.recylcerView);

        team1=findViewById(R.id.f_logo);
        team2=findViewById(R.id.s_logo);
        teamname=(TextView)findViewById(R.id.teamname);
        rec_newLiveSubCategory=findViewById(R.id.rec_newLiveSubCategory);
        timeleft=(TextView)findViewById(R.id.timeleft);
        series=(TextView)findViewById(R.id.series);
        tv_Date=(TextView)findViewById(R.id.tv_Date);



        categoryName=getIntent().getStringExtra("categoryNew");
        teamOne=getIntent().getStringExtra("newtem1");
        seriesName=getIntent().getStringExtra("series1");
        matchCode=getIntent().getStringExtra("matchcodeone");
        matchTime=getIntent().getStringExtra("matchtime1");
        matchDate=getIntent().getStringExtra("matchdate1");
        logoOne=getIntent().getStringExtra("logo11");
        logoTwo=getIntent().getStringExtra("logo12");
//        Toast.makeText(activity, ""+logoOne + logoTwo , Toast.LENGTH_SHORT).show();


        teamname.setText(teamOne);
        series.setText(seriesName);
        tv_Date.setText(matchDate);


        Picasso.get().load(logoOne).placeholder(R.drawable.dummy).into(team1);
        Picasso.get().load(logoTwo).placeholder(R.drawable.dummy).into(team2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        //spinner.setVisibility(View.VISIBLE);



        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            loadMatches();
        }else{
            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override

                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                            loadMatches();
                            mSwipeRefreshLayout.setRefreshing(false);
                        }else{
                            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                        }
                    }
                },3000);
            }
        });



        initToolbar();




    }



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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadMatches() {
        String tem=getIntent().getStringExtra("series1");
        mSwipeRefreshLayout.setRefreshing(true);
        User user = SharedPrefManager.getInstance(this).getUser();
        final String emailuser = user.getEmail();
        String category=getIntent().getStringExtra("category");
//        Toast.makeText(this, ""+category, Toast.LENGTH_SHORT).show();

        Log.d(TAG, "matchurl222----: "+MATCH_LIST+category);
        JSONObject json = new JSONObject();
        try {
            json.put("matchcode",matchCode);
            json.put("series",seriesName);
            json.put("email",emailuser);
            Log.d("loadMatches:-----", String.valueOf(json));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, LIVE_BATING_CONTEST, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("response","geniusbetting"+response.toString());
                        try {
                            JSONObject object = response.getJSONObject("geniusbetting");
                            JSONArray jsonArray = object.getJSONArray("ContestList");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                LiveBatingContestModel model = new LiveBatingContestModel();
                                model.setId(jsonObject.getString("id "));
                                model.setSeries(jsonObject.getString("series"));
                                model.setMatchcode(jsonObject.getString("matchcode"));
                                model.setMdate(jsonObject.getString("mdate"));
                                model.setTeam1(jsonObject.getString("team1"));
                                model.setTeam2(jsonObject.getString("team2"));
                                model.setFuser(jsonObject.getString("fuser"));
                                model.setSuser(jsonObject.getString("suser"));
                                model.setContest_name(jsonObject.getString("contest_name"));
                                model.setF_betting_rate(jsonObject.getString("f_betting_rate"));
                                model.setS_betting_rate(jsonObject.getString("s_betting_rate"));
                                model.setBetting(jsonObject.getString("betting"));
                                subcategoryListItems.add(model);


                                Log.v("response","2.....   "+response.toString());

                            }
//                            creating adapter object and setting it to recyclerview
                            LivebatingContestMatchAdapter adapter = new LivebatingContestMatchAdapter(getApplicationContext(), subcategoryListItems);
                            rec_newLiveSubCategory.setAdapter(adapter);
                           // rec_newLiveSubCategory.setHasFixedSize(true);
                            rec_newLiveSubCategory.setLayoutManager(new LinearLayoutManager(context));
                            adapter.notifyDataSetChanged();
                           // mSwipeRefreshLayout.setRefreshing(false);
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
}