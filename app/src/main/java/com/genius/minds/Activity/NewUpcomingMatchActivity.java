package com.genius.minds.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Model.ResponseListMatchs.MatchListItem;
import com.genius.minds.Model.ResponseListMatchs.ResponseListMatch;
import com.genius.minds.R;
import com.genius.minds.matches.ModelNewHome;
import com.genius.minds.matches.NewMatchAdapter;
import com.genius.minds.mycontest.upcoming.Adapter.NewUpcomingMatchAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewUpcomingMatchActivity extends BaseActivity {
    Activity activity;
    ImageView iv_backMore;
    static RecyclerView rec_newUpcoming;
    static NewUpcomingMatchAdapter newMatchAdapter;
    static List<MatchListItem> matchListItems =new ArrayList<>();
    String catName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_upcoming_match);
        activity=NewUpcomingMatchActivity.this;
        rec_newUpcoming=findViewById(R.id.rec_newUpcoming);
        catName=getIntent().getStringExtra("cat_name");

        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.matchLists(activity,catName,"2");

        }else{
            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
        }
    }

    public static void upcomingmatchlist(Context context, String response) {

        Type type = new TypeToken<ResponseListMatch>() {}.getType();
        ResponseListMatch responseDashBoardList = new Gson().fromJson(response, type);

        matchListItems=responseDashBoardList.getGeniusbetting().getMatchList();
        if (matchListItems.size() > 0) {
            newMatchAdapter = new NewUpcomingMatchAdapter(matchListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            rec_newUpcoming.setLayoutManager(horizontalLayoutManagaer1);
            rec_newUpcoming.setAdapter(newMatchAdapter);

        }
    }

}