package com.genius.minds.wallet;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.HelperClass.HttpParse;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Inerfaces.ApiServices;
import com.genius.minds.R;
import com.genius.minds.Utils.CustomLoader;
import com.genius.minds.webview.WebViewActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.genius.minds.Config.MyBaseUrl.PAYMENT_URL;
import static com.genius.minds.Config.MyBaseUrl.USER_AMOUNT;

import retrofit2.Call;
import retrofit2.Callback;

public class WalletFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG ="genius" ;
    SessionManager session;
    TextView bonus,balance,deposit,tv_Transaction;
    String FinalAmountToAdd;
    TextView tv_OneHundred,tv_TwoHundred,tv_FiveHundred,tv_AddCash,tv_tenHundred,tv_Addonline;
    EditText et_AddCashAmount;
    LinearLayout withdraw;
    private String orderID = "";
    String finalResult ;
    private String PayAmount;
    int finalamount;
    String useremail,mobile,username;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String email;
    SwipeRefreshLayout mSwipeRefreshLayout ;
    ApiServices apiServices;
//    private CheckoutAPIClient mCheckoutAPIClient;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wallet, container, false);
        SharedPreferences pref = this.getActivity().getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this.getActivity());
        User user = SharedPrefManager.getInstance(this.getActivity()).getUser();
        email=user.getEmail();
        balance=(TextView)v.findViewById(R.id.totalamount);
        deposit=(TextView)v.findViewById(R.id.deposit);
        bonus=(TextView)v.findViewById(R.id.bonus);
        tv_Addonline=(TextView)v.findViewById(R.id.tv_Addonline);
        getData(email);
        useremail=user.getEmail();
        mobile=user.getMobile();
        username=user.getUsername();
        et_AddCashAmount=(EditText)v.findViewById(R.id.et_AddCashEnterAmount);
        tv_OneHundred=(TextView)v.findViewById(R.id.tv_OneHundred);
        tv_TwoHundred=(TextView)v.findViewById(R.id.tv_TwoHundred);
        tv_FiveHundred=(TextView)v.findViewById(R.id.tv_FiveHundred);
        tv_tenHundred=(TextView)v.findViewById(R.id.tv_tenHundred);
        tv_AddCash=(TextView)v.findViewById(R.id.tv_AddCash);
        tv_Transaction=(TextView)v.findViewById(R.id.tv_Transaction);
        withdraw=(LinearLayout)v.findViewById(R.id.withdraw);
        apiServices = ApiServices.retrofit.create(ApiServices.class);
        mSwipeRefreshLayout =(SwipeRefreshLayout)v.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                getData(email);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        tv_Addonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_AddCashAmount.getText().toString().equals(""))
                {
                    et_AddCashAmount.setError("Enter Amount");
                }
                else{
                    InsertOrder id = new InsertOrder(et_AddCashAmount.getText().toString(),username,email,mobile);
                    Log.d("wallet amt----",username+email+mobile);
                    Call<OrderResponse> call = apiServices.CreateOrder(id);
                    final CustomLoader customLoader = new CustomLoader(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                    customLoader.show();
                    call.enqueue(new Callback<OrderResponse>() {
                        @Override
                        public void onResponse(Call<OrderResponse> call, retrofit2.Response<OrderResponse> response) {
                            if (response.isSuccessful()) {
                                String strResponse = new Gson().toJson(response.body());
                                customLoader.dismiss();
                                Log.d("tes===", strResponse);
                                try {
                                    JSONObject jsonObject = new JSONObject(strResponse);
                                    JSONObject jsonObject1 = jsonObject.getJSONObject("geniusbetting");
                                    Log.d("jso", jsonObject1.getString("payment_url"));
                                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                                    String otp = jsonObject1.getString("payment_url");
                                   // Toast.makeText(getActivity(), ""+otp, Toast.LENGTH_SHORT).show();
                                    intent.putExtra("urlpmt",otp);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }




                               /* try {
                                    JSONObject jsonObject = new JSONObject(strResponse);
                                    JSONObject jsonObject1 = jsonObject.getJSONObject("geniusbetting");
                                    Log.d("jso", jsonObject1.getString("otp"));
                                  *//*  Intent intent = new Intent(SignUpActivity.this, VirifyOtpActivity.class);
                                    String otp = jsonObject1.getString("otp");
                                    String user_id = jsonObject1.getString("user_id");
                                    Toast.makeText(SignUpActivity.this, ""+otp, Toast.LENGTH_SHORT).show();
                                    intent.putExtra("otp",otp);
                                    intent.putExtra("user_id",user_id);
                                    startActivity(intent);*//*
                                    // setCategory(list);

                                } catch (Exception e) {
                                    Toast.makeText(getActivity(), "756757677", Toast.LENGTH_SHORT).show();
                                }*/
                            } else {
                           /* Intent intent=  new Intent(SignUpActivity.this,VirifyOtpActivity.class);
                            //intent.putExtra("otp",list.get(0).getOtp());
                            //intent.putExtra("user_id",list.get(0).getUserId());
                            startActivity(intent);*/
                                customLoader.dismiss();
                                Toast.makeText(getActivity(), "Something went wrong... ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<OrderResponse> call, Throwable t) {
                            customLoader.dismiss();
                        }
                    });
                   /* finalamount=Integer.parseInt(et_AddCashAmount.getText().toString());
                    Intent intent=new Intent(getActivity(), StartPaymentActivity.class);
                    intent.putExtra("phone",mobile);
                    intent.putExtra("email",email);
                    intent.putExtra("amount",et_AddCashAmount.getText().toString());
                    startActivity(intent);*/
                }
            }
        });
        tv_AddCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_AddCashAmount.getText().toString().equals(""))
                {
                    et_AddCashAmount.setError("Enter Amount");
                }
                else{
                    finalamount=Integer.parseInt(et_AddCashAmount.getText().toString());
                    Intent intent=new Intent(getActivity(), StartPaymentActivity.class);
                    intent.putExtra("phone",mobile);
                    intent.putExtra("email",email);
                    intent.putExtra("amount",et_AddCashAmount.getText().toString());
                    startActivity(intent);
                }

            }
        });
        tv_OneHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_AddCashAmount.setText(getResources().getString(R.string.hundered));
            }
        });
        tv_TwoHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_AddCashAmount.setText(getResources().getString(R.string.two_hundred));
            }
        });
        tv_FiveHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_AddCashAmount.setText(getResources().getString(R.string.five_hundred));
            }
        });
        tv_tenHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_AddCashAmount.setText(getResources().getString(R.string.ten_hundred));
            }
        });
        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Withdraw.class);
                startActivity(i);
            }
        });
        tv_Transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Transaction.class);
                startActivity(i);
            }
        });

        return v;
    }
    @Override
    public void onRefresh() {
        // Fetching data from server
        getData(email);
    }


    public void InsertData(final String uemail, final String prize,final String razorpayPaymentID){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(getActivity(),"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("prize",params[1]);
                hashMap.put("paymentid",params[2]);

                finalResult = httpParse.postRequest(hashMap, PAYMENT_URL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(uemail,prize,razorpayPaymentID);
    }












    private void getData(String email){

        final ProgressDialog loading = ProgressDialog.show(this.getActivity(), "Please wait...","Loading Wallet Details...",false,false);
        Log.d("emailofuser",email);
        // String email="admin@gmail.com";
        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(USER_AMOUNT+email,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        loading.dismiss();
                        mSwipeRefreshLayout.setRefreshing(false);
                        showData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }
    private void showData(JSONArray jsonArray){

        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
                String depositamount=obj.getString("deposit");
                String bonusamount=obj.getString("bonus");
                Double totalbalance=Double.parseDouble(depositamount)+Double.parseDouble(bonusamount);
                deposit.setText("\u20B9 "+depositamount);
                bonus.setText("\u20B9 "+bonusamount);
                balance.setText("\u20B9 "+totalbalance);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}