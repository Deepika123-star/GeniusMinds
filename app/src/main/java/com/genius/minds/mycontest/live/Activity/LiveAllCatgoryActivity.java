package com.genius.minds.mycontest.live.Activity;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.genius.minds.mycontest.live.LiveMatches;
import com.skydoves.elasticviews.ElasticButton;

public class LiveAllCatgoryActivity extends BaseActivity implements View.OnClickListener {
    ImageView iv_backMore;
    ElasticButton btn_geniusBetting,btn_liveBetting,btn_uniqueBetting,btn_fantasyBetting;
    Activity activity;
    Fragment nav_host_fragment;
    String teamOne,categoryMatch,seriesName,matchCode,matchTime,matchDate,logoOne,logoTwo,idOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_all_catgory);
        activity= LiveAllCatgoryActivity.this;
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
        btn_liveBetting.setOnClickListener(this);
        btn_geniusBetting.setOnClickListener(this);



        categoryMatch=getIntent().getStringExtra("category");
//        teamOne=getIntent().getStringExtra("team");
//        idOne=getIntent().getStringExtra("id1");//
//        seriesName=getIntent().getStringExtra("series");
//        matchCode=getIntent().getStringExtra("matchcode");
//        matchTime=getIntent().getStringExtra("matchtime");
//        matchDate=getIntent().getStringExtra("matchdate");
//        logoOne=getIntent().getStringExtra("logo1");
//        logoTwo=getIntent().getStringExtra("logo2");



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_liveBetting:
                liveBetting();
                break;
            case R.id.btn_geniusBetting:
                geniusBetting();
                break;

        }
    }

    private void geniusBetting() {
        startActivity(new Intent(getApplicationContext(),LiveMatches.class).putExtra("category",categoryMatch));
    }

    private void liveBetting() {

        startActivity(new Intent(getApplicationContext(), LivebatingMatchActivity.class)
                .putExtra("category",categoryMatch));
    }

}