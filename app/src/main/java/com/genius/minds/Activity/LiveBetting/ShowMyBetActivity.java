package com.genius.minds.Activity.LiveBetting;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Adapter.AdapterLiveContestBetting;
import com.genius.minds.Adapter.AdapterShowMyBet;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseGetAllMatchLiveContest.ContestListItem;
import com.genius.minds.Model.ResponseGetAllMatchLiveContest.ResponseGetAllMatchLiveContests;
import com.genius.minds.Model.ResponseShowMyBets.MyBetListItem;
import com.genius.minds.Model.ResponseShowMyBets.ResponseShowMyBet;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowMyBetActivity extends BaseActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    String MatchCode,contestId;
    Activity activity;
    static AdapterShowMyBet adapterShowMyBet;
    static List<MyBetListItem> myBetListItems = new ArrayList<>();
    static String  imgs;

 static  RecyclerView rec_showMyBet;
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_bet);
        activity=ShowMyBetActivity.this;

        rec_showMyBet=findViewById(R.id.rec_showMyBet);


        SharedPreferences pref = context.getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(context);
        User user = SharedPrefManager.getInstance(context).getUser();
        String  Email_address=user.getEmail();
        MatchCode=getIntent().getStringExtra("matchCode");
        contestId=getIntent().getStringExtra("contest_id");
        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.liveshowBetList(activity,Email_address,MatchCode,contestId);


        }else{
            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
        }

        initToolbar();

    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Show My Bet");
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
    public static void showMyBetList(Context context, String response) {

        Type type = new TypeToken<ResponseShowMyBet>() {
        }.getType();
        ResponseShowMyBet responseDashBoardList = new Gson().fromJson(response, type);

        myBetListItems = responseDashBoardList.getGeniusbetting().getMyBetList();
        if (myBetListItems.size() > 0) {
            adapterShowMyBet = new AdapterShowMyBet(myBetListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rec_showMyBet.setLayoutManager(horizontalLayoutManagaer1);
            rec_showMyBet.setAdapter(adapterShowMyBet);

        }

    }

}