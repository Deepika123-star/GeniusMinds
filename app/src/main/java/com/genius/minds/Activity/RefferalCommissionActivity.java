package com.genius.minds.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;

public class RefferalCommissionActivity extends BaseActivity {
LinearLayout CommissionInHindi,CommissionInEnglish;
ImageView imageView;
Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refferal_commission);
        activity=RefferalCommissionActivity.this;
        imageView=findViewById(R.id.iv_backMore);
        CommissionInHindi=findViewById(R.id.CommissionInHindi);
        CommissionInEnglish=findViewById(R.id.CommissionInEnglish);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        CommissionInHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,RefferalCommssionInHindi.class,null);
            }
        });
        CommissionInEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,RefferalCommssionInEnglsih.class,null);
            }
        });
    }
}