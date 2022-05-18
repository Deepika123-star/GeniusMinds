package com.genius.minds.info;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.Constaints;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseUserCommissions.ResponseUserCommission;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skydoves.elasticviews.ElasticButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.genius.minds.Config.MyBaseUrl.USER_PROFILE;

import java.lang.reflect.Type;

public class ReferFragment extends Fragment {
    SessionManager session;
   static TextView refcode,tv_userCommissionHome;
    String code="";
    Button sharebutton;
    ElasticButton elb_myRefer,btn_myReferDetails,bt_viewMore;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_refer, container, false);
        refcode=(TextView)v.findViewById(R.id.refcode);
        elb_myRefer=v.findViewById(R.id.elb_myRefer);
        btn_myReferDetails=v.findViewById(R.id.btn_myReferDetails);
        bt_viewMore=v.findViewById(R.id.bt_viewMore);
        tv_userCommissionHome=v.findViewById(R.id.tv_userCommissionHome);
        sharebutton=(Button)v.findViewById(R.id.sharebutton);
        SharedPreferences pref = this.getActivity().getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        session = new SessionManager(this.getActivity());
        getProfileData();




        SharedPreferences preff = getContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editorr = preff.edit();
        session = new SessionManager(getContext());
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        String  Email_address=user.getEmail();
        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });

        elb_myRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyRefferalsDetailsActivity.class);
                getContext().startActivity(intent);
            }
        });
        btn_myReferDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyRefferalsDetailsActivity.class);
                getContext().startActivity(intent);
            }
        });


        if (UtilMethods.INSTANCE.isNetworkAvailable(getContext())) {
            UtilMethods.INSTANCE.userCommission(getContext(), Email_address,Constaints.refer_code);
        } else {
            UtilMethods.INSTANCE.Error(getContext(), getResources().getString(R.string.NOCONN));

        }


        bt_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", refcode.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "copied", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
    private void getProfileData(){

        User user = SharedPrefManager.getInstance(this.getActivity()).getUser();
        String email=user.getEmail();
        final ProgressDialog loading = ProgressDialog.show(this.getActivity(), "Please wait...","Loading Data...",false,false);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(USER_PROFILE+email,
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
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }
    private void showData(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
                code=obj.getString("referal");
                refcode.setText(obj.getString("referal"));
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyLoginSharedPref",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("status",true);
                editor.putString("referal", refcode.getText().toString());
                editor.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi friends i am using Genius Betting app.Download from given link and enter my referal code and you will get 100 cash bonus in your wallet ." + "https://geniusbetting.in/geniusbetting.apk "+" Code is "+code); //getPackageName()
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }
    public static void userCommissionData(Context context, String response) {
        try {

            Type type = new TypeToken<ResponseUserCommission>() {
            }.getType();
            ResponseUserCommission responseUserCommission = new Gson().fromJson(response, type);

            tv_userCommissionHome.setText("\u20B9  "+responseUserCommission.getGeniusbetting().getTotalAmount());

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("praSDSDFdf", e.toString());
        }
    }

}