package com.genius.minds.contest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponsePrizeBreakups.ResponsePrizeBreakUp;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class PrizeBreakUpTeamOneActivity extends BaseActivity {
Activity activity;
ImageView iv_backMore;
static TextView tv_userWinningAmount,tv_EntryFeeReferalCommission,tv_WinningAmountReferalCommssions;
String contestId,teams;

    SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize_break_up_team_one);
        activity=PrizeBreakUpTeamOneActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        tv_userWinningAmount=findViewById(R.id.tv_userWinningAmount);
        tv_EntryFeeReferalCommission=findViewById(R.id.tv_EntryFeeReferalCommission);
        tv_WinningAmountReferalCommssions=findViewById(R.id.tv_WinningAmountReferalCommssions);


        contestId=getIntent().getStringExtra("contestid");
        teams=getIntent().getStringExtra("team");

        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        SharedPreferences pref = activity.getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(activity);
        User user = SharedPrefManager.getInstance(activity).getUser();
        String  Email_address=user.getEmail();



        if (UtilMethods.INSTANCE.isNetworkAvailable(context)){
            UtilMethods.INSTANCE.prizeBreakUP(context,contestId,Email_address,teams);

        }else{
            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
        }
    }


    public static void getMoneyPrizeBreakup(Context context, String response) {
        Type type = new TypeToken<ResponsePrizeBreakUp>() {}.getType();
        ResponsePrizeBreakUp responseCompleteWinningAmount = new Gson().fromJson(response, type);
        tv_userWinningAmount.setText("\u20B9 "+responseCompleteWinningAmount.getGeniusbetting().getUserWinningAmount());
        tv_EntryFeeReferalCommission.setText("\u20B9 "+responseCompleteWinningAmount.getGeniusbetting().getEntryFeeCommission());
        tv_WinningAmountReferalCommssions.setText("\u20B9 "+responseCompleteWinningAmount.getGeniusbetting().getEntryFeeReferralCommission());


    }

}