package com.genius.minds.utills;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.HelperClass.HttpParse;
import com.genius.minds.R;
import com.skydoves.elasticviews.ElasticButton;

import java.util.HashMap;
import java.util.Random;
import static com.genius.minds.Config.MyBaseUrl.USER_REGISTER;

public class UserRegister extends BaseActivity {

    Button register,account;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    String finalResult ;
    static   EditText First_Name, mobile, Email, Password,referal ,editotp,cpassword;
    String fname,mobileno,regemail,regpass,regreferal;
    String F_Name_Holder, Mobile_Holder, EmailHolder, PasswordHolder,ReferalHolder,Cpassword_Holder;
    Boolean CheckEditText ;
    LinearLayout otp;
    static TextView extratext,refcode;
    String generatedPassword;
    ElasticButton bt_viewMore;
    Activity activity;
    String code="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        activity=UserRegister.this;
        //Assign Id'S
        First_Name = (EditText)findViewById(R.id.editTextF_Name);
        mobile = (EditText)findViewById(R.id.editTextL_Name);
        Email = (EditText)findViewById(R.id.editTextEmail);
        Password = (EditText)findViewById(R.id.editTextPassword);
        cpassword=(EditText)findViewById(R.id.editTextCpassword);
        referal=(EditText)findViewById(R.id.referal);
        editotp=(EditText)findViewById(R.id.editotp);
        register = (Button)findViewById(R.id.register);
        extratext=(TextView)findViewById(R.id.extratext);
        bt_viewMore=findViewById(R.id.bt_viewMore);
        refcode=findViewById(R.id.refcode);
        String first="Refer this application and ";
        String second="<font color='#E00101'>get 100/-rs Bonus</font>";
        String third=" and Refer this application to your friend and";
        String fourth="<font color='#E00101'>get 100/-rs extra bonus</font>";





        extratext.setText(Html.fromHtml(first+second+third+fourth));
           /*        log_in = (Button)findViewById(R.id.Login);*/
        account = (Button)findViewById(R.id.account);
        otp=(LinearLayout)findViewById(R.id.otp);

        Random random = new Random();
        generatedPassword = String.format("%04d", random.nextInt(10000));



        bt_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager)activity.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", refcode.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(activity, "copied "+refcode.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (First_Name.getText().toString().trim().isEmpty()){
                    First_Name.setError(getString( R.string.errofirstname ));
                }
                else if(mobile.getText().toString().trim().isEmpty()){
                    mobile.setError(getString( R.string.errorphonenumber ));
                }
                else if(Email.getText().toString().trim().isEmpty()){
                    Email.setError(getString( R.string.erroremailID ));
                }
                else if(Password.getText().toString().trim().isEmpty()){
                    Password.setError(getString( R.string.errorPassword ));
                }
                else if(referal.getText().toString().trim().isEmpty()){
                    referal.setError(getString( R.string.referal ));
                }

                else if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                    UtilMethods.INSTANCE.userRegister(activity,
                            First_Name.getText().toString(),
                            mobile.getText().toString(),
                            Email.getText().toString(),
                            Password.getText().toString(),
                            referal.getText().toString());
                }else{
                    UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                }

            }
        });


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRegister.this,UserLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}