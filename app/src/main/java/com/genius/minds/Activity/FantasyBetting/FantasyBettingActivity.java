package com.genius.minds.Activity.FantasyBetting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.R;

public class FantasyBettingActivity extends AppCompatActivity {
    ImageView iv_backMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy_betting);
        iv_backMore=findViewById(R.id.iv_backMore);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}