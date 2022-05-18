package com.genius.minds.Network;

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
import com.genius.minds.info.Modeles.ResponseCommissionAmounts.ResponseCommissionAmount;
import com.genius.minds.mycontest.complete.Model.ResponseCompletewinningAmounts.ResponseCompleteWinningAmount;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("api/Registration")
    Call<ResponseSignup> userSignUPDetails(@Body JsonObject jsonBody);

  @POST("api/VerifyOTP")
    Call<ResponseOtp> userOtp(@Body JsonObject jsonBody);

    @POST("api/Api/get_referralslist")
    Call<ResponseMyReferalList> getRreferralslist(@Body JsonObject jsonBody);

    @POST("api/GetAllReferralAmount")
    Call<ResponseUserCommission> getUserCommission(@Body JsonObject jsonBody);

    @POST("api/UserCommissionDetails")
    Call<ResponseUserCommissionDetail> getUserCommissionDetails(@Body JsonObject jsonBody);

  @POST("api/Api/get_commission_amount")
    Call<ResponseCommissionAmount> getUserCommissionAmont(@Body JsonObject jsonBody);

    @GET("api/GetAllCategory")
    Call<ResponseCategories> homeCategory();

    @POST("api/GetAllMatch")
    Call<ResponseListMatch> getMatchList(@Body JsonObject jsonBody);

    @POST("api/GetAllMatchContest")
    Call<ResponseGetAllMatchLiveContests> getLiveMatchList(@Body JsonObject jsonBody);

  /*  @POST("api/GetAllSubcategory")
    Call<ResponseSubCategory> getSubCategory(@Body JsonObject jsonBody); */

    @POST("api/GetAllSubcategory")
    Call<ResponseSubCategory> getSubCategory(@Body JsonObject jsonBody);

   @POST("api/GetAllUpcommingSubcategory")
    Call<ResponseUpComingSubCategory> getUpComingSubCategory(@Body JsonObject jsonBody);

 @POST("api/GetAllCompleteSubcategory")
    Call<ResponseCompleteSubCategory> getCompleteSubCategory(@Body JsonObject jsonBody);


    @POST("api/GetAllContest")
    Call<ResponseAllContest> getAllContest(@Body JsonObject jsonBody);


  @POST("api/GetMatchScoreCard")
    Call<ResponseGetMatchScore> getMatchScores(@Body JsonObject jsonBody);

   @POST("api/GetBetName")
    Call<ResponseGetBetName> getBetName(@Body JsonObject jsonBody);

 @POST("api/AddMoney")
    Call<ResponseAddMoney> addMoney(@Body JsonObject jsonBody);

    @POST("api/AddWalletHistory")
    Call<ResponseTransationHistory> transactionHistory(@Body JsonObject jsonBody);

    @POST("api/RefferWinHistory")
    Call<ResponseRefferalCommissionHistory> getRefferalCommissionHistory(@Body JsonObject jsonBody);


 @POST("api/JoinContestHistory")
    Call<ResponseJoinContestHistory> joinContestHistory(@Body JsonObject jsonBody);

  @POST("api/ContestWinHistory")
    Call<ResponseJoinWinningHistory> winningContestHistory(@Body JsonObject jsonBody);

 @POST("api/GetWidhraHistory")
    Call<ResponseWithDrawHistory> withDrawHistory(@Body JsonObject jsonBody);


  @POST("api/ForgotPasswordOtp")
    Call<ResponseForgetPasswordOtp> getForgetOtp(@Body JsonObject jsonBody);

   @POST("api/ForgotPassword")
    Call<ResponseForgetPassword> getForgetPassword(@Body JsonObject jsonBody);

  @POST("api/LeaderbordCommissionHistory")
    Call<ResponseCompleteWinningAmount> getCompleteWinningAmount(@Body JsonObject jsonBody);

    @GET("api/GetUpiData")
    Call<ResponseGetUpi> getUpiData();

    @POST("api/PrizeBreakUP")
    Call<ResponsePrizeBreakUp> getPrizeBreakUp(@Body JsonObject jsonBody);

  @POST("api/JoinContest")
    Call<ResponseJoinLiveContest> getJoinLivContest(@Body JsonObject jsonBody);

    @POST("api/ShowMyBet")
    Call<ResponseShowMyBet> getShowMyBet(@Body JsonObject jsonBody);

    @POST("api/CheckStatus")
    Call<ResponseAMt> checkStatus(@Body JsonObject jsonBody);

}
