package com.genius.minds.Activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Adapter.AdapterContinue;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Model.ModelContinue;
import com.genius.minds.R;
import com.skydoves.elasticviews.ElasticButton;

import java.util.ArrayList;

public class ContinueActivity extends BaseActivity {
 ImageView iv_backMore;
 Activity activity;
    String [] PlayerName={"V Kohali","Q Kock","J Buttler","J Baristow","V Kohali","Q Kock","J Buttler","Cricket","J Buttler","J Buttler",
            "V Kohali","Q Kock","J Buttler","J Baristow","V Kohali"};
    String [] point={"705","109","987","212","765","234","433","109","655","977","103","150","708","786","109"};
    AdapterContinue adapterWicketKeeper;
    ArrayList<ModelContinue> modelWicketKeepers;
    RecyclerView rec_continue;
    ElasticButton btn_saveTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue);
      activity=ContinueActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        rec_continue=findViewById(R.id.rec_continue);
        btn_saveTeam=findViewById(R.id.btn_saveTeam);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_saveTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(activity,SelectTeamActivity.class,null);
            }
        });

        RecyclerView.LayoutManager vertical = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
        rec_continue.setLayoutManager(vertical);
        rec_continue.setItemAnimator(new DefaultItemAnimator());
        modelWicketKeepers = new ArrayList<>();
        for (int i = 0; i < PlayerName.length; i++) {
            ModelContinue ab = new ModelContinue(PlayerName[i],point[i]);
            modelWicketKeepers.add(ab);
        }
        adapterWicketKeeper = new AdapterContinue(modelWicketKeepers,context);
        rec_continue.setAdapter(adapterWicketKeeper);

    }
}