package com.genius.minds.mycontest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Helper.Constaints;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Inerfaces.InterfaceLeaderBoard;
import com.genius.minds.Model.ResponseGetBetNames.ResponseGetBetName;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;
import static com.genius.minds.Config.MyBaseUrl.LEGERBOARD_URL;
import static com.genius.minds.Config.MyBaseUrl.MATCH_LIST;
import static com.genius.minds.Config.MyBaseUrl.WIN_STATUS;

public class LedgerBoard extends BaseActivity implements InterfaceLeaderBoard {

    private ProgressBar spinner;
 static    TextView txtstatus,tv_betName;
    private static final String TAG = "Genius";
    List<LegerboardModel> productList;
    RecyclerView recyclerView;
    Activity activity;
    SessionManager session;
 static String myUser_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger_board);
        activity=LedgerBoard.this;
        recyclerView = findViewById(R.id.recylcerView);
        txtstatus=findViewById(R.id.txtstatus);
        String contestidd=getIntent().getStringExtra("contestid");
        String contestid_UserEmails=getIntent().getStringExtra("contestid_UserEmail");
        tv_betName=findViewById(R.id.tv_betName);
        spinner=(ProgressBar)findViewById(R.id.progressBar);





        SharedPreferences pref = getApplicationContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getApplicationContext());
        User user = SharedPrefManager.getInstance(this).getUser();
        Constaints.Email_address=user.getEmail();
//        Toast.makeText(activity, ""+Constaints.Email_address, Toast.LENGTH_SHORT).show();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        loadLegerboard();
        spinner.setVisibility(View.VISIBLE);
        initToolbar();
        getData();


        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.userGetContest(activity, contestidd,Constaints.Email_address);

        }else{
            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
        }



    }
    private void loadLegerboard() {
        String contestid=getIntent().getStringExtra("contestid");
        User user = SharedPrefManager.getInstance(this).getUser();
        final String emailuser = user.getUsername();
        Log.d(TAG, "matchurl: "+MATCH_LIST+contestid);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LEGERBOARD_URL+contestid+"&email="+emailuser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject contest = array.getJSONObject(i);
                                productList.add(new LegerboardModel(
                                        contest.getInt("id"),
                                        contest.getInt("contest_id"),
                                        contest.getString("winstatus"),
                                        contest.getString("useremail"),
                                        contest.getString("username"),
                                        contest.getString("amount"),
                                        contest.getString("winamount"),
                                        contest.getString("matchcode")

                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            LegerboardAdapter adapter = new LegerboardAdapter(LedgerBoard.this, productList,(InterfaceLeaderBoard) context);
                            recyclerView.setAdapter(adapter);
                            spinner.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private void getData(){
        String id=getIntent().getStringExtra("contestid");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(WIN_STATUS+id,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

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
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
                String result=obj.getString("result");
                if (isEmpty(result))
                {

                }else{
                    txtstatus.setText("Result : "+result+" win");
                }

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
        getSupportActionBar().setTitle("Leaderboard");
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

    public static void getBetName(Context context, String response) {
        try
        {

            Type type = new TypeToken<ResponseGetBetName>() {}.getType();
            ResponseGetBetName responseProfile = new Gson().fromJson(response, type);

            tv_betName.setText(responseProfile.getGeniusbetting().getBetName());


        }catch (Exception e){
            e.printStackTrace();
            Log.d("praSDSDFdf",e.toString());
        }

    }

    @Override
    public void onRowClick(String userEmail) {
        myUser_Email=userEmail;
    }
}