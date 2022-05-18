package com.genius.minds.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;

public class HowToPlayGeniuseBettingActivity extends BaseActivity {
ImageView iv_backMore;
Activity activity;
LinearLayout howPlayGeniusInHindi,howPlayGeniusInEnglish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play_geniuse_betting);
        activity=HowToPlayGeniuseBettingActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        howPlayGeniusInHindi=findViewById(R.id.howPlayGeniusInHindi);
        howPlayGeniusInEnglish=findViewById(R.id.howPlayGeniusInEnglish);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        howPlayGeniusInHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToActivity(activity,HowPlayGeniusInHindiActivity.class,null);
            }
        });
        howPlayGeniusInEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToActivity(activity,HowPlayGeniusInEnglishActivity.class,null);
            }
        });
    }
}