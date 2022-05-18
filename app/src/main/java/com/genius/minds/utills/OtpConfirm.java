package com.genius.minds.utills;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.R;
public class OtpConfirm extends BaseActivity {

    Button confirm;
    EditText editotp;
    LinearLayout resend;
   static TextView tv_resend;
    String names,phones,emails,passwords,referesl,otps;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_confirm);
        activity=OtpConfirm.this;
        tv_resend=findViewById(R.id.tv_resend);
        confirm=(Button)findViewById(R.id.confirm);
        editotp=(EditText)findViewById(R.id.editotp);
        resend=(LinearLayout)findViewById(R.id.resend);
        names=getIntent().getStringExtra("names");
        phones=getIntent().getStringExtra("phones");
        emails=getIntent().getStringExtra("emails");
        passwords=getIntent().getStringExtra("passwords");
        referesl=getIntent().getStringExtra("referesl");
        otps=getIntent().getStringExtra("otps");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editotp.getText().toString().trim().isEmpty()){
                    editotp.setError(getString( R.string.errootp));
                } else if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                    UtilMethods.INSTANCE.userOtp(activity,
                            names,
                            phones,
                            emails,
                            passwords,
                            otps,
                            referesl,
                            editotp.getText().toString());
                }else{
                    UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                }

            }
        });

        tv_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                    UtilMethods.INSTANCE.userRegister(activity,
                            names,
                            phones,
                            emails,
                            passwords,
                            referesl);
                }else{
                    UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                }

            }
        });

    }
}
