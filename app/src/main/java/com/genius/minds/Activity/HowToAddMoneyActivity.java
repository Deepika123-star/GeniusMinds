package com.genius.minds.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;

public class HowToAddMoneyActivity extends BaseActivity {
ImageView iv_backMore;
LinearLayout howToAddMoneyInHindi,howToAddMoneyInEnglish;
Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_add_money_activity);
        activity=HowToAddMoneyActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        howToAddMoneyInHindi=findViewById(R.id.howToAddMoneyInHindi);
        howToAddMoneyInEnglish=findViewById(R.id.howToAddMoneyInEnglish);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        howToAddMoneyInHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,HowToAddMoneyInEnglishActivity.class,null);
            }
        });
        howToAddMoneyInEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,HowToAddMoneyInHindiActivity.class,null);
            }
        });

    }
}