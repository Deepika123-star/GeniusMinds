package com.genius.minds.paymentwithdraw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.genius.minds.HelperClass.HttpParse;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.MainActivity;
import com.genius.minds.R;

import java.util.HashMap;

import static com.genius.minds.Config.MyBaseUrl.MOBILE_DETAILS;

public class MobilePayment extends AppCompatActivity {

    SessionManager session;

    EditText name,upino;
    LinearLayout submit;
    private static Context mCtx;
    String  Name_Holder,Upi_Holder,Type_Holder,Amount_Holder;
    String finalResult ;
    //TextView typetext;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String type,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_payment);
        initToolbar();
        name=(EditText)findViewById(R.id.name);

        upino=(EditText)findViewById(R.id.upino);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getApplicationContext());
        type=getIntent().getStringExtra("type");
       // typetext=(TextView)findViewById(R.id.typetext);
        amount=getIntent().getStringExtra("amount");
        //typetext.setText(type);
        submit=(LinearLayout)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){
                    BankDetailsFunction(Name_Holder,Upi_Holder,Type_Holder,Amount_Holder);
                }
                else {
                    Toast.makeText(MobilePayment.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void CheckEditTextIsEmptyOrNot(){

        Name_Holder = name.getText().toString();
        Upi_Holder = upino.getText().toString();
        Amount_Holder=amount;
        Type_Holder=type;

        if(TextUtils.isEmpty(Name_Holder) || TextUtils.isEmpty(Upi_Holder))
        {
            CheckEditText = false;
        }
        else {
            CheckEditText = true ;
        }
    }
    public void BankDetailsFunction(final String name, final String upino,final String type,final String amount){

        class BankDetailsClass extends AsyncTask<String,Void,String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(MobilePayment.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(MobilePayment.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MobilePayment.this, MainActivity.class);
                startActivity(intent);
            }
            @Override
            protected String doInBackground(String... params) {
                session = new SessionManager(getApplicationContext());
                User user = SharedPrefManager.getInstance(MobilePayment.this).getUser();
                String useremail=user.getEmail();

                hashMap.put("name",params[0]);
                hashMap.put("mobile",params[1]);
                hashMap.put("type",params[2]);
                hashMap.put("amount",params[3]);

                finalResult = httpParse.postRequest(hashMap, MOBILE_DETAILS+useremail);
                Log.d("Url",MOBILE_DETAILS+useremail);
                return finalResult;
            }
        }

        BankDetailsClass bankDetailsClass = new BankDetailsClass();

        bankDetailsClass.execute(name,upino,type,amount);
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Payment Details");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}