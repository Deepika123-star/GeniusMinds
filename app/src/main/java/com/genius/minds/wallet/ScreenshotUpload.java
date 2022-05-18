package com.genius.minds.wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.MainActivity;
import com.genius.minds.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ScreenshotUpload extends AppCompatActivity {
    String profilename;
    String email,result;
    EditText username,number,tdate,amount,userid,profile,mobile;
    String ServerUploadPath1 ="https://geniusbetting.in/geniusapi/Screenshotupload.php" ;
    Bitmap bitmap;
    TextView extratext;
    boolean check = true;
    Button SelectImageGallery, UploadImageServer;
    ImageView imageView;
    EditText imageName;
    ProgressDialog progressDialog ;
    String GetImageNameEditText;
    String ImageName = "image_name" ;
    String ImagePath = "image_path" ;
    String Userid="userid";
    String Profile="profilename";
    String Tdate="tdate";
    String Number="number";
    String Amount="amount";
    String Mode="mode";
    String balance;
    String Username="username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot_upload);
        Intent intent = getIntent();
        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        User user = SharedPrefManager.getInstance(this).getUser();
        profilename=user.getUsername();

        email = intent.getExtras().getString("email");
        result = intent.getExtras().getString("mode");
        balance= intent.getExtras().getString("amount");

        username=(EditText)findViewById(R.id.username);
        Log.d("username",username.getText().toString());
        profile=(EditText)findViewById(R.id.profile);
        userid=(EditText)findViewById(R.id.email);
        number=(EditText)findViewById(R.id.number);
        tdate=(EditText)findViewById(R.id.tdate);
        amount=(EditText)findViewById(R.id.amount);
        extratext=(TextView)findViewById(R.id.extratext);
        mobile=(EditText)findViewById(R.id.mobile);

        userid.setText(email);
        profile.setText(profilename);
        amount.setText(balance);
        imageView = (ImageView)findViewById(R.id.imageView);

        imageName = (EditText)findViewById(R.id.editTextImageName);

        SelectImageGallery = (Button)findViewById(R.id.buttonSelect);

        UploadImageServer = (Button)findViewById(R.id.submit);
        SelectImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

            }
        });


        UploadImageServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetImageNameEditText = imageName.getText().toString();

                ImageUploadToServerFunction();

            }
        });



       if (result.equalsIgnoreCase("Phone Pay"))
        {
            username.setHint(result+" username");
            username.setHintTextColor(Color.LTGRAY);
            number.setHintTextColor(Color.LTGRAY);
            number.setHint(result+" number");

            extratext.setText("आपने जिस Phone Pay Number से रुपये Add किये है और जिस तारीख मे रुपये Add किये है , उसकी जानकारी नीचे भरिये और Phone Pay से Add किये गये रुपये की Transaction History की screenshot को Attach screenshot वाले coloum मे upload करके sumbit कर दीजिये. आपके रुपये 5 मिनट के अंदर आपके wallet मे add हो जायेंगे.\nEnter the information of the phone pay number from which you have added rupees and the date on which you have added rupees, and upload the screenshot of Transaction History of the rupee linked with Phone Pay by uploading it in the coloum with Attach screenshot. Your money will be added to your wallet within 5 minutes.");
        }
         if (result.equalsIgnoreCase("Google Pay"))
        {
            username.setHint(result+" username");
            number.setHint(result+" number");
            username.setHintTextColor(Color.LTGRAY);
            number.setHintTextColor(Color.LTGRAY);
            extratext.setText("आपने जिस Google Pay Number से रुपये Add किये है और जिस तारीख मे रुपये Add किये है , उसकी जानकारी नीचे भरिये और Google Pay से Add किये गये रुपये की Transaction History की screenshot को Attach screenshot वाले coloum मे upload करके sumbit कर दीजिये. आपके रुपये 5 मिनट के अंदर आपके wallet मे add हो जायेंगे. \nEnter the information of the Google pay number from which you have added rupees and the date on which you have added rupees, and upload the screenshot of Transaction History of the rupee linked with Google Pay by uploading it in the coloum with Attach screenshot. Your money will be added to your wallet within 5 minutes.");
        }
        if (result.equalsIgnoreCase("BHIP UPI"))
        {
            username.setHint(result+" username");
            number.setHint(result+" ID");
            username.setHintTextColor(Color.LTGRAY);
            number.setHintTextColor(Color.LTGRAY);
            extratext.setText("आपने जिस BHIM UPI ID  से रुपये Add किये है और जिस तारीख मे रुपये Add किये है , उसकी जानकारी नीचे भरिये और BHIM UPI ID से Add किये गये रुपये की Transaction History की screenshot को Attach screenshot वाले coloum मे upload करके sumbit कर दीजिये. आपके रुपये 5 मिनट के अंदर आपके wallet मे add हो जायेंगे.\n Enter the information of the BHIM UPI ID from which you have added rupees and the date on which you have added rupees, and upload the screenshot of Transaction History of the rupee linked with BHIM UPI ID by uploading it in the coloum with Attach screenshot. Your money will be added to your wallet within 5 minutes.");
        }
        if (result.equalsIgnoreCase("Bank Transfer"))
        {
            username.setHint(result+" username");
            number.setHint(result+" number");
            username.setHintTextColor(Color.LTGRAY);
            number.setHintTextColor(Color.LTGRAY);
            extratext.setText("आपने जिस बैंक खाते से रुपये Add किये है और जिस तारीख मे रुपये Add किये है , उसकी जानकारी नीचे भरिये , जैसे - Bank name,  Account holder name,  Account Number और बैंक खाते से Add किये गये रुपये की Transaction History की screenshot को Attach screenshot वाले coloum मे upload करके sumbit कर दीजिये. आपके रुपये 5 मिनट के अंदर आपके wallet मे add हो जायेंगे.\nEnter the information of the bank account from which you have added money and the date on which you have added money, such as - Bank name, Account holder name, Account number. Upload the screenshot of the Transaction History of the rupee added to the bank account by uploading it to the coloum containing the Attach screenshot. Your money will be added to your wallet within 5 minutes.");
        }



        initToolbar();
    }

    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public void ImageUploadToServerFunction(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

                progressDialog = ProgressDialog.show(ScreenshotUpload.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Toast.makeText(ScreenshotUpload.this,string1,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ScreenshotUpload.this, MainActivity.class);
                startActivity(intent);


            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageName, GetImageNameEditText);
                HashMapParams.put(ImagePath, ConvertImage);
                HashMapParams.put(Userid, userid.getText().toString());
                HashMapParams.put(Profile, profilename);
                HashMapParams.put(Tdate, tdate.getText().toString());
                HashMapParams.put(Number, number.getText().toString());
                HashMapParams.put(Amount, amount.getText().toString());
                HashMapParams.put(Mode, result);
                HashMapParams.put(Username, username.getText().toString());

        //Log.d("data", String.valueOf(HashMapParams));

                String FinalData = imageProcessClass.ImageHttpRequest(ServerUploadPath1, HashMapParams);

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }

















    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Upload Details");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}