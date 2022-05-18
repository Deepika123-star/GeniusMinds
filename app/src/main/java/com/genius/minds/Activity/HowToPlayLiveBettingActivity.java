package com.genius.minds.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;

public class HowToPlayLiveBettingActivity extends BaseActivity {
ImageView iv_backMore;
Activity activity;
LinearLayout howPlayLiveInHindi,howPlayLiveInEnglish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play_live_betting);
        activity=HowToPlayLiveBettingActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        howPlayLiveInHindi=findViewById(R.id.howPlayLiveInHindi);
        howPlayLiveInEnglish=findViewById(R.id.howPlayLiveInEnglish);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        howPlayLiveInHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,PlayLiveInHindiActivity.class,null);
            }
        });
        howPlayLiveInEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,ToPlayLiveInEglishActivity.class,null);
            }
        });
    }
}