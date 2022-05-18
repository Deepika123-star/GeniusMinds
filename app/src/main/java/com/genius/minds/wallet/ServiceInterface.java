package com.genius.minds.wallet;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceInterface {

    @Multipart
    @POST("https://geniusbetting.in/geniusapi/new_hash.php")
    Call<String>getHashCall(
      @Part("key") RequestBody Key,
      @Part("txnid") RequestBody txnid,
      @Part("amount") RequestBody amount,
      @Part("productinfo") RequestBody productinfo,
      @Part("firstname") RequestBody firstname,
      @Part("email") RequestBody email
    );
}
