package com.genius.minds.utills;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.HelperClass.HttpParse;
import com.genius.minds.R;

import java.util.HashMap;

import static com.genius.minds.Config.MyBaseUrl.FORGOT_PASSWORD;

public class ChangePassword extends BaseActivity {
Activity activity;
    ProgressDialog progressDialog;
    EditText password,cpassword,et_confirmOtp;
    Button change;
    String PasswordHolder,Cpassword_Holder;
    Boolean CheckEditText ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String finalResult ;

    String phones,otps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        activity=ChangePassword.this;
        password=(EditText)findViewById(R.id.password);
        cpassword=(EditText)findViewById(R.id.cpassword);
        change=(Button)findViewById(R.id.change);
        et_confirmOtp=findViewById(R.id.et_confirmOtp);


        phones=getIntent().getStringExtra("phones");
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (cpassword.getText().toString().equals(password.getText().toString().trim().isEmpty())){
                    cpassword.setError(getString( R.string.errorPasswords));
                } else if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                               UtilMethods.INSTANCE.userForgetPassword(activity,
                                       phones,
                                       password.getText().toString(),
                                       et_confirmOtp.getText().toString());
                }else{
                    UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                }
//                if (cpassword.getText().toString().equals(password.getText().toString())) {
//
////                    UserRegisterFunction(password.getText().toString(),mobile);
//                }
            }
        });
    }
    public void UserRegisterFunction(final String password, final String mobile){

//        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                progressDialog = ProgressDialog.show(ChangePassword.this,"Changing Password",null,true,true);
//
//            }
//            @Override
//            protected void onPostExecute(String httpResponseMsg) {
//                super.onPostExecute(httpResponseMsg);
//                progressDialog.dismiss();
//                Toast.makeText(ChangePassword.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(ChangePassword.this,UserLoginActivity.class);
//                startActivity(intent);
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//
//                hashMap.put("password",params[0]);
//                hashMap.put("mobile",params[1]);
//                finalResult = httpParse.postRequest(hashMap, FORGOT_PASSWORD);
//
//                return finalResult;
//            }
//        }

//        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

//        userRegisterFunctionClass.execute(password,mobile);
    }
//    public void CheckEditTextIsEmptyOrNot(){
//        PasswordHolder = password.getText().toString();
//        Cpassword_Holder=cpassword.getText().toString();
//
//        if(TextUtils.isEmpty(PasswordHolder) || TextUtils.isEmpty(Cpassword_Holder))
//        {
//            CheckEditText = false;
//        }
//        else {
//            CheckEditText = true ;
//        }
//    }
}
