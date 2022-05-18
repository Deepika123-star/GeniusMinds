package com.genius.minds.matches;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Model.ResponseListMatchs.MatchListItem;
import com.genius.minds.Model.ResponseListMatchs.ResponseListMatch;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewMatchActivity extends BaseActivity {
    Activity activity;
    ImageView iv_backMore;
   static RecyclerView rec_newMatch;
   static NewMatchAdapter newMatchAdapter;
   static List<MatchListItem>matchListItems =new ArrayList<>();
    ArrayList<ModelNewHome> modelNewHomes;
    int[] _img = {R.drawable.karchi};
    int[] _img1 = {R.drawable.karchi};
    int[] _img2 = {R.drawable.karchi};
    int[] _img3 = {R.drawable.karchi};

    String [] karachiName={"karachi"};
    String [] PantherName={"Panther"};
    String [] karachi1Name={"karachi"};
    String [] Panther1Name={"Panther"};
    String catName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);
        activity=NewMatchActivity.this;
        rec_newMatch=findViewById(R.id.rec_newMatch);
        iv_backMore=findViewById(R.id.iv_backMore);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        catName=getIntent().getStringExtra("cat_name");

        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.matchLists(activity,catName,"1");

        }else{
            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
        }

//        RecyclerView.LayoutManager vertical = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
//        rec_newMatch.setLayoutManager(vertical);
//        rec_newMatch.setItemAnimator(new DefaultItemAnimator());
//        modelNewHomes = new ArrayList<>();
//        for (int i = 0; i < karachiName.length; i++) {
//            ModelNewHome ab = new ModelNewHome(karachiName[i],PantherName[i],karachi1Name[i],Panther1Name[i],_img[i], _img1[i],_img2[i],_img3[i]);
//            modelNewHomes.add(ab);
//        }
//        newMatchAdapter = new NewMatchAdapter(modelNewHomes,activity);
//        rec_newMatch.setAdapter(newMatchAdapter);



    }

    public static void matchListData(Context context, String response) {
        Type type = new TypeToken<ResponseListMatch>() {}.getType();
        ResponseListMatch responseDashBoardList = new Gson().fromJson(response, type);

        matchListItems=responseDashBoardList.getGeniusbetting().getMatchList();
        if (matchListItems.size() > 0) {
            newMatchAdapter = new NewMatchAdapter(matchListItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            rec_newMatch.setLayoutManager(horizontalLayoutManagaer1);
            rec_newMatch.setAdapter(newMatchAdapter);

        }

    }

}