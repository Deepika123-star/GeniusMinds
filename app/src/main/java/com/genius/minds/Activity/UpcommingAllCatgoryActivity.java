package com.genius.minds.Activity;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.genius.minds.mycontest.upcoming.UpcomingMatches;
import com.genius.minds.mycontest.upcoming.livebating.UpcommingLivebatingMatchActivity;
import com.skydoves.elasticviews.ElasticButton;

public class UpcommingAllCatgoryActivity extends BaseActivity implements View.OnClickListener{
    ImageView iv_backMore;
    ElasticButton btn_geniusBetting,btn_liveBetting,btn_uniqueBetting,btn_fantasyBetting;
    Activity activity;
    Fragment nav_host_fragment;
    String teamOne,categoryMatch,seriesName,matchCode,matchTime,matchDate,logoOne,logoTwo,idOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcomming_all_catgory);
        activity=UpcommingAllCatgoryActivity.this;
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
        categoryMatch=getIntent().getStringExtra("category");
//        teamOne=getIntent().getStringExtra("team");
//        idOne=getIntent().getStringExtra("id1");//
//        seriesName=getIntent().getStringExtra("series");
//        matchCode=getIntent().getStringExtra("matchcode");
//        matchTime=getIntent().getStringExtra("matchtime");
//        matchDate=getIntent().getStringExtra("matchdate");
//        logoOne=getIntent().getStringExtra("logo1");
//        logoTwo=getIntent().getStringExtra("logo2");
//        Toast.makeText(this, ""+ matchCode, Toast.LENGTH_SHORT).show();


        btn_liveBetting.setOnClickListener(this);
        btn_geniusBetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(UpcommingAllCatgoryActivity.this, UpcomingSubCategory.class);
//                intent.putExtra("categoryNew",categoryMatch);
//                intent.putExtra("newtem1",teamOne);
//                intent.putExtra("id12",idOne);
//                intent.putExtra("series1",seriesName);
//                intent.putExtra("matchcodeone",matchCode);
//                intent.putExtra("matchtime1",matchTime);
//                intent.putExtra("matchdate1",matchDate);
//                intent.putExtra("logo11",logoOne);
//                intent.putExtra("logo12",logoTwo);
//                startActivity(intent);

                Intent intent=new Intent(UpcommingAllCatgoryActivity.this, UpcomingMatches.class);
                intent.putExtra("category",categoryMatch);
                startActivity(intent);



            }
        });

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
    
        case R.id.btn_liveBetting:
            liveBetting();
    
    }
    }

    private void liveBetting() {

        startActivity(new Intent(getApplicationContext(), UpcommingLivebatingMatchActivity.class)
                .putExtra("category",categoryMatch));
    }
}