package com.genius.minds.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.R;

public class HowToAddMoneyInEnglishActivity extends AppCompatActivity {
ImageView iv_backMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_add_money_in_english);
        iv_backMore=findViewById(R.id.iv_backMore);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}