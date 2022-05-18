package com.genius.minds.Activity;

import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Adapter.ProfileViewPagerAdapter;
import com.genius.minds.Fragment.AllRounderFragment;
import com.genius.minds.Fragment.BatsmanFragment;
import com.genius.minds.Fragment.BowlerFragment;
import com.genius.minds.Fragment.WicketKeeperFragment;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.google.android.material.tabs.TabLayout;
import com.skydoves.elasticviews.ElasticButton;

public class CreateTeamActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager1;
    ElasticButton btn_Continue,btn_TeamPreview;
   Activity activity;
   ImageView iv_backMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);
        activity=CreateTeamActivity.this;
        viewPager1 = findViewById(R.id.viewpager1);
        btn_Continue = findViewById(R.id.btn_Continue);
        btn_TeamPreview = findViewById(R.id.btn_TeamPreview);
        iv_backMore = findViewById(R.id.iv_backMore);
        setupViewPager(viewPager1);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager1);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,ContinueActivity.class,null);
            }
        });
        btn_TeamPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,TeamPreviewActivity.class,null);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager1) {
        ProfileViewPagerAdapter profileViewPagerAdapter = new ProfileViewPagerAdapter(getSupportFragmentManager());
        profileViewPagerAdapter.addFragment(new WicketKeeperFragment(), "WK(0)");
        profileViewPagerAdapter.addFragment(new BatsmanFragment(), "BAT(0)");
        profileViewPagerAdapter.addFragment(new AllRounderFragment(), "AR(0)");
        profileViewPagerAdapter.addFragment(new BowlerFragment(), "BOWL(0)");
        viewPager1.setAdapter(profileViewPagerAdapter);
    }

}