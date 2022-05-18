package com.genius.minds.mycontest.live.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Helper.Constaints;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseListMatchs.MatchListItem;
import com.genius.minds.Model.ResponseListMatchs.ResponseListMatch;
import com.genius.minds.R;
import com.genius.minds.mycontest.live.Adapter.NewLiveMatchAdapter;
import com.genius.minds.mycontest.upcoming.Adapter.NewUpcomingMatchAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewLiveMatchActivity extends BaseActivity {
    Activity activity;
    ImageView iv_backMore;
    static RecyclerView rec_newLive;
    static NewLiveMatchAdapter newMatchAdapter;
    static List<MatchListItem> matchListItems =new ArrayList<>();
    String catName;
    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_live_match);
        activity=NewLiveMatchActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        rec_newLive=findViewById(R.id.rec_newLive);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getApplicationContext());
        User user = SharedPrefManager.getInstance(this).getUser();
        Constaints.Email_address=user.getEmail();
//        Toast.makeText(activity, ""+Constaints.Email_address, Toast.LENGTH_SHORT).show();

        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        catName=getIntent().getStringExtra("category");



        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.matchLists(activity,catName,"3");

        }else{
            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
        }
    }
    public static void livematchlist(Context context, String response) {
        Type type = new TypeToken<ResponseListMatch>() {
        }.getType();
        ResponseListMatch responseDashBoardList = new Gson().fromJson(response, type);

        matchListItems = responseDashBoardList.getGeniusbetting().getMatchList();
        if (matchListItems.size() > 0) {
            newMatchAdapter = new NewLiveMatchAdapter(matchListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rec_newLive.setLayoutManager(horizontalLayoutManagaer1);
            rec_newLive.setAdapter(newMatchAdapter);

        }
    }
}