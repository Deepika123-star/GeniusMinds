package com.genius.minds.wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.R;
import com.genius.minds.paymentwithdraw.BankDetailsUpload;
import com.genius.minds.paymentwithdraw.MobilePayment;
import com.genius.minds.paymentwithdraw.UpiPayment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.genius.minds.Config.MyBaseUrl.USER_AMOUNT;

public class Withdraw extends AppCompatActivity {
    SessionManager session;
    TextView winningamount;
    LinearLayout bank,upi,gpay,phonepay,paytm;
    EditText amount2,amount;
    static String useremail,winning;
    float winamount,withamount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        initToolbar();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("geniusmindspref", 0);
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getApplicationContext());
        User user = SharedPrefManager.getInstance(this).getUser();
        useremail=user.getEmail();
        getData();
        winningamount=(TextView)findViewById(R.id.winningamount);
        amount2=(EditText)findViewById(R.id.amount);
        bank=(LinearLayout)findViewById(R.id.bank);
        upi=(LinearLayout)findViewById(R.id.upi);
        gpay=(LinearLayout)findViewById(R.id.gpay);
        phonepay=(LinearLayout)findViewById(R.id.phonepay);
        paytm=(LinearLayout)findViewById(R.id.paytm);
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = (EditText) findViewById(R.id.amount);
                String text=amount.getText().toString().trim();
                if (TextUtils.isEmpty(text)){
                    Toast.makeText(Withdraw.this, "Enter amount", Toast.LENGTH_LONG).show();
                }else {
                    winamount = Float.parseFloat(winningamount.getText().toString());
                    withamount = Float.parseFloat(amount.getText().toString());
                    if (withamount < 100.00) {
                        Toast.makeText(Withdraw.this, "You can withdrawl minimum 100 rs ",
                                Toast.LENGTH_LONG).show();
                    } else {
                        if (winamount >= withamount) {
                            Intent intent = new Intent(Withdraw.this, BankDetailsUpload.class);
                            intent.putExtra("amount", amount2.getText().toString());
                            intent.putExtra("bank", "Bank Transfer");
                            startActivity(intent);
                        } else {
                            Toast.makeText(Withdraw.this, "Please enter a valid amount.your amount is greater then your winning balance",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }

            }
        });
        upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = (EditText) findViewById(R.id.amount);
                String text = amount.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(Withdraw.this, "Enter amount",
                            Toast.LENGTH_LONG).show();
                } else {
                    winamount = Float.parseFloat(winningamount.getText().toString());
                    withamount = Float.parseFloat(amount.getText().toString());
                    if (withamount < 100.00) {
                        Toast.makeText(Withdraw.this, "You can withdrawl minimul 100 rs ",
                                Toast.LENGTH_LONG).show();
                    } else {
                        if (winamount >= withamount) {
                            Intent intent = new Intent(Withdraw.this, UpiPayment.class);
                            intent.putExtra("amount", amount2.getText().toString());
                            intent.putExtra("upi", "UPI Transfer");
                            startActivity(intent);
                        } else {
                            Toast.makeText(Withdraw.this, "Please enter a valid amount.your amount is greater then your winning balance",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        gpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = (EditText) findViewById(R.id.amount);
                amount = (EditText) findViewById(R.id.amount);
                String text = amount.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    Toast.makeText(Withdraw.this, "Enter amount", Toast.LENGTH_LONG).show();
                } else {
                    winamount = Float.parseFloat(winningamount.getText().toString());
                    withamount = Float.parseFloat(amount.getText().toString());
                    if (withamount < 100.00) {
                        Toast.makeText(Withdraw.this, "You can withdrawl minimul 100 rs ", Toast.LENGTH_LONG).show();
                    } else {
                        if (winamount >= withamount) {
                            Intent intent = new Intent(Withdraw.this, MobilePayment.class);
                            intent.putExtra("amount", amount2.getText().toString());
                            intent.putExtra("type", "Google Pay");
                            startActivity(intent);
                        } else {
                            Toast.makeText(Withdraw.this, "Please enter a valid amount.your amount is greater then your winning balance",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = (EditText) findViewById(R.id.amount);
                amount = (EditText) findViewById(R.id.amount);
                String text=amount.getText().toString().trim();
                if (TextUtils.isEmpty(text)){
                    Toast.makeText(Withdraw.this, "Enter amount",
                            Toast.LENGTH_LONG).show();
                }else {
                    winamount = Float.parseFloat(winningamount.getText().toString());
                    withamount = Float.parseFloat(amount.getText().toString());
                    if (withamount < 100) {
                        Toast.makeText(Withdraw.this, "You can withdrawl minimul 100 rs ",
                                Toast.LENGTH_LONG).show();
                    } else {
                        if (winamount >= withamount) {
                            Intent intent = new Intent(Withdraw.this, MobilePayment.class);
                            intent.putExtra("amount", amount2.getText().toString());
                            intent.putExtra("type", "Paytm");
                            startActivity(intent);
                        } else {
                            Toast.makeText(Withdraw.this, "Please enter a valid amount.your amount is greater then your winning balance",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        phonepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = (EditText) findViewById(R.id.amount);
                amount = (EditText) findViewById(R.id.amount);
                String text=amount.getText().toString().trim();
                if (TextUtils.isEmpty(text)){
                    Toast.makeText(Withdraw.this, "Enter amount",
                            Toast.LENGTH_LONG).show();
                }else {
                    winamount = Float.parseFloat(winningamount.getText().toString());
                    withamount = Float.parseFloat(amount.getText().toString());
                    if (withamount < 100.00) {
                        Toast.makeText(Withdraw.this, "You can withdrawl minimul 100 rs ",
                                Toast.LENGTH_LONG).show();
                    } else {
                        if (winamount >= withamount) {
                            Intent intent = new Intent(Withdraw.this, MobilePayment.class);
                            intent.putExtra("amount", amount2.getText().toString());
                            intent.putExtra("type", "Phone Pay");
                            startActivity(intent);
                        } else {
                            Toast.makeText(Withdraw.this, "Please enter a valid amount.your amount is greater then your winning balance",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }
    private void getData(){

        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Loading Account Balance...",false,false);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(USER_AMOUNT+useremail,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        loading.dismiss();

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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }
    private void showData(JSONArray jsonArray){

        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);

                final String winning=obj.getString("deposit");
                winningamount.setText(winning);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }





    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Withdraw Money");
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