package com.genius.minds.contest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.HelperClass.HttpParse;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.MainActivity;
import com.genius.minds.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.genius.minds.Config.MyBaseUrl.JOIN_CONTEST;
import static com.genius.minds.Config.MyBaseUrl.USER_AMOUNT;

public class ConfirmJoining extends AppCompatActivity {

    SessionManager session;
    TextView walletamount,bonusamount;
    Button amounttopay;
    private ProgressBar spinner;
    private static final String TAG = "Genius";
    String contestid,selectedteam,fee,bonus;
    String email;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String finalResult ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_joining);

        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this);
        User user = SharedPrefManager.getInstance(this).getUser();
        email=user.getEmail();

        selectedteam=getIntent().getStringExtra("team");
        fee=getIntent().getStringExtra("fee");
        bonus=getIntent().getStringExtra("bonus");
        contestid=getIntent().getStringExtra("contestid");


        walletamount=(TextView)findViewById(R.id.walletamount);
        bonusamount=(TextView)findViewById(R.id.bonusamount);
        amounttopay=(Button)findViewById(R.id.amounttopay);

        //spinner.setVisibility(View.VISIBLE);
        loadbalance(email);
        initToolbar();
    }
    private void loadbalance(String email){

        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Loading Wallet Details...",false,false);
        Log.d("emailofuser",email);
        // String email="admin@gmail.com";
        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(USER_AMOUNT+email,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                       // spinner.setVisibility(View.GONE);
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
                final String depositamount=obj.getString("deposit");
                String bonusamount1=obj.getString("bonus");

                final double amount= (Double.parseDouble(depositamount))+(Double.parseDouble(bonusamount1));
                final double joinamount=(Double.parseDouble(fee))-(Double.parseDouble(bonus));
                amounttopay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if ((Double.parseDouble(depositamount)) >= (Double.parseDouble(fee)) ){


                            UserRegisterFunction(email, fee,contestid,bonus,selectedteam);
                        }else{
                            Toast.makeText(ConfirmJoining.this, "Don't have money in your wallet please add money",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
                walletamount.setText("\u20B9 "+depositamount);
                bonusamount.setText("\u20B9 "+bonusamount1);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void UserRegisterFunction(final String email,final String fee,final String contestid,final String bonus,final String selectedteam){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(ConfirmJoining.this,"Joining Contest",null,true,true);

            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();
                Toast.makeText(ConfirmJoining.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
                Intent i=new Intent(ConfirmJoining.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("fee",params[1]);

                hashMap.put("contestid",params[2]);

                hashMap.put("bonus",params[3]);

                hashMap.put("selectedteam",params[4]);

                finalResult = httpParse.postRequest(hashMap, JOIN_CONTEST);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(email, fee,contestid,bonus,selectedteam);
    }




    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Matches");
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