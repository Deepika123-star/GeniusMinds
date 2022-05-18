package com.genius.minds.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.genius.minds.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.genius.minds.Config.MyBaseUrl.UPDATE_USER_PROFILE;
import static com.genius.minds.Config.MyBaseUrl.USER_PROFILE;

public class EditProfile extends AppCompatActivity {

    SessionManager session;
    EditText et_name,et_username,et_email,et_mobile,et_state,et_city,et_address;
    Button submit;
    String Name_Holder,Username_Holder,Email_Holder,Mobile_Holder,State_Holder,City_Holder,Address_Holder;
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String finalResult ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        et_name=(EditText)findViewById(R.id.et_name);
        et_username=(EditText)findViewById(R.id.et_username);
        et_email=(EditText)findViewById(R.id.et_email);
        et_mobile=(EditText)findViewById(R.id.et_mobile);
        et_state=(EditText)findViewById(R.id.et_state);
        et_city=(EditText)findViewById(R.id.et_city);
        et_address=(EditText)findViewById(R.id.et_address);
        submit=(Button)findViewById(R.id.submit);
        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this);
        User user = SharedPrefManager.getInstance(this).getUser();
        final String emailuser=user.getEmail();
        getProfileData(emailuser);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckEditTextIsEmptyOrNot();
                if(CheckEditText){
                    UserRegisterFunction(Name_Holder,Username_Holder,Email_Holder,Mobile_Holder,State_Holder,City_Holder,Address_Holder);
                }
                else {
                    Toast.makeText(EditProfile.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
                }
            }
        });
        initToolbar();
    }
    public void CheckEditTextIsEmptyOrNot(){

        Name_Holder = et_name.getText().toString();
        Username_Holder = et_username.getText().toString();
        Email_Holder = et_email.getText().toString();
        Mobile_Holder = et_mobile.getText().toString();
        State_Holder = et_state.getText().toString();
        City_Holder = et_city.getText().toString();
        Address_Holder = et_address.getText().toString();

        if(TextUtils.isEmpty(Name_Holder) || TextUtils.isEmpty(Username_Holder) || TextUtils.isEmpty(Email_Holder) || TextUtils.isEmpty(Mobile_Holder) || TextUtils.isEmpty(State_Holder) || TextUtils.isEmpty(City_Holder) || TextUtils.isEmpty(Address_Holder))
        {
            CheckEditText = false;
        }
        else {
            CheckEditText = true ;
        }
    }

    public void UserRegisterFunction(final String name,final String username,final String email,final String mobile,final String state,final String city,final String address){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(EditProfile.this,"Loading Data","Updating Profile",true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();
                Toast.makeText(EditProfile.this, httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }
            @Override
            protected String doInBackground(String... params) {

                hashMap.put("name",params[0]);
                hashMap.put("username",params[1]);
                hashMap.put("email",params[2]);
                hashMap.put("mobile",params[3]);
                hashMap.put("state",params[4]);
                hashMap.put("city",params[5]);
                hashMap.put("address",params[6]);
                finalResult = httpParse.postRequest(hashMap, UPDATE_USER_PROFILE);
                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();
        userRegisterFunctionClass.execute(name,username,email,mobile,state,city,address);
    }


    private void getProfileData(String emailuser){

        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Loading Profile Data...",false,false);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(USER_PROFILE+emailuser,
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
                // username.setText(obj.getString("username"));
                et_name.setText(obj.getString("name"));
                et_username.setText(obj.getString("username"));
                et_email.setText(obj.getString("email"));
                et_mobile.setText(obj.getString("mobile"));
                et_state.setText(obj.getString("state"));
                et_city.setText(obj.getString("city"));
                et_address.setText(obj.getString("address"));
                if (obj.getString("address")=="")
                {
                    submit.setText("Submit");
                }else{
                    submit.setText("Update Profile");
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
        getSupportActionBar().setTitle("Edit Profile");
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