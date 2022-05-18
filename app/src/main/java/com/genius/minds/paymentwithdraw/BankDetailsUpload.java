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

import static com.genius.minds.Config.MyBaseUrl.BANK_DETAILS;

public class BankDetailsUpload extends AppCompatActivity {

    SessionManager session;

    EditText name,bankname,acnumber,ifsc;
    LinearLayout submit;
    private static Context mCtx;
    String  Name_Holder,Bank_Holder,Account_Holder,Ifsc_Holder,Type_Holder,Amount_Holder;
    String finalResult ;

    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String type,amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details_upload);
        initToolbar();
        name=(EditText)findViewById(R.id.name);
        bankname=(EditText)findViewById(R.id.bankname);
        acnumber=(EditText)findViewById(R.id.acnumber);
        ifsc=(EditText)findViewById(R.id.ifsc);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getApplicationContext());
        type=getIntent().getStringExtra("bank");
        amount=getIntent().getStringExtra("amount");
        submit=(LinearLayout)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){
                    BankDetailsFunction(Name_Holder,Bank_Holder,Account_Holder,Ifsc_Holder,Amount_Holder,Type_Holder);
                }
                else {
                    Toast.makeText(BankDetailsUpload.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void CheckEditTextIsEmptyOrNot(){

        Name_Holder = name.getText().toString();
        Bank_Holder = bankname.getText().toString();
        Account_Holder = acnumber.getText().toString();
        Ifsc_Holder=ifsc.getText().toString();
        Amount_Holder=amount;
        Type_Holder=type;

        if(TextUtils.isEmpty(Name_Holder) || TextUtils.isEmpty(Bank_Holder) || TextUtils.isEmpty(Account_Holder) || TextUtils.isEmpty(Ifsc_Holder))
        {
            CheckEditText = false;
        }
        else {
            CheckEditText = true ;
        }
    }
    public void BankDetailsFunction(final String name, final String bankname, final String acnumber, final String ifsc,final String amount,final String type){

        class BankDetailsClass extends AsyncTask<String,Void,String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(BankDetailsUpload.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(BankDetailsUpload.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(BankDetailsUpload.this, MainActivity.class);
                startActivity(intent);
            }
            @Override
            protected String doInBackground(String... params) {
                session = new SessionManager(getApplicationContext());
                User user = SharedPrefManager.getInstance(BankDetailsUpload.this).getUser();
                String useremail=user.getEmail();

                hashMap.put("name",params[0]);
                hashMap.put("bankname",params[1]);
                hashMap.put("acnumber",params[2]);
                hashMap.put("ifsc",params[3]);
                hashMap.put("amount",params[4]);
                hashMap.put("type",params[5]);
                finalResult = httpParse.postRequest(hashMap, BANK_DETAILS+useremail);
                return finalResult;
            }
        }

        BankDetailsClass bankDetailsClass = new BankDetailsClass();
        bankDetailsClass.execute(name,bankname,acnumber,ifsc,amount,type);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Bank Details");
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