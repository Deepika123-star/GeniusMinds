package com.genius.minds.mycontest;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
import com.genius.minds.mycontest.upcoming.Adapter.AdapterViewMore;
import com.genius.minds.mycontest.upcoming.Model.ModelViewMore;

import java.util.ArrayList;

public class ViewMoreActivity extends BaseActivity {
Activity activity;
ImageView iv_backMore;
RecyclerView rec_viewMore;
AdapterViewMore adapterViewMore;
ArrayList<ModelViewMore>modelViewMores;

    String [] serialNumber={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    String [] bettingName={"Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket","Cricket"};
    String [] entryFee={"260","600","400","100","450","260","600","400","100","450","260","600","400","100","450"};
    String [] rate={"700","700","700","700","700","700","700","700","700","700","700","700","700","700","700"};
    String [] priceMoney={"2000","1000","5000","9000","7000","2000","1000","5000","9000","7000","2000","1000","5000","9000","7000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more);
        activity=ViewMoreActivity.this;
        rec_viewMore=findViewById(R.id.rec_viewMore);
        iv_backMore=findViewById(R.id.iv_backMore);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView.LayoutManager vertical = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false);
        rec_viewMore.setLayoutManager(vertical);
        rec_viewMore.setItemAnimator(new DefaultItemAnimator());
        modelViewMores = new ArrayList<>();
        for (int i = 0; i < serialNumber.length; i++) {
            ModelViewMore ab = new ModelViewMore(serialNumber[i],bettingName[i],entryFee[i],rate[i],priceMoney[i]);
            modelViewMores.add(ab);
        }
        adapterViewMore = new AdapterViewMore(modelViewMores,activity);
        rec_viewMore.setAdapter(adapterViewMore);

    }

}