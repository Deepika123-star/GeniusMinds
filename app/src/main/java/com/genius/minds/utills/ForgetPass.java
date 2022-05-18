package com.genius.minds.utills;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;

import java.util.Random;

public class ForgetPass extends BaseActivity {
Activity activity;
    RelativeLayout rel1,rel2;
    EditText mobile,otpnumber;
    Button sendbtn,submit;
    String generatedPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        activity=ForgetPass.this;
        rel1=(RelativeLayout)findViewById(R.id.rel1);
        rel2=(RelativeLayout)findViewById(R.id.rel2);
        mobile=(EditText)findViewById(R.id.mobile);
        otpnumber=(EditText)findViewById(R.id.otpnumber);
        sendbtn=(Button) findViewById(R.id.sendbtn);
        submit=(Button)findViewById(R.id.submit);

        Random random = new Random();
//        generatedPassword = String.format("%04d", random.nextInt(10000));
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobile.getText().toString().trim().isEmpty()){
                    mobile.setError(getString( R.string.errootp));
                } else if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                    UtilMethods.INSTANCE.userForgetOtp(activity,
                            mobile.getText().toString());
                }else{
                    UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                }



            }
        });
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (generatedPassword.equals(otpnumber.getText().toString()))
//                {
//                    Intent intent = new Intent(ForgetPass.this,ChangePassword.class);
//                    intent.putExtra("mobile",mobile.getText().toString());
//                    startActivity(intent);
//                }
//                else{
//                    Toast.makeText(ForgetPass.this, "Otp not Matched ! Please enter the currect Otp number", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
//    public void SendOtp()
//    {
//        String message="Genius Betting \nForgot Password Otp is : \n"+generatedPassword;
//        String url="http://login.yourbulksms.com/api/sendhttp.php?authkey=16830AHVgFju1ae6001def0P15&mobiles=91\"+mobile.getText().toString()+\"&message=\"+message+\"&sender=WORIRS&route=4&country=91";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://login.yourbulksms.com/api/sendhttp.php?authkey=16830AHVgFju1ae6001def0P15&mobiles=91"+mobile.getText().toString()+"&message="+message+"&sender=WORIRS&route=4&country=91",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(ForgetPass.this, "Your Otp is send Successfully", Toast.LENGTH_LONG).show();
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//        Volley.newRequestQueue(this).add(stringRequest);
//    }
}
