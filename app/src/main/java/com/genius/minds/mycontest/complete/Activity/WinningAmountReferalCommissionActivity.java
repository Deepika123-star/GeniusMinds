package com.genius.minds.mycontest.complete.Activity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.R;
import com.genius.minds.mycontest.complete.Model.ResponseCompletewinningAmounts.ResponseCompleteWinningAmount;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class WinningAmountReferalCommissionActivity extends BaseActivity {
Activity activity;
ImageView iv_winning;
static RecyclerView rec_winningCategory;
   static TextView tv_ReferNameWinning,tv_mobileNumberWinning,tv_EntryFeeWinning,tv_WinningAmountReferalCommssion;

    SessionManager session;
    String contestIds,matchCode,email_Ids;

SwipeRefreshLayout swiperefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_amount_referal_commission);
        activity=WinningAmountReferalCommissionActivity.this;
        iv_winning=findViewById(R.id.iv_winning);
        tv_ReferNameWinning=findViewById(R.id.tv_ReferNameWinning);
        tv_mobileNumberWinning=findViewById(R.id.tv_mobileNumberWinning);
        tv_EntryFeeWinning=findViewById(R.id.tv_EntryFeeWinning);
        tv_WinningAmountReferalCommssion=findViewById(R.id.tv_WinningAmountReferalCommssion);
        swiperefresh=findViewById(R.id.swiperefresh);


         contestIds=getIntent().getStringExtra("contest_ids");
         email_Ids=getIntent().getStringExtra("email_idUser");
         matchCode=getIntent().getStringExtra("matchcode");

        SharedPreferences pref = activity.getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(activity);
        User user = SharedPrefManager.getInstance(activity).getUser();
        String  Email_address=user.getEmail();

//        Toast.makeText(activity, ""+ email_Ids, Toast.LENGTH_SHORT).show();

        swiperefresh.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.LeaderbordCommissionHistory(activity,email_Ids,contestIds,matchCode);

        }else{
            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
        }



        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override

                    public void run() {
                        swiperefresh.setRefreshing(false);
                        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                            UtilMethods.INSTANCE.LeaderbordCommissionHistory(activity,email_Ids,contestIds,matchCode);

                        }else{
                            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
                        }
                    }
                },3000);
            }
        });


    }




    public static void getLederBoardCommission(Context context, String response) {

        Type type = new TypeToken<ResponseCompleteWinningAmount>() {}.getType();
        ResponseCompleteWinningAmount responseCompleteWinningAmount = new Gson().fromJson(response, type);
        tv_ReferNameWinning.setText(responseCompleteWinningAmount.getGeniusbetting().getReferralName());
        tv_mobileNumberWinning.setText(responseCompleteWinningAmount.getGeniusbetting().getReferralNumber());
        tv_EntryFeeWinning.setText("\u20b9 "+responseCompleteWinningAmount.getGeniusbetting().getEntryFeeRefCommission());
        tv_WinningAmountReferalCommssion.setText("\u20b9 "+responseCompleteWinningAmount.getGeniusbetting().getWinningAmountRefCommission());
    }

}