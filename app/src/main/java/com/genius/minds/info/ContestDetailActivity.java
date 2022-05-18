package com.genius.minds.info;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.genius.minds.info.Adapter.AdapterContestDetails;
import com.genius.minds.info.Modeles.ModelContestDetail;

import java.util.ArrayList;

public class ContestDetailActivity extends BaseActivity {
    Activity activity;
    ImageView iv_backMore;
    RecyclerView rec_contestDetails;
    AdapterContestDetails adapterViewMore;
    ArrayList<ModelContestDetail> modelViewMores;

    String [] contestName={"Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket"};
    String [] entryFee={"2000","1000","5000","9000","7000","2000","1000","5000","9000","7000","2000","1000","5000","9000","7000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_detail);
        rec_contestDetails=findViewById(R.id.rec_contestDetails);
        iv_backMore=findViewById(R.id.iv_backMore);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView.LayoutManager vertical = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
        rec_contestDetails.setLayoutManager(vertical);
        rec_contestDetails.setItemAnimator(new DefaultItemAnimator());
        modelViewMores = new ArrayList<>();
        for (int i = 0; i < contestName.length; i++) {
            ModelContestDetail ab = new ModelContestDetail(contestName[i],entryFee[i]);
            modelViewMores.add(ab);
        }
        adapterViewMore = new AdapterContestDetails(modelViewMores,activity);
        rec_contestDetails.setAdapter(adapterViewMore);

    }
}