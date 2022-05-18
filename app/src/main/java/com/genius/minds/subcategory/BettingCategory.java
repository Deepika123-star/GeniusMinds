package com.genius.minds.subcategory;

import static com.genius.minds.Config.MyBaseUrl.MATCH_LIST;
import static com.genius.minds.Config.MyBaseUrl.SUB_CAT_LIST;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Adapter.AdapterSubCategory;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Model.ResponseSubCategories.ResponseSubCategory;
import com.genius.minds.Model.ResponseSubCategories.SubcategoryListItem;
import com.genius.minds.R;
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

public class BettingCategory extends BaseActivity   {

    private static final String TAG = "Genius";
    List<SubCatModel> productList;
    static RecyclerView recyclerView,rec_category;
    CircleImageView team1,team2;
    private ProgressBar spinner;
    TextView teamname,timeleft,series,tv_Date;
    SwipeRefreshLayout mSwipeRefreshLayout ;
    String teamOne,categoryName,seriesName,matchCode,matchTime,matchDate,logoOne,logoTwo,type;
    static  AdapterSubCategory adapterSubCategory;
    static List<SubcategoryListItem> subcategoryListItems=new ArrayList<>();
   Activity activity;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betting_category);
        activity=BettingCategory.this;
        recyclerView = findViewById(R.id.recylcerView);
        rec_category = findViewById(R.id.rec_category);
        team1=findViewById(R.id.f_logo);
        team2=findViewById(R.id.s_logo);
        teamname=(TextView)findViewById(R.id.teamname);
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

        teamname.setText(teamOne);
        series.setText(seriesName);
        tv_Date.setText(matchDate);
//        timeleft.setText(matchTime+" left");
        Picasso.get().load(logoOne).placeholder(R.drawable.dummy).into(team1);
        Picasso.get().load(logoTwo).placeholder(R.drawable.dummy).into(team2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefreshLayout =(SwipeRefreshLayout)findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        productList = new ArrayList<>();
        //spinner.setVisibility(View.VISIBLE);


        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.matchCategory(activity,categoryName,matchCode,type,"1");

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
                            UtilMethods.INSTANCE.matchCategory(activity,categoryName,matchCode,type,"1");
                            loadSubCategory();

                        }else{
                            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                        }
                    }
                },3000);
            }
        });



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





        loadSubCategory();


        initToolbar();
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


    private void loadSubCategory() {
//        mSwipeRefreshLayout.setRefreshing(true);
        String category=getIntent().getStringExtra("category");
        final String matchcode=getIntent().getStringExtra("matchcode");
        final String series=getIntent().getStringExtra("series");
        Log.d(TAG, "matchcode: "+matchcode);
        Log.d(TAG, "series: "+series);
        Log.d(TAG, "matchurl: "+MATCH_LIST+category);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, SUB_CAT_LIST+category+"&series="+series+"&matchcode="+matchcode,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject contest = array.getJSONObject(i);
                                productList.add(new SubCatModel(
                                        contest.getInt("id"),
                                        contest.getString("cat_name"),
                                        contest.getString("sub_cat"),
                                        contest.getString("matchcode"),
                                        contest.getString("series")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            BettingAdapter adapter = new BettingAdapter(BettingCategory.this, productList);
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
        getSupportActionBar().setTitle("Genius Betting Contest");
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

    public static void matchListCatgory(Context context, String response) {
        Type type = new TypeToken<ResponseSubCategory>() {}.getType();
        ResponseSubCategory responseDashBoardList = new Gson().fromJson(response, type);

        subcategoryListItems=responseDashBoardList.getGeniusbetting().getSubcategoryList();
        if (subcategoryListItems.size() > 0) {
            adapterSubCategory = new AdapterSubCategory(subcategoryListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            rec_category.setLayoutManager(horizontalLayoutManagaer1);
            rec_category.setAdapter(adapterSubCategory);

        }

    }

}