package com.genius.minds.wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.genius.minds.R;
import com.razorpay.Checkout;

public class StartPaymentActivity extends AppCompatActivity{

    String phone,amount,email;
    RadioButton edbphonepay,rdbbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        Checkout.preload(getApplicationContext());
        Intent intent = getIntent();
        phone = intent.getExtras().getString("phone");
        email = intent.getExtras().getString("email");
        amount = intent.getExtras().getString("amount");
        initToolbar();

        edbphonepay = (RadioButton)findViewById(R.id.edbphonepay);
        rdbbank = (RadioButton)findViewById(R.id.rdbbank);
        Button btn = (Button)findViewById(R.id.getBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result= (edbphonepay.isChecked())?"Online Payment":(rdbbank.isChecked())?"Net Banking":"";
//                String result= (rdbbank.isChecked())?"Net Banking":"";

                if (rdbbank.isChecked())
                {
                    Intent i = new Intent(getApplicationContext(), BankScreenshot.class);
                    i.putExtra("mode", result);
                    i.putExtra("phoneNew", email);
                    i.putExtra("emailNew", email);
                    i.putExtra("amountNew", amount);
                    startActivity(i);
                }else {
                    Intent i = new Intent(getApplicationContext(), UpiPayment.class);
                    i.putExtra("mode", result);
                    i.putExtra("email", email);
                    i.putExtra("amount", amount);
                    startActivity(i);
                }

            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.edbphonepay:
                if(checked)
                    str = "Online Payment";
                break;
            case R.id.rdbbank:
                if(checked)
                    str = "Offline Mode";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Payment Mode");
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

