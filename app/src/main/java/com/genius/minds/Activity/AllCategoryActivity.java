package com.genius.minds.Activity;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Activity.FantasyBetting.FantasyBettingActivity;
import com.genius.minds.Activity.LiveBetting.LiveBettingActivity;
import com.genius.minds.Activity.UniqueBetting.UniqueBettingActivity;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.genius.minds.subcategory.BettingCategory;
import com.skydoves.elasticviews.ElasticButton;

public class AllCategoryActivity extends BaseActivity {
ImageView iv_backMore;
ElasticButton btn_geniusBetting,btn_liveBetting,btn_uniqueBetting,btn_fantasyBetting;
Activity activity;
Fragment nav_host_fragment;
String teamOne,categoryMatch,seriesName,matchCode,matchTime,matchDate,logoOne,logoTwo,idOne,team_one,team_two;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);
        activity=AllCategoryActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        btn_liveBetting=findViewById(R.id.btn_liveBetting);
        btn_uniqueBetting=findViewById(R.id.btn_uniqueBetting);
        btn_geniusBetting=findViewById(R.id.btn_geniusBetting);
        btn_fantasyBetting=findViewById(R.id.btn_fantasyBetting);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        categoryMatch=getIntent().getStringExtra("category");
        teamOne=getIntent().getStringExtra("team");
        team_one=getIntent().getStringExtra("team_one");
        team_two=getIntent().getStringExtra("team_two");
        idOne=getIntent().getStringExtra("id1");
        seriesName=getIntent().getStringExtra("series");
        matchCode=getIntent().getStringExtra("matchcode");
        matchTime=getIntent().getStringExtra("matchtime");
        matchDate=getIntent().getStringExtra("matchdate");
        logoOne=getIntent().getStringExtra("logo1");
        logoTwo=getIntent().getStringExtra("logo2");
        btn_geniusBetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllCategoryActivity.this,BettingCategory.class);
                intent.putExtra("id12",idOne);
                intent.putExtra("categoryNew",categoryMatch);
                intent.putExtra("newtem1",teamOne);
                intent.putExtra("series1",seriesName);
                intent.putExtra("matchcodeone",matchCode);
                intent.putExtra("matchtime1",matchTime);
                intent.putExtra("matchdate1",matchDate);
                intent.putExtra("logo11",logoOne);
                intent.putExtra("logo12",logoTwo);
                startActivity(intent);



            }
        });
        btn_liveBetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AllCategoryActivity.this,LiveBettingActivity.class);
                intent.putExtra("id12",idOne);
                intent.putExtra("categoryNew",categoryMatch);
                intent.putExtra("newtem1",teamOne);
                intent.putExtra("newteam_one",team_one);
                intent.putExtra("newteam_two",team_two);
                intent.putExtra("series1",seriesName);
                intent.putExtra("matchcodeone",matchCode);
                intent.putExtra("matchtime1",matchTime);
                intent.putExtra("matchdate1",matchDate);
                intent.putExtra("logo11",logoOne);
                intent.putExtra("logo12",logoTwo);
                startActivity(intent);



            }
        });
//        btn_liveBetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToActivity(AllCategoryActivity.this, LiveBettingActivity.class,null);
//
//            }
//        });
        btn_uniqueBetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(AllCategoryActivity.this, UniqueBettingActivity.class,null);

            }
        });
        btn_fantasyBetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(AllCategoryActivity.this, FantasyBettingActivity.class,null);

            }
        });
    }
}