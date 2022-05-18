package com.genius.minds.Activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;

public class HowToPlayActivity extends BaseActivity {
    ImageView iv_backMore;
    LinearLayout playFantasyPoints,playGeniusBettingContest,playLiveBettingContest,playUniqueBettingContest;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        activity=HowToPlayActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        playFantasyPoints=findViewById(R.id.playFantasyPoints);
        playGeniusBettingContest=findViewById(R.id.playGeniusBettingContest);
        playLiveBettingContest=findViewById(R.id.playLiveBettingContest);
        playUniqueBettingContest=findViewById(R.id.playUniqueBettingContest);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        playFantasyPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,HowToFantasyPointActivity.class,null);
            }
        });
        playGeniusBettingContest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,HowToPlayGeniuseBettingActivity.class,null);
            }
        });
        playLiveBettingContest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,HowToPlayLiveBettingActivity.class,null);
            }
        });
        playUniqueBettingContest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,HowToPlayUniqueBettingActivity.class,null);
            }
        });
    }
}