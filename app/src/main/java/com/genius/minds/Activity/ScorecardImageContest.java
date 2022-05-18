package com.genius.minds.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.squareup.picasso.Picasso;

public class ScorecardImageContest extends BaseActivity {
String newImg;
    ImageView scoreimage;
Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard_image_contest);
        activity=ScorecardImageContest.this;
        scoreimage=findViewById(R.id.scoreimage);

        newImg =getIntent().getStringExtra("img_id");
//        Toast.makeText(this, ""+newImg, Toast.LENGTH_SHORT).show();



        Picasso.get().load(newImg).placeholder(R.drawable.dummy).into(scoreimage);

    }
}