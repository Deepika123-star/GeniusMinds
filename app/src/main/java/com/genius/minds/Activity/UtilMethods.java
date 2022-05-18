package com.genius.minds.Activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.genius.minds.Activity.LiveBetting.LiveBettingActivity;
import com.genius.minds.Activity.LiveBetting.ShowMyBetActivity;
import com.genius.minds.Fragment.AddMoneyInWalletFragment;
import com.genius.minds.Fragment.JoinContestFragment;
import com.genius.minds.Fragment.UserRefferalCommissionFragment;
import com.genius.minds.Fragment.WinningAmountFragment;
import com.genius.minds.Fragment.WithDrawTransationHistoryFragment;
import com.genius.minds.MainActivity;
import com.genius.minds.Model.ResponseAddMoneys.ResponseAddMoney;
import com.genius.minds.Model.ResponseAllContests.ResponseAllContest;
import com.genius.minds.Model.ResponseCategory.ResponseCategories;
import com.genius.minds.Model.ResponseCompleteSubCategorys.ResponseCompleteSubCategory;
import com.genius.minds.Model.ResponseForgetPasswordOtps.ResponseForgetPasswordOtp;
import com.genius.minds.Model.ResponseForgetPasswords.ResponseForgetPassword;
import com.genius.minds.Model.ResponseGetAllMatchLiveContest.ResponseGetAllMatchLiveContests;
import com.genius.minds.Model.ResponseGetBetNames.ResponseGetBetName;
import com.genius.minds.Model.ResponseGetMatchScores.ResponseGetMatchScore;
import com.genius.minds.Model.ResponseGetUpis.ResponseGetUpi;
import com.genius.minds.Model.ResponseJoiContestsHistory.ResponseJoinContestHistory;
import com.genius.minds.Model.ResponseListMatchs.ResponseListMatch;
import com.genius.minds.Model.ResponseMyRefralLists.ResponseMyReferalList;
import com.genius.minds.Model.ResponseOtps.ResponseOtp;
import com.genius.minds.Model.ResponsePrizeBreakups.ResponsePrizeBreakUp;
import com.genius.minds.Model.ResponseRefferalCommissionsHistory.ResponseRefferalCommissionHistory;
import com.genius.minds.Model.ResponseShowMyBets.ResponseShowMyBet;
import com.genius.minds.Model.ResponseSignUps.ResponseSignup;
import com.genius.minds.Model.ResponseSubCategories.ResponseSubCategory;
import com.genius.minds.Model.ResponseTransation.ResponseTransationHistory;
import com.genius.minds.Model.ResponseUpcomingSubCategorys.ResponseUpComingSubCategory;
import com.genius.minds.Model.ResponseUserCommissionDetails.ResponseUserCommissionDetail;
import com.genius.minds.Model.ResponseUserCommissions.ResponseUserCommission;
import com.genius.minds.Model.ResponseWinnigHistorys.ResponseJoinWinningHistory;
import com.genius.minds.Model.ResponseWithDrawHistoryies.ResponseWithDrawHistory;
import com.genius.minds.Model.ResposeJoinLiveContests.ResponseJoinLiveContest;
import com.genius.minds.Network.API_Config;
import com.genius.minds.Network.ApiServices;
import com.genius.minds.Network.ResponseAMt;
import com.genius.minds.Utils.ApplicationConstant;
import com.genius.minds.Utils.CustomLoader;
import com.genius.minds.contest.MatchContest;
import com.genius.minds.contest.PrizeBreakUpTeamOneActivity;
import com.genius.minds.home.HomeFragment;
import com.genius.minds.info.MyRefferalsDetailsActivity;
import com.genius.minds.info.ReferFragment;
import com.genius.minds.info.UserCommissionActivity;
import com.genius.minds.matches.NewMatchActivity;
import com.genius.minds.mycontest.LedgerBoard;
import com.genius.minds.mycontest.complete.Activity.WinningAmountReferalCommissionActivity;
import com.genius.minds.mycontest.complete.CompleteCategory;
import com.genius.minds.mycontest.complete.CompleteSubCategory;
import com.genius.minds.mycontest.complete.Model.ResponseCompletewinningAmounts.ResponseCompleteWinningAmount;
import com.genius.minds.mycontest.live.Activity.LiveBatingContestActivity;
import com.genius.minds.mycontest.live.Activity.NewLiveMatchActivity;
import com.genius.minds.mycontest.live.LiveCategory;
import com.genius.minds.mycontest.live.LiveSubCategory;
import com.genius.minds.mycontest.upcoming.UpcomingCategory;
import com.genius.minds.mycontest.upcoming.UpcomingSubCategory;
import com.genius.minds.subcategory.BettingCategory;
import com.genius.minds.utills.ChangePassword;
import com.genius.minds.utills.OtpConfirm;
import com.genius.minds.utills.UserLoginActivity;
import com.genius.minds.wallet.BankScreenshot;
import com.genius.minds.wallet.WalletFragment;
import com.genius.minds.webview.WebViewActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public enum UtilMethods {
    INSTANCE;
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void Error(Context activity,String message) {

        /*final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_popup);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        Button btnOk = dialog.findViewById(R.id.btnOk);
        TextView tvAlert = dialog.findViewById(R.id.tvAlert);
        tvAlert.setText(message);


        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MainUserDetailsActivity.viewPager.setCurrentItem(1);
                dialog.dismiss();

            }
        });*/
    }
    public void setLoginPref(Context context, String message) {
        SharedPreferences sp = context.getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(ApplicationConstant.INSTANCE.LOGIN_RESPONSE, message).apply();
    }
    public String getLoginPref(Context context) {
        SharedPreferences sp = context.getSharedPreferences("MyPref", 0);
        return sp.getString(ApplicationConstant.INSTANCE.LOGIN_RESPONSE, "");
    }

    public JsonObject api_params(JSONObject jsonObj_) {

        JsonObject gsonObject = new JsonObject();
        JsonParser jsonParser = new JsonParser();
        gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        //print parameter
        Log.e("MY gson.JSON:  ", "AS PARAMETER  " + gsonObject);

        return gsonObject;
    }


    public void userReferalPage(Context context, int user_id) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "user_id", user_id);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseMyReferalList> call = service.getRreferralslist(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseMyReferalList>() {
            @Override
            public void onResponse(Call<ResponseMyReferalList> call, Response<ResponseMyReferalList> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getStatus()==1) {
                    Log.d("refralList", "is : " + new Gson().toJson(response.body()).toString());
                    MyRefferalsDetailsActivity.referalListData(context, new Gson().toJson(response.body()).toString());

                }else if (response.body().getStatus()==0){

                    Toast.makeText(context, "No data Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMyReferalList> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("refralList", "is : " + t.getMessage());

            }
        });
    }

    public void userCommission(Context context, String user_id, String refer_code) {

//        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
//        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "email", user_id);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseUserCommission> call = service.getUserCommission(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseUserCommission>() {
            @Override
            public void onResponse(Call<ResponseUserCommission> call, Response<ResponseUserCommission> response) {
//                if (customLoader != null) {
//                    if (customLoader.isShowing())
//                        customLoader.dismiss();
//                }
                if (response.body().getGeniusbetting().getResCode().equals("1")) {
                    Log.d("usercoommissiondata", "is : " + new Gson().toJson(response.body()).toString());
                    ReferFragment.userCommissionData(context, new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, "No data Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUserCommission> call, Throwable t) {
                try {
//                    if (customLoader != null) {
//                        if (customLoader.isShowing())
//                            customLoader.dismiss();
//                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("usercoommissiondata", "is : " + t.getMessage());

            }
        });
    }

    public void userCommissionsDetails(Context context, String user_email,String pageindex) {
        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "email", user_email);
            jsonObj_.put( "pageindex", "0");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseUserCommissionDetail> call = service.getUserCommissionDetails(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseUserCommissionDetail>() {
            @Override
            public void onResponse(Call<ResponseUserCommissionDetail> call, Response<ResponseUserCommissionDetail> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")) {
                    Log.d("usercommissiodetails", "is : " + new Gson().toJson(response.body()).toString());
                    UserCommissionActivity.userCommissionDetails(context, new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUserCommissionDetail> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("usercommissiodetails", "is : " + t.getMessage());

            }
        });
    }


    public void userTenderCategoryPage(Context context,String flag) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();

        Call<ResponseCategories> call = service.homeCategory();
        call.enqueue(new Callback<ResponseCategories>() {
            @Override
            public void onResponse(Call<ResponseCategories> call, Response<ResponseCategories> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){

                    if (flag.equals("1")) {

                        HomeFragment.categoryData(context, new Gson().toJson(response.body()).toString());

                    }
                    else if (flag.equals("2")){

                        UpcomingCategory.upcommingcategory(context, new Gson().toJson(response.body()).toString());

                    }
                    else if (flag.equals("3")){

                        LiveCategory.liveCategory(context, new Gson().toJson(response.body()).toString());

                    } else if (flag.equals("4")){

                        CompleteCategory.completeCategory(context, new Gson().toJson(response.body()).toString());

                    }
                    Log.e("categoryResponse", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }

            }




            @Override
            public void onFailure(Call<ResponseCategories> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();

                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("categoryResponse", "is : " + t.getMessage());

            }
        });
    }

    public void matchLists(Context context, String catName,String flag) {
        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "category_name", catName);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseListMatch> call = service.getMatchList(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseListMatch>() {
            @Override
            public void onResponse(Call<ResponseListMatch> call, Response<ResponseListMatch> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){

                    if (flag.equals("1")) {
                        NewMatchActivity.matchListData(context, new Gson().toJson(response.body()).toString());
                    }
                    else if (flag.equals("2")){
                        NewUpcomingMatchActivity.upcomingmatchlist(context, new Gson().toJson(response.body()).toString());

                    }
                    else if (flag.equals("3")){
                        NewLiveMatchActivity.livematchlist(context, new Gson().toJson(response.body()).toString());

                    }


                    Log.e("listMatch", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListMatch> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("listMatch", "is : " + t.getMessage());

            }
        });

    }


    public void matchCategory(Context context, String category, String matchCode, String type,String flag) {
        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "category_name", category);
            jsonObj_.put( "matchcode", matchCode);
            jsonObj_.put( "type", "1");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseSubCategory> call = service.getSubCategory(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseSubCategory>() {
            @Override
            public void onResponse(Call<ResponseSubCategory> call, Response<ResponseSubCategory> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){
                    if (flag.equals("1")) {
                        BettingCategory.matchListCatgory(context, new Gson().toJson(response.body()).toString());
                    }
                    else if (flag.equals("2")) {
//                        UpcomingSubCategory.upsubCatgory(context, new Gson().toJson(response.body()).toString());

                    }
                    else if (flag.equals("3")) {
                        LiveSubCategory.livesubCatgory(context, new Gson().toJson(response.body()).toString());

                    }
                    else if (flag.equals("4")) {
//                        CompleteSubCategory.completesubCatgory(context, new Gson().toJson(response.body()).toString());

                    }
                    Log.e("listsubcatMatch", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSubCategory> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("listsubcatMatch", "is : " + t.getMessage());

            }
        });

    }



    public void matchUpcomingSubCategory(Context context, String categoryName, String matchCode, String type, String email_address, String seriesName) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "category_name", categoryName);
            jsonObj_.put( "matchcode", matchCode);
            jsonObj_.put( "type", "1");
            jsonObj_.put( "email", email_address);
            jsonObj_.put( "series", seriesName);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseUpComingSubCategory> call = service.getUpComingSubCategory(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseUpComingSubCategory>() {
            @Override
            public void onResponse(Call<ResponseUpComingSubCategory> call, Response<ResponseUpComingSubCategory> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){

                    UpcomingSubCategory.upsubCatgory(context, new Gson().toJson(response.body()).toString());

                    Log.e("listsubcatMatch", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpComingSubCategory> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("listsubcatMatch", "is : " + t.getMessage());

            }
        });



    }


    public void matchCompleteSubCategory(Context context, String categoryName, String matchCode, String type, String email_address, String seriesName) {



        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {
            jsonObj_.put( "category_name", categoryName);
            jsonObj_.put( "matchcode", matchCode);
            jsonObj_.put( "type", "1");
            jsonObj_.put( "email", email_address);
            jsonObj_.put( "series", seriesName);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseCompleteSubCategory> call = service.getCompleteSubCategory(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseCompleteSubCategory>() {
            @Override
            public void onResponse(Call<ResponseCompleteSubCategory> call, Response<ResponseCompleteSubCategory> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){
                       CompleteSubCategory.completesubCatgory(context, new Gson().toJson(response.body()).toString());


                    Log.e("listsubcatMatch", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCompleteSubCategory> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("listsubcatMatch", "is : " + t.getMessage());

            }
        });




    }




    public void matchGetAllContest(Context context, String categoryName, String MatchCode,String subcategoryName, String series) {
        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "category_name", categoryName);
            jsonObj_.put( "matchcode", MatchCode);
            jsonObj_.put( "sub_cate", subcategoryName);
            jsonObj_.put( "series", series);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseAllContest> call = service.getAllContest(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseAllContest>() {
            @Override
            public void onResponse(Call<ResponseAllContest> call, Response<ResponseAllContest> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){
                    MatchContest.matchGetAllContest(context, new Gson().toJson(response.body()).toString());
                    Log.e("getAllContestlist", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseAllContest> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("getAllContestlist", "is : " + t.getMessage());

            }
        });


    }

    public void userRegister(Context context, String fullname, String phone, String email, String password, String referel) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "name", fullname );
            jsonObj_.put( "mobile", phone);
            jsonObj_.put( "email", email);
            jsonObj_.put( "password", password);
            jsonObj_.put( "referal", referel);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseSignup> call = service.userSignUPDetails(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseSignup>() {
            @Override
            public void onResponse(Call<ResponseSignup> call, Response<ResponseSignup> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    Log.e("RegisterResponse", "is : " + new Gson().toJson(response.body()).toString());
                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, OtpConfirm.class);
                    intent.putExtra("names",fullname);
                    intent.putExtra("phones",phone);
                    intent.putExtra("emails",email);
                    intent.putExtra("passwords",password);
                    intent.putExtra("referesl",referel);
                    intent.putExtra("otps",response.body().getGeniusbetting().getOtp());
                    context.startActivity(intent);
                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseSignup> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("RegisterResponse", "is : " + t.getMessage());

            }
        });
    }

    public void userOtp(Context context, String names, String phones, String emails, String passwords, String otps, String referesl, String new_otp) {
        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "name", names);
            jsonObj_.put( "mobile", phones);
            jsonObj_.put( "email", emails);
            jsonObj_.put( "password", passwords);
            jsonObj_.put( "referal", referesl);
            jsonObj_.put( "old_otp", otps);
            jsonObj_.put( "new_otp", new_otp);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseOtp> call = service.userOtp(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseOtp>() {
            @Override
            public void onResponse(Call<ResponseOtp> call, Response<ResponseOtp> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    Log.e("otpResponse", "is : " + new Gson().toJson(response.body()).toString());
                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, UserLoginActivity.class);
                    context.startActivity(intent);
                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseOtp> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("otpResponse", "is : " + t.getMessage());

            }
        });


    }

    public void userGetContest(Context context, String contestidd, String email_address) {


        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {
            jsonObj_.put("id",contestidd);
            jsonObj_.put("email",email_address);

        }catch (Exception e){
            e.getMessage();
        }

        Call<ResponseGetBetName> call = service.getBetName(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseGetBetName>() {
            @Override
            public void onResponse(Call<ResponseGetBetName> call, Response<ResponseGetBetName> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")) {
                    LedgerBoard.getBetName(context, new Gson().toJson(response.body()).toString());

//                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                    Log.e("getBetResponse", "is : " + new Gson().toJson(response.body()).toString());
                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();



                }


            }

            @Override
            public void onFailure(Call<ResponseGetBetName> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("getBetResponse", "is : " + t.getMessage());

            }
        });
    }

    public void addPayment(Context context, String user_id, String amount, String mode, String username,
                           String mobileNumber, String tdate, String encoding) {



        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "user_id", user_id);
            jsonObj_.put( "amount", amount);
            jsonObj_.put( "mode", mode);
            jsonObj_.put( "username", username);
            jsonObj_.put( "mobile", mobileNumber);
            jsonObj_.put( "tdate", tdate);
            jsonObj_.put( "screenshot", encoding);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseAddMoney> call = service.addMoney(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseAddMoney>() {
            @Override
            public void onResponse(Call<ResponseAddMoney> call, Response<ResponseAddMoney> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    Log.e("addmoneyResponse", "is : " + new Gson().toJson(response.body()).toString());
                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseAddMoney> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("addmoneyResponse", "is : " + t.getMessage());

            }
        });


    }

    public void historyAddMoneyLists(Context context, String email_address, String index) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "email", email_address);
            jsonObj_.put( "pageindex", "0");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseTransationHistory> call = service.transactionHistory(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseTransationHistory>() {
            @Override
            public void onResponse(Call<ResponseTransationHistory> call, Response<ResponseTransationHistory> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    Log.e("transationResponse", "is : " + new Gson().toJson(response.body()).toString());
                    AddMoneyInWalletFragment.getMoney(context, new Gson().toJson(response.body()).toString());

//                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseTransationHistory> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("transationResponse", "is : " + t.getMessage());

            }
        });

    }

    public void historyJoinContestLists(Context context, String email_address, String index) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "email", email_address);
            jsonObj_.put( "pageindex", "0");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseJoinContestHistory> call = service.joinContestHistory(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseJoinContestHistory>() {
            @Override
            public void onResponse(Call<ResponseJoinContestHistory> call, Response<ResponseJoinContestHistory> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
//                    Log.e("historyjoincontestresponse", "is : " + new Gson().toJson(response.body()).toString());
                    JoinContestFragment.getcontesthistory(context, new Gson().toJson(response.body()).toString());

//                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseJoinContestHistory> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Log.e("historyjoincontestresponse", "is : " + t.getMessage());

            }
        });

    }

    public void historyWinnigContestLists(Context context, String email_address, String index) {


        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "email", email_address);
            jsonObj_.put( "pageindex", "0");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseJoinWinningHistory> call = service.winningContestHistory(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseJoinWinningHistory>() {
            @Override
            public void onResponse(Call<ResponseJoinWinningHistory> call, Response<ResponseJoinWinningHistory> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
//                    Log.e("historyjoincontestresponse", "is : " + new Gson().toJson(response.body()).toString());
                    WinningAmountFragment.getWinninghistory(context, new Gson().toJson(response.body()).toString());

//                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseJoinWinningHistory> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Log.e("historyjoincontestresponse", "is : " + t.getMessage());

            }
        });



    }







    public void historyWithDraw(Context context, String email_address, String index) {


        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "email", email_address);
            jsonObj_.put( "pageindex", "0");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseWithDrawHistory> call = service.withDrawHistory(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseWithDrawHistory>() {
            @Override
            public void onResponse(Call<ResponseWithDrawHistory> call, Response<ResponseWithDrawHistory> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    WithDrawTransationHistoryFragment.getWithDrawHistory(context, new Gson().toJson(response.body()).toString());


                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseWithDrawHistory> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Log.e("historyjoincontestresponse", "is : " + t.getMessage());

            }
        });
    }

    public void userForgetOtp(Context context, String mobile) {



        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "mobile", mobile );

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseForgetPasswordOtp> call = service.getForgetOtp(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseForgetPasswordOtp>() {
            @Override
            public void onResponse(Call<ResponseForgetPasswordOtp> call, Response<ResponseForgetPasswordOtp> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    Log.e("forgetotpResponse", "is : " + new Gson().toJson(response.body()).toString());
                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

//                    Intent intent = new Intent(context, OtpConfirm.class);
                    Intent intent = new Intent(context, ChangePassword.class);
//                    intent.putExtra("phones",mobile);
                    intent.putExtra("phones",response.body().getGeniusbetting().getMobile());
                    context.startActivity(intent);
                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseForgetPasswordOtp> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("forgetotpResponse", "is : " + t.getMessage());

            }
        });
    }

    public void userForgetPassword(Context context, String phones, String password, String otp) {
        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "mobile", phones);
            jsonObj_.put( "password", password);
            jsonObj_.put( "otp", otp);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseForgetPassword> call = service.getForgetPassword(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseForgetPassword>() {
            @Override
            public void onResponse(Call<ResponseForgetPassword> call, Response<ResponseForgetPassword> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    Log.e("forgotpasswordResponse", "is : " + new Gson().toJson(response.body()).toString());
                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, UserLoginActivity.class);
                    context.startActivity(intent);
                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseForgetPassword> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("forgotpasswordResponse", "is : " + t.getMessage());

            }
        });


    }

    public void LeaderbordCommissionHistory(Context context, String email_address, String contestIds,String matchCode) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "email", email_address);
            jsonObj_.put( "contest_id", contestIds);
            jsonObj_.put( "matchcode", matchCode);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseCompleteWinningAmount> call = service.getCompleteWinningAmount(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseCompleteWinningAmount>() {
            @Override
            public void onResponse(Call<ResponseCompleteWinningAmount> call, Response<ResponseCompleteWinningAmount> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
//                    Log.e("historyjoincontestresponse", "is : " + new Gson().toJson(response.body()).toString());
                    WinningAmountReferalCommissionActivity.getLederBoardCommission(context, new Gson().toJson(response.body()).toString());

//                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseCompleteWinningAmount> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Log.e("historyjoincontestresponse", "is : " + t.getMessage());

            }
        });

    }

    public void userUpiData(Context context) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();

        Call<ResponseGetUpi> call = service.getUpiData();
        call.enqueue(new Callback<ResponseGetUpi>() {
            @Override
            public void onResponse(Call<ResponseGetUpi> call, Response<ResponseGetUpi> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){

                    BankScreenshot.getdataUpi(context, new Gson().toJson(response.body()).toString());

                    Log.e("categoryResponse", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }

            }




            @Override
            public void onFailure(Call<ResponseGetUpi> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();

                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("categoryResponse", "is : " + t.getMessage());

            }
        });

    }


    public void prizeBreakUP(Context context, String contestId, String email_address, String teams) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "contest_id", contestId);
//            jsonObj_.put( "email", email_address);
            jsonObj_.put( "selectedteam", teams);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponsePrizeBreakUp> call = service.getPrizeBreakUp(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponsePrizeBreakUp>() {
            @Override
            public void onResponse(Call<ResponsePrizeBreakUp> call, Response<ResponsePrizeBreakUp> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    Log.e("prizebrekupResponse", "is : " + new Gson().toJson(response.body()).toString());
                    PrizeBreakUpTeamOneActivity.getMoneyPrizeBreakup(context, new Gson().toJson(response.body()).toString());

//                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponsePrizeBreakUp> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("prizebrekupResponse", "is : " + t.getMessage());

            }
        });


    }

    public void historyRefferalWinHistory(Context context, String email_address, String index) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "email", email_address);
            jsonObj_.put( "pageindex", "0");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseRefferalCommissionHistory> call = service.getRefferalCommissionHistory(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseRefferalCommissionHistory>() {
            @Override
            public void onResponse(Call<ResponseRefferalCommissionHistory> call, Response<ResponseRefferalCommissionHistory> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
//                    Log.e("historyjoincontestresponse", "is : " + new Gson().toJson(response.body()).toString());
                    UserRefferalCommissionFragment.getRefferalhistory(context, new Gson().toJson(response.body()).toString());

//                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseRefferalCommissionHistory> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Log.e("historyjoincontestresponse", "is : " + t.getMessage());

            }
        });



    }

    public void userMatchScorePage(Context context, String matchCode,String flag) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "matchcode", matchCode);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseGetMatchScore> call = service.getMatchScores(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseGetMatchScore>() {
            @Override
            public void onResponse(Call<ResponseGetMatchScore> call, Response<ResponseGetMatchScore> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    if (flag.equals("1")) {

                        MatchContest.getRefferalhistoryScoreCard(context, new Gson().toJson(response.body()).toString());

                    }else if (flag.equals("2")){
                        LiveBettingActivity.getRefferalhistoryScoreCards(context, new Gson().toJson(response.body()).toString());

                    }
//                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseGetMatchScore> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Log.e("historyjoincontestresponse", "is : " + t.getMessage());

            }
        });


    }

    public void liveAllMatchLists(Context context, String matchCode, String matchSeries) {
        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
       // customLoader.show();
        if (customLoader != null) {
            if (customLoader.isShowing())
                customLoader.dismiss();
        }
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "matchcode", matchCode);
            jsonObj_.put( "series", matchSeries);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseGetAllMatchLiveContests> call = service.getLiveMatchList(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseGetAllMatchLiveContests>() {
            @Override
            public void onResponse(Call<ResponseGetAllMatchLiveContests> call, Response<ResponseGetAllMatchLiveContests> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){
                    LiveBettingActivity.allLiveMatchListData(context, new Gson().toJson(response.body()).toString());


                    Log.e("alllivelistMatch", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetAllMatchLiveContests> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("alllivelistMatch", "is : " + t.getMessage());

            }
        });


    }

    public void liveAllMatchListss(Context context, String matchcode, String rate,String amount,
                                   String id, String email_address, String selectedteam) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
       // customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "matchcode", matchcode);
            jsonObj_.put( "rate", rate);
            jsonObj_.put( "amount", amount);
            jsonObj_.put( "contestid", id);
            jsonObj_.put( "email", email_address);
            jsonObj_.put( "selectedteam", selectedteam);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseJoinLiveContest> call = service.getJoinLivContest(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseJoinLiveContest>() {
            @Override
            public void onResponse(Call<ResponseJoinLiveContest> call, Response<ResponseJoinLiveContest> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1")) {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                    Log.e("livejoinResponse", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseJoinLiveContest> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("livejoinResponse", "is : " + t.getMessage());

            }
        });

    }

    public void liveshowBetList(Context context, String email_address, String matchCode, String contestId) {


        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "email", email_address);
            jsonObj_.put( "matchcode", matchCode);
            jsonObj_.put( "contest_id", contestId);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseShowMyBet> call = service.getShowMyBet(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseShowMyBet>() {
            @Override
            public void onResponse(Call<ResponseShowMyBet> call, Response<ResponseShowMyBet> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){
                    ShowMyBetActivity.showMyBetList(context, new Gson().toJson(response.body()).toString());

                    Log.e("showbetresponse", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseShowMyBet> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("showbetresponse", "is : " + t.getMessage());

            }
        });


    }



    public void LiveBatingContestMatchLists(Context context, String matchCode, String matchSeries) {
        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "matchcode", matchCode);
            jsonObj_.put( "series", matchSeries);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseGetAllMatchLiveContests> call = service.getLiveMatchList(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseGetAllMatchLiveContests>() {
            @Override
            public void onResponse(Call<ResponseGetAllMatchLiveContests> call, Response<ResponseGetAllMatchLiveContests> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }
                if (response.body().getGeniusbetting().getResCode().equals("1")){
                  //  LiveBatingContestActivity.allLiveMatchListData(context, new Gson().toJson(response.body()).toString());


                    Log.e("alllivelistMatch", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetAllMatchLiveContests> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("alllivelistMatch", "is : " + t.getMessage());

            }
        });


    }
    public void userBatingContestMatchScorePage(Context context, String matchCode,String flag) {

        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {


            jsonObj_.put( "matchcode", matchCode);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseGetMatchScore> call = service.getMatchScores(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseGetMatchScore>() {
            @Override
            public void onResponse(Call<ResponseGetMatchScore> call, Response<ResponseGetMatchScore> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                }

                if (response.body().getGeniusbetting().getResCode().equals("1") ) {
                    if (flag.equals("1")) {

                        MatchContest.getRefferalhistoryScoreCard(context, new Gson().toJson(response.body()).toString());

                    }else if (flag.equals("2")){
                       // LiveBatingContestActivity.getRefferalhistoryScoreCards(context, new Gson().toJson(response.body()).toString());

                    }
//                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(context, "" + response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<ResponseGetMatchScore> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        //UtilMethods.INSTANCE.Error(context, context.getString(R.string.error_message));
                        Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Log.e("historyjoincontestresponse", "is : " + t.getMessage());

            }
        });


    }
    ////////////
    public void paymtList(Context context, String client_txn_id) {


        final CustomLoader customLoader = new CustomLoader(context, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        ApiServices service = API_Config.getApiClient_ByPost();
        JSONObject jsonObj_ = new JSONObject();

        try {

            jsonObj_.put( "client_txn_id", client_txn_id);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseAMt> call = service.checkStatus(UtilMethods.INSTANCE.api_params(jsonObj_));
        call.enqueue(new Callback<ResponseAMt>() {
            @Override
            public void onResponse(Call<ResponseAMt> call, Response<ResponseAMt> response) {
                if (customLoader != null) {
                    if (customLoader.isShowing())
                        customLoader.dismiss();
                    if (response.body().getGeniusbetting().getResCode().equalsIgnoreCase("1")) {
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText("Payment Details").setContentText(response.body().getGeniusbetting().getMessage()).setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // Showing simple toast message to user
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                                sDialog.dismissWithAnimation();
                            }
                        }).show();
                    }
                    else {
                        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Payment Details").setContentText(response.body().getGeniusbetting().getMessage()).setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                // Showing simple toast message to user
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                                sDialog.dismissWithAnimation();
                            }
                        }).show();
                    }
                }
               /* if (response.body().getGeniusbetting().getStatus().equals(1)){
                   // ShowMyBetActivity.showMyBetList(context, new Gson().toJson(response.body()).toString());
                    WebViewActivity.pmt(context, new Gson().toJson(response.body().toString()));
                    Log.e("pmt-----", "is : " + new Gson().toJson(response.body()).toString());

                }else {

                    Toast.makeText(context, ""+response.body().getGeniusbetting().getResMsg(), Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onFailure(Call<ResponseAMt> call, Throwable t) {
                try {
                    if (customLoader != null) {
                        if (customLoader.isShowing())
                            customLoader.dismiss();
                        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Payment Details").setContentText(t.getMessage()).setConfirmText("Ok").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {@Override
                        public void onClick(SweetAlertDialog sDialog) {
                            // Showing simple toast message to user
                           Intent intent=new Intent(context,MainActivity.class);
                           context.startActivity(intent);
                            sDialog.dismissWithAnimation();
                        }
                        }).show();
                        //Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.e("showbetresponse", "is : " + t.getMessage());

            }
        });


    }


}
