package com.genius.minds.mycontest.live;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
import com.genius.minds.mycontest.live.Adapter.NewAdapterLiveCategory;
import com.genius.minds.mycontest.upcoming.Adapter.NewAdapterSubCategory;
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

import static com.genius.minds.Config.MyBaseUrl.LIVE_SUB_CAT_LIST;
import static com.genius.minds.Config.MyBaseUrl.MATCH_LIST;

import de.hdodenhof.circleimageview.CircleImageView;

public class LiveSubCategory extends BaseActivity {

    private static final String TAG = "Genius";
    List<LiveSubCatModel> productList;
    static RecyclerView recyclerView,rec_newLiveSubCategory;
    SessionManager session;
   CircleImageView  team1,team2;
    private ProgressBar spinner;
    TextView teamname,timeleft,series,tv_Date;
    SwipeRefreshLayout mSwipeRefreshLayout ;

    String teamOne,categoryName,seriesName,matchCode,matchTime,matchDate,logoOne,logoTwo,type;
    Activity activity;
    static NewAdapterLiveCategory adapterSubCategory;
    static List<SubcategoryListItem> subcategoryListItems=new ArrayList<>();
    CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_subcategory);
        activity=LiveSubCategory.this;
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
        productList = new ArrayList<>();
        mSwipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        productList = new ArrayList<>();
        //spinner.setVisibility(View.VISIBLE);



        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.matchCategory(activity,categoryName,matchCode,type,"3");
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
                            UtilMethods.INSTANCE.matchCategory(activity,categoryName,matchCode,type,"3");
                            loadSubCategory();

                        }else{
                            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                        }
                    }
                },3000);
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
                    timeleft.setText("Live");
                }

            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadSubCategory() {
        mSwipeRefreshLayout.setRefreshing(true);
        String category=getIntent().getStringExtra("category");
        final String matchcode=getIntent().getStringExtra("matchcode");
        final String series1=getIntent().getStringExtra("series");
        User user = SharedPrefManager.getInstance(this).getUser();
        final String emailuser = user.getEmail();
        Log.d(TAG, "matchcode: "+matchcode);
        Log.d(TAG, "series: "+series1);
        Log.d(TAG, "email: "+emailuser);
        Log.d(TAG, "matchurl: "+MATCH_LIST+category);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, LIVE_SUB_CAT_LIST+category+"&series="+series1+"&matchcode="+matchcode+"&email="+emailuser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject contest = array.getJSONObject(i);
                                productList.add(new LiveSubCatModel(
                                        contest.getInt("id"),
                                        contest.getString("cat_name"),
                                        contest.getString("sub_cat"),
                                        contest.getString("matchcode"),
                                        contest.getString("series")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            LiveSubcatAdapter adapter = new LiveSubcatAdapter(LiveSubCategory.this, productList);
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
        getSupportActionBar().setTitle("Live Betting Sub Category");
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

    public static void livesubCatgory(Context context, String response) {
        Type type = new TypeToken<ResponseSubCategory>() {}.getType();
        ResponseSubCategory responseDashBoardList = new Gson().fromJson(response, type);

        subcategoryListItems=responseDashBoardList.getGeniusbetting().getSubcategoryList();
        if (subcategoryListItems.size() > 0) {
            adapterSubCategory = new NewAdapterLiveCategory(subcategoryListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            rec_newLiveSubCategory.setLayoutManager(horizontalLayoutManagaer1);
            rec_newLiveSubCategory.setAdapter(adapterSubCategory);

        }
    }

}