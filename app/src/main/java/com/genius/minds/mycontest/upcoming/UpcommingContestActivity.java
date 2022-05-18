package com.genius.minds.mycontest.upcoming;

import static com.genius.minds.Config.MyBaseUrl.MATCH_LIST;
import static com.genius.minds.Config.MyBaseUrl.UPCOMING_LIVE_BATING_CONTEST;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseUpcomingSubCategorys.SubcategoryListItem;
import com.genius.minds.R;
import com.genius.minds.mycontest.live.LiveBatingContestModel;
import com.genius.minds.mycontest.upcoming.Adapter.UpcommingLivebatingContestAdapter;
import com.genius.minds.mycontest.upcoming.Adapter.NewAdapterSubCategory;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpcommingContestActivity extends BaseActivity {
    private static final String TAG = "Genius";
    List<LiveBatingContestModel> productList;
    static RecyclerView recyclerView,rec_newSubCategory;
    SessionManager session;
    CircleImageView team1,team2;
    TextView teamname,timeleft,series,tv_Date;
    SwipeRefreshLayout mSwipeRefreshLayout ;
    String teamOne,categoryName,seriesName,matchCode,matchTime,matchDate,logoOne,logoTwo,type;
    Activity activity;
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcomming_contest);
        activity=UpcommingContestActivity.this;
        recyclerView = findViewById(R.id.recylcerView);
        team1=findViewById(R.id.f_logo);
        team2=findViewById(R.id.s_logo);
//
//        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
//        SharedPreferences.Editor editor = pref.edit();
//        session = new SessionManager(this);

        SharedPreferences pref = activity.getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(activity);
        User user = SharedPrefManager.getInstance(activity).getUser();
        String  Email_address=user.getEmail();

        teamname=(TextView)findViewById(R.id.teamname);
        rec_newSubCategory=findViewById(R.id.rec_newSubCategory);
        tv_Date=findViewById(R.id.tv_Date);


        categoryName=getIntent().getStringExtra("categoryNew");
        teamOne=getIntent().getStringExtra("newtem1");
        seriesName=getIntent().getStringExtra("series1");
        matchCode=getIntent().getStringExtra("matchcodeone");
        matchTime=getIntent().getStringExtra("matchtime1");
        matchDate=getIntent().getStringExtra("matchdate1");
        logoOne=getIntent().getStringExtra("logo11");
        logoTwo=getIntent().getStringExtra("logo12");
//        Toast.makeText(activity, ""+seriesName, Toast.LENGTH_SHORT).show();
        timeleft=(TextView)findViewById(R.id.timeleft);
        series=(TextView)findViewById(R.id.series);

        teamname.setText(teamOne);
        series.setText(seriesName);
        tv_Date.setText(matchDate);
        Picasso.get().load(logoOne).placeholder(R.drawable.dummy).into(team1);
        Picasso.get().load(logoTwo).placeholder(R.drawable.dummy).into(team2);




        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        mSwipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        productList = new ArrayList<>();

        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            loadSubCategory();

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
                            loadSubCategory();

                        }else{
                            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                        }
                    }
                },3000);
            }
        });

        loadSubCategory();
        initToolbar();

        String date1 =matchDate;
        String time =matchTime;
        System.out.println("Date "+date1+" Time "+time);
        String startDate = date1 +" "+time+":00";




    }


    private void loadSubCategory() {
        mSwipeRefreshLayout.setRefreshing(true);
        String category=getIntent().getStringExtra("categoryNew");
        final String matchcode=getIntent().getStringExtra("matchcodeone");
        final String series1=getIntent().getStringExtra("series1");
        User user = SharedPrefManager.getInstance(this).getUser();
        final String emailuser = user.getEmail();
        Log.d(TAG, "matchcodeone: "+matchcode);
        Log.d(TAG, "series1: "+series1);
        Log.d(TAG, "matchurl: "+MATCH_LIST+category);
//        Toast.makeText(this, ""+ matchcode, Toast.LENGTH_SHORT).show();

        JSONObject json = new JSONObject();
        try {
            json.put("matchcode", matchcode);
            json.put("series", series1);
            json.put("email", emailuser);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UPCOMING_LIVE_BATING_CONTEST, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("response","geniusbetting"+response.toString());
                        try {
                            JSONObject object = response.getJSONObject("geniusbetting");
                            JSONArray jsonArray = object.getJSONArray("ContestList");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.v("response","2.....   "+response.toString());
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
                                productList.clear();
                                productList.add(model);
                            }
                            //creating adapter object and setting it to recyclerview
                            UpcommingLivebatingContestAdapter adapter = new UpcommingLivebatingContestAdapter(getApplicationContext(), productList);
                            rec_newSubCategory.setAdapter(adapter);
                            rec_newSubCategory.setHasFixedSize(true);
                            rec_newSubCategory.setLayoutManager(new LinearLayoutManager(context));
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
        getSupportActionBar().setTitle("UpComing Contest");
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