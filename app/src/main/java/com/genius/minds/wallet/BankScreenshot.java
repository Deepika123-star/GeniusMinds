package com.genius.minds.wallet;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Helper.Constaints;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.MainActivity;
import com.genius.minds.Model.ResponseCategory.ResponseCategories;
import com.genius.minds.Model.ResponseGetUpis.ResponseGetUpi;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skydoves.elasticviews.ElasticButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class BankScreenshot extends BaseActivity {

    SessionManager session;
    String email,mode,phone;
    int UserID;
   static TextView extratext,tv_upiId;
    EditText userid,profile,bankname,username,acnumber,tdate,amount,mobileNumber;
    String ServerUploadPath1 ="https://geniusbetting.in/geniusapi/BankScreenshot.php" ;
//    Bitmap bitmap;
    boolean check = true;
    ElasticButton btn_browsesImg, submit,bt_viewMore;
    EditText imageName;
    ProgressDialog progressDialog ;
    String balance;

    //    String GetImageNameEditText;
//    String ImageName = "image_name" ;
//    String ImagePath = "image_path" ;
//    String Mode="mode";
//    String BankName="bankname";
//    String AccountHolder="holdername";
//    String AccountNumber="acnumber";
//    String Tdate="tdate";
//    String Amount="amount";
//    String Userid="userid";
//    String Mobile="mobile";
//    String Profile="profile";
    private Calendar myCalendar1;
   Activity activity;

//    Button btn_browsesImg;

    Bitmap bitmap;

    public static String encoding = "";
    private final int PICK_IMAGE_CAMERA = 0, PICK_IMAGE_GALLERY = 1;
    static  ImageView iv_UploadImage;
    String code="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_screenshot);
        activity=BankScreenshot.this;
        Intent intent = getIntent();
        User user = SharedPrefManager.getInstance(this).getUser();
        String profilename=user.getUsername();

        SharedPreferences pref = this.getSharedPreferences("geniusmindspref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(this);
        User userr = SharedPrefManager.getInstance(this).getUser();
        UserID=userr.getId();



        mode = intent.getExtras().getString("mode");
        phone = intent.getExtras().getString("phoneNew");
        email = intent.getExtras().getString("emailNew");
        balance= intent.getExtras().getString("amountNew");
        
        userid=(EditText)findViewById(R.id.userid);
        bankname=(EditText)findViewById(R.id.bankname);
        username=(EditText)findViewById(R.id.username);
        acnumber=(EditText)findViewById(R.id.acnumber);
        tdate=(EditText)findViewById(R.id.tdate);
        amount=(EditText)findViewById(R.id.amount);
        mobileNumber=(EditText)findViewById(R.id.mobileNumber);
        profile=(EditText)findViewById(R.id.profile);
        extratext=(TextView) findViewById(R.id.extratext);

        tv_upiId=findViewById(R.id.tv_upiId);
        bt_viewMore=findViewById(R.id.bt_viewMore);
        extratext.setText("आप अपने PHONE PAY, GOOGLE PAY, PAYTM,BHIM या फिर किसी भी UPI APP से इस UPI ID पर PAYMENT कर सकते है. PAYMENT करने के बाद payment page का screenshot ले लेना है. फिर नीचे अपना नाम, जिस मोबाईल नम्बर से PAYMENT किया है वो नम्बर डालना है, ये सब भर कर payment page  की screenshot image को attach screenshot बटन पर click करके UPLOAD करके SUMBIT BUTTON पर click करना है.है. आपके GENIUS SPORTS APP  के WALLET मे रुपये ADD  हो जायेंगे.   \n" +
                "        You can PAYMENT on this UPI ID from your PHONE PAY, GOOGLE PAY, PAYTM, BHIM or any UPI APP. After making the payment, the screenshot of the payment page has to be taken. Then you have to enter your name, the mobile number from which the payment has been made, after filling all this, the screenshot image of the payment page has to be uploaded by clicking on the attached screenshot button and clicking on SUMBIT BUTTON. Money will be ADD in the WALLET of your GENIUS SPORTS APP.");
        userid.setText(email);
        profile.setText(profilename);
        amount.setText(balance);
//        Toast.makeText(activity, ""+profilename, Toast.LENGTH_SHORT).show();


        iv_UploadImage = findViewById(R.id.imageView);
        imageName = findViewById(R.id.editTextImageName);
        btn_browsesImg = findViewById(R.id.buttonSelect);
        submit = findViewById(R.id.submit);


        bt_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager)activity.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", tv_upiId.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(activity, "copied "+tv_upiId.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
            UtilMethods.INSTANCE.userUpiData(activity);

        }else{
            UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
        }

        btn_browsesImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PackageManager pm = context.getPackageManager();
                    int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, context.getPackageName());
                    if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                        final CharSequence[] options = {"Take Photo", "Choose from Gallery"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Select option");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                if (options[item].equals("Take Photo")) {
                                    dialog.dismiss();
                                    Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(camera_intent, PICK_IMAGE_CAMERA);
                                } else if (options[item].equals("Choose from Gallery")) {
                                    dialog.dismiss();
                                    Intent intent = new Intent();
                                    intent.setType("image/*");
                                    intent.setAction(Intent.ACTION_PICK);
                                    startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_GALLERY);
                                }

                            }
                        });
                        builder.show();
                    } else
                        requestPermission();
                } catch (Exception e) {
                    requestPermission();
                    e.printStackTrace();
                    Log.e("dfdsddfg", e.getMessage());
                }

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){
                    UtilMethods.INSTANCE.addPayment(activity,
                            profilename,
                            amount.getText().toString(),
                            mode,
                            username.getText().toString(),
                            mobileNumber.getText().toString(),
                            tdate.getText().toString(),
                            encoding);
                }else{
                    UtilMethods.INSTANCE.Error(activity,getResources().getString(R.string.NOCONN));
                }


            }
        });

        tdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tdate:
                        Calendar myCalendar = Calendar.getInstance();
                        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, R.style.DatePickerDialog, datePicker,
                                myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.show();
                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                        return;
                }
            }
        });

        initToolbar();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(context, new String[]{WRITE_EXTERNAL_STORAGE, CAMERA, READ_EXTERNAL_STORAGE}, 101);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case PICK_IMAGE_GALLERY:
                if (resultCode == RESULT_OK) {
                    Uri filePath = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), filePath);
                        iv_UploadImage.setImageBitmap(bitmap);
                        encoding = encodeImage(bitmap);
                        Log.d("imageDta",""+encoding);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case PICK_IMAGE_CAMERA:
                if (resultCode == RESULT_OK) {
                    try {
                        bitmap = (Bitmap)data.getExtras().get("data");
                        iv_UploadImage.setImageBitmap(bitmap);
                        encoding = encodeImage(bitmap);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;

        }



    }

    public String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    private DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar1 = Calendar.getInstance();
            myCalendar1.set(Calendar.YEAR, year);
            myCalendar1.set(Calendar.MONTH, monthOfYear);
            myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            tdate.setText(sdf.format(myCalendar1.getTime()));

        }
    };



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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public static void getdataUpi(Context context, String response) {
        Type type = new TypeToken<ResponseGetUpi>() {}.getType();
        ResponseGetUpi responseGetUpi = new Gson().fromJson(response, type);
        tv_upiId.setText(responseGetUpi.getGeniusbetting().getUpiId());
    }

}