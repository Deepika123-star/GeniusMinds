package com.genius.minds.mycontest.complete.Activity;

import static com.genius.minds.Config.MyBaseUrl.COMPLETE_LIVE_BATING_CONTEST;
import static com.genius.minds.Config.MyBaseUrl.COMPLETE_MATCH_LIST_LIVE_BATING_MATCH;
import static com.genius.minds.Config.MyBaseUrl.COMPLETE_SUB_CAT_LIST;
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
import com.genius.minds.Model.ResponseCompleteSubCategorys.ResponseCompleteSubCategory;
import com.genius.minds.Model.ResponseCompleteSubCategorys.SubcategoryListItem;
import com.genius.minds.R;
import com.genius.minds.mycontest.complete.Adapter.CompleteLivebatingMatchAdapter;
import com.genius.minds.mycontest.complete.Adapter.NewAdapterCompleteCategory;
import com.genius.minds.mycontest.complete.CompleteBatingContestMatchAdapter;
import com.genius.minds.mycontest.complete.CompleteSubCatModel;
import com.genius.minds.mycontest.complete.CompleteSubCategory;
import com.genius.minds.mycontest.complete.CompleteSubcatAdapter;
import com.genius.minds.mycontest.complete.Model.CompleteLivebatingMatchModel;
import com.genius.minds.mycontest.live.Activity.LivebatingContestMatchAdapter;
import com.genius.minds.mycontest.live.LiveBatingContestModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

public class CompleteBatingContestActivity extends BaseActivity {

    private static final String TAG = "Genius";
    List<CompleteSubCatModel> productList;
    static RecyclerView recyclerView,rec_completeSubCat;
    CircleImageView team1,team2;
    SessionManager session;
    private ProgressBar spinner;
    TextView teamname,timeleft,series,tv_Date;
    SwipeRefreshLayout mSwipeRefreshLayout ;

    String teamOne,categoryName,seriesName,matchCode,matchTime,matchDate,logoOne,logoTwo,type;
    Activity activity;
    static List<LiveBatingContestModel> subcategoryListItems=new ArrayList<>();
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_sub_category);
        activity= CompleteBatingContestActivity.this;
        recyclerView = findViewById(R.id.recylcerView);
        team1=findViewById(R.id.f_logo);
        team2=findViewById(R.id.s_logo);


        SharedPreferences pref = activity.getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(activity);
        User user = SharedPrefManager.getInstance(activity).getUser();
        String  Email_address=user.getEmail();

        teamname=(TextView)findViewById(R.id.teamname);
        timeleft=(TextView)findViewById(R.id.timeleft);
        series=(TextView)findViewById(R.id.series);
        rec_completeSubCat=findViewById(R.id.rec_completeSubCat);
        tv_Date=findViewById(R.id.tv_Date);





        categoryName=getIntent().getStringExtra("categoryNew");
        teamOne=getIntent().getStringExtra("newtem1");
        seriesName=getIntent().getStringExtra("series1");
        matchCode=getIntent().getStringExtra("matchcodeone");
        matchTime=getIntent().getStringExtra("matchtime1");
        matchDate=getIntent().getStringExtra("matchdate1");
        logoOne=getIntent().getStringExtra("logo11");
        logoTwo=getIntent().getStringExtra("logo12");

        Log.v("response","1 ...."+categoryName);
        Log.v("response","2 ...."+teamOne);
        Log.v("response","3 ...."+seriesName);
        Log.v("response","4 ...."+matchCode);
        Log.v("response","5 ...."+matchTime);
        Log.v("response","6 ...."+matchDate);
        Log.v("response","7 ...."+matchDate);



        teamname.setText(teamOne);
        series.setText(seriesName);
        tv_Date.setText(matchDate);

        Picasso.get().load(logoOne).placeholder(R.drawable.dummy).into(team1);
        Picasso.get().load(logoTwo).placeholder(R.drawable.dummy).into(team2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        mSwipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        productList = new ArrayList<>();
        //spinner.setVisibility(View.VISIBLE);

        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            loadSubCategory();

        }else{
            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
        }

        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                    loadSubCategory();

                }else{
                    UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                }
                loadSubCategory();
            }
        });






        initToolbar();


        String date1 =matchDate;
        String time =matchTime;
        System.out.println("Date "+date1+" Time "+time);
        String startDate = date1 +" "+time+":00";
        final List<Long> list = findDifference(startDate);
        if (timer != null) {
            timer.cancel();
        }
        try {
            int FlashCount = (int) (list.get(0)*31536000)+(int) (list.get(1)*86400)+(int) (list.get(2)*3600)+(int) (list.get(3)*60);
            long millisUntilFinished = FlashCount * 1000;
            System.out.println("Time left "+millisUntilFinished);
            timer = new CountDownTimer(millisUntilFinished, 1000) {
                public void onTick(long millisUntilFinished) {
                    long Days = TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
                    long Hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished));
                    long Minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
                    long Seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
                    String format = "%1$02d";
                    timeleft.setText(String.format(format, Days) + ":" + String.format(format, Hours) + ":" + String.format(format, Minutes) + ":" + String.format(format, Seconds)+" left");
                }

                public void onFinish() {
                    timeleft.setText("Completed");
                }

            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void loadSubCategory() {
        mSwipeRefreshLayout.setRefreshing(true);
        String category=getIntent().getStringExtra("category");
        User user = SharedPrefManager.getInstance(this).getUser();
        final String emailuser = user.getEmail();
        Log.d(TAG, "matchcode: "+matchCode);
        Log.d(TAG, "series: "+seriesName);
        Log.d(TAG, "matchurl: "+MATCH_LIST+category);
        JSONObject json = new JSONObject();
        try {
            json.put("matchcode",matchCode);
            json.put("series", seriesName);
            json.put("email", emailuser);
//            json.put("matchcode","MIvsRCB192");
//            json.put("series", "Live Match");
//            json.put("email", "satyadav919@gmail.com");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, COMPLETE_LIVE_BATING_CONTEST, json,
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
                                subcategoryListItems.clear();
                                subcategoryListItems.add(model);


                                Log.v("response","2.....   "+response.toString());

                            }
//                            creating adapter object and setting it to recyclerview
                            CompleteBatingContestMatchAdapter adapter = new CompleteBatingContestMatchAdapter(getApplicationContext(), subcategoryListItems);
                            rec_completeSubCat.setAdapter(adapter);
                            //rec_completeSubCat.setHasFixedSize(true);
                            rec_completeSubCat.setLayoutManager(new LinearLayoutManager(context));
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
        getSupportActionBar().setTitle("Complete Contest");
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


    public List<Long> findDifference(String end_date)
    {
        List<Long> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String start_date=sdf.format(new Date());
        try {

            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            list.add(difference_In_Years);
            list.add(difference_In_Days);
            list.add(difference_In_Hours);
            list.add(difference_In_Minutes);
            list.add(difference_In_Seconds);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }







}