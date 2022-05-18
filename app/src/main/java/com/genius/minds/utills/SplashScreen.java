package com.genius.minds.utills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.MainActivity;
import com.genius.minds.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.genius.minds.Config.MyBaseUrl.VERSION_DETAILS;

public class  SplashScreen extends AppCompatActivity {

    SessionManager session;
    Handler handler;
    String email=null;
    int UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getApplicationContext());
        User user = SharedPrefManager.getInstance(this).getUser();
        email=user.getUsername();

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (email==null)
                {
                    getData();
                    Intent intent = new Intent(SplashScreen.this, UserLoginActivity.class);
                    startActivity(intent);
                }else{
                    getData();
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        },3000);
    }
    public static boolean isNullOrEmpty(String email)
    {
        if (email!=null && !email.trim().isEmpty())
            return false;
        return true;
    }
    private void getData(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(VERSION_DETAILS,
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
                final String getversion=obj.getString("version");
                PackageManager manager = SplashScreen.this.getPackageManager();
                try {
                    PackageInfo info = manager.getPackageInfo(SplashScreen.this.getPackageName(), 0);
                    String packageName = info.packageName;
                    int versionCode = info.versionCode;
                    String versionName = info.versionName;

                    if (versionCode!=Integer.parseInt(getversion)){
                        Intent intent = new Intent(SplashScreen.this, Download.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    // TODO Auto-generated catch block
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
