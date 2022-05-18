package com.genius.minds.Inerfaces;

import com.genius.minds.wallet.InsertOrder;
import com.genius.minds.wallet.OrderResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {
    String BASE_URL = "https://pragatisoulutions.com/geniusbetting/admin/api/";
    String FEED="posts";

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)

            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("CreateOrder")
    Call<OrderResponse> CreateOrder(@Body InsertOrder insertsignup);
}
