package com.genius.minds.mycontest.complete.Activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.genius.minds.mycontest.complete.Adapter.AdapterViewMoreComplete;
import com.genius.minds.mycontest.complete.Model.ModelViewMoreComplete;

import java.util.ArrayList;

public class ViewMoreCompleteActivity extends BaseActivity {
    Activity activity;
    ImageView iv_backMore;
    RecyclerView rec_viewMoreComplete;
    AdapterViewMoreComplete adapterViewMore;
    ArrayList<ModelViewMoreComplete> modelViewMores;

    String [] serialNumber={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    String [] bettingName={"Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket"};
    String [] entryFee={"260","600","400","100","450","260","600","400","100","450","260","600","400","100","450"};
    String [] rate={"700","700","700","700","700","700","700","700","700","700","700","700","700","700","700"};
    String [] priceMoney={"2000","1000","5000","9000","7000","2000","1000","5000","9000","7000","2000","1000","5000","9000","7000"};
    String [] result={"win","loss","win","loss","win","tie","win","loss","win","tie","loss","win","tie","tie","win"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more_complete);
        activity=ViewMoreCompleteActivity.this;
        rec_viewMoreComplete=findViewById(R.id.rec_viewMoreComplete);
        iv_backMore=findViewById(R.id.iv_backMore);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView.LayoutManager vertical = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
        rec_viewMoreComplete.setLayoutManager(vertical);
        rec_viewMoreComplete.setItemAnimator(new DefaultItemAnimator());
        modelViewMores = new ArrayList<>();
        for (int i = 0; i < serialNumber.length; i++) {
            ModelViewMoreComplete ab = new ModelViewMoreComplete(serialNumber[i],bettingName[i],entryFee[i],rate[i],priceMoney[i],result[i]);
            modelViewMores.add(ab);
        }
        adapterViewMore = new AdapterViewMoreComplete(modelViewMores,activity);
        rec_viewMoreComplete.setAdapter(adapterViewMore);

    }
}