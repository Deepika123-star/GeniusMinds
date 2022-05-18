package com.genius.minds.mycontest.complete.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.genius.minds.mycontest.complete.CompleteMatches;
import com.genius.minds.mycontest.complete.CompleteSubCategory;
import com.genius.minds.mycontest.live.Activity.LiveAllCatgoryActivity;
import com.genius.minds.mycontest.live.LiveSubCategory;
import com.skydoves.elasticviews.ElasticButton;

public class CompleteAllCatgoryActivity extends BaseActivity implements View.OnClickListener{
    ImageView iv_backMore;
    ElasticButton btn_geniusBetting,btn_liveBetting,btn_uniqueBetting,btn_fantasyBetting;
    Activity activity;
    Fragment nav_host_fragment;
    String teamOne,categoryMatch,seriesName,matchCode,matchTime,matchDate,logoOne,logoTwo,idOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_all_catgory);
        activity=CompleteAllCatgoryActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        btn_liveBetting=findViewById(R.id.btn_liveBetting);
        btn_uniqueBetting=findViewById(R.id.btn_uniqueBetting);
        btn_geniusBetting=findViewById(R.id.btn_geniusBetting);
        btn_fantasyBetting=findViewById(R.id.btn_fantasyBetting);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_liveBetting.setOnClickListener(this);
        btn_geniusBetting.setOnClickListener(this);

//        categoryMatch=getIntent().getStringExtra("category");
//        teamOne=getIntent().getStringExtra("team");
//        idOne=getIntent().getStringExtra("id1");//
//        seriesName=getIntent().getStringExtra("series");
//        matchCode=getIntent().getStringExtra("matchcode");
//        matchTime=getIntent().getStringExtra("timeleft");
//        matchDate=getIntent().getStringExtra("matchdate");
//        logoOne=getIntent().getStringExtra("logo1");
//        logoTwo=getIntent().getStringExtra("logo2");


        categoryMatch=getIntent().getStringExtra("category");
//        categoryMatch=getIntent().getStringExtra("category");
//        teamOne=getIntent().getStringExtra("team");
//        idOne=getIntent().getStringExtra("id1");//
//        seriesName=getIntent().getStringExtra("series");
//        matchCode=getIntent().getStringExtra("matchcode");
//        matchTime=getIntent().getStringExtra("matchtime");
//        matchDate=getIntent().getStringExtra("matchdate");
//        logoOne=getIntent().getStringExtra("logo1");
//        logoTwo=getIntent().getStringExtra("logo2");


//        btn_geniusBetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent=new Intent(CompleteAllCatgoryActivity.this, CompleteSubCategory.class);
////                intent.putExtra("categoryNew",categoryMatch);
////                intent.putExtra("newtem1",teamOne);
////                intent.putExtra("id12",idOne);
////                intent.putExtra("series1",seriesName);
////                intent.putExtra("matchcodeone",matchCode);
////                intent.putExtra("matchtime1",matchTime);
////                intent.putExtra("matchdate1",matchDate);
////                intent.putExtra("logo11",logoOne);
////                intent.putExtra("logo12",logoTwo);
////                startActivity(intent);
//
//
//
//
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_liveBetting:
                liveBetting();
                break;
            case R.id.btn_geniusBetting:
                geniusBetting();
                break;

        }
    }

    private void liveBetting() {
    startActivity(new Intent(getApplicationContext(),CompleteLivebatingMatchActivity.class).putExtra("category",categoryMatch));
    }

    private void geniusBetting() {
        Intent intent=new Intent(CompleteAllCatgoryActivity.this, CompleteMatches.class);
        intent.putExtra("category",categoryMatch);
        startActivity(intent);
    }

}