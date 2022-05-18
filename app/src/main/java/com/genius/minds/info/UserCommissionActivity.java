package com.genius.minds.info;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Helper.Constaints;
import com.genius.minds.Model.ResponseUserCommissionDetails.CommissionDetailsItem;
import com.genius.minds.Model.ResponseUserCommissionDetails.ResponseUserCommissionDetail;
import com.genius.minds.R;
import com.genius.minds.info.Adapter.AdapterUserCommissionDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserCommissionActivity extends BaseActivity {
    Activity activity;
    ImageView iv_backMore;
   static RecyclerView rec_userCommission;
    static AdapterUserCommissionDetails adapterUserCommissionDetails;
    static ArrayList<CommissionDetailsItem> commissionDataItems = new ArrayList<>();
    SwipeRefreshLayout swiperefresh_Commission;
    String user_email,pageindex="0";
   static LinearLayout ll_userCommissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_commission);
        activity=UserCommissionActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        rec_userCommission=findViewById(R.id.rec_userCommission);
        swiperefresh_Commission=findViewById(R.id.swiperefresh_Commission);
        ll_userCommissions=findViewById(R.id.ll_userCommissions);
        int myColor = Color.parseColor("#E00101");
        swiperefresh_Commission.setColorSchemeColors(myColor);
        user_email=getIntent().getStringExtra("user_emails");
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyLoginSharedPref", Context.MODE_PRIVATE);
        Constaints.refer_code = sharedPreferences.getString("referal", "");
//        Toast.makeText(UserCommissionActivity.this, "refer code is "+ Constaints.refer_code, Toast.LENGTH_SHORT).show();



        swiperefresh_Commission.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override

                    public void run() {
                        swiperefresh_Commission.setRefreshing(false);
                        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){

                            UtilMethods.INSTANCE.userCommissionsDetails(activity, user_email,pageindex);

                        }else{
                            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
                        }
                    }
                },3000);
            }
        });


        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)) {
            UtilMethods.INSTANCE.userCommissionsDetails(activity, user_email,pageindex);

        } else {
            UtilMethods.INSTANCE.Error(activity, getResources().getString(R.string.NOCONN));

        }
    }

    public static void userCommissionDetails(Context context, String response) {

        Type type = new TypeToken<ResponseUserCommissionDetail>() {
        }.getType();
        ResponseUserCommissionDetail responseDashBoardList = new Gson().fromJson(response, type);

        commissionDataItems = (ArrayList<CommissionDetailsItem>) responseDashBoardList.getGeniusbetting().getCommissionDetails();

        if (commissionDataItems.isEmpty()) {

            rec_userCommission.setVisibility(View.GONE);
            ll_userCommissions.setVisibility(View.VISIBLE);

        } else {

            rec_userCommission.setVisibility(View.VISIBLE);

            ll_userCommissions.setVisibility(View.GONE);
        }
        if (commissionDataItems.size() > 0) {
            adapterUserCommissionDetails = new AdapterUserCommissionDetails(commissionDataItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rec_userCommission.setLayoutManager(horizontalLayoutManagaer1);
            rec_userCommission.setAdapter(adapterUserCommissionDetails);

        }
    }

}