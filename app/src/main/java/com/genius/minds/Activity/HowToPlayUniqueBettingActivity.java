package com.genius.minds.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;

public class HowToPlayUniqueBettingActivity extends BaseActivity {
LinearLayout howPlayUniqueInHindi,howPlayUniqueInEnglish;
ImageView imageView;
Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play_unique_betting);
        activity=HowToPlayUniqueBettingActivity.this;
        imageView=findViewById(R.id.iv_backMore);
        howPlayUniqueInHindi=findViewById(R.id.howPlayUniqueInHindi);
        howPlayUniqueInEnglish=findViewById(R.id.howPlayUniqueInEnglish);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        howPlayUniqueInHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,PlayUniqueInHindiActivity.class,null);
            }
        });
        howPlayUniqueInEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,PlayUniqueInEnglishiActivity.class,null);

            }
        });

    }
}