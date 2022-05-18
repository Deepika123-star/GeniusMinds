package com.genius.minds.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Helper.BaseFragment;
import com.genius.minds.R;

public class HowToFantasyPointActivity extends BaseActivity {
Activity activity;
ImageView iv_backMore;
LinearLayout howFantasyInHindi,howFantasyInEnglish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_fantasy_point);
       activity=HowToFantasyPointActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        howFantasyInHindi=findViewById(R.id.howFantasyInHindi);
        howFantasyInEnglish=findViewById(R.id.howFantasyInEnglish);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        howFantasyInHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,FantasyPlayInHindiActivity.class,null);
            }
        });
        howFantasyInEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,FantasyPlayInEnglishActivity.class,null);
            }
        });

    }
}