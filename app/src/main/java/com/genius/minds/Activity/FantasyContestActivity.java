package com.genius.minds.Activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.skydoves.elasticviews.ElasticButton;

public class FantasyContestActivity extends BaseActivity {
    ElasticButton bt_CreateTeam;
   Activity activity;
   ImageView iv_backFantasy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy_contest);
        activity=FantasyContestActivity.this;
         bt_CreateTeam=findViewById(R.id.bt_CreateTeam);
        iv_backFantasy=findViewById(R.id.iv_backFantasy);
        iv_backFantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_CreateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(FantasyContestActivity.this,CreateTeamActivity.class,null);

            }
        });

    }
}