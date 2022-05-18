package com.genius.minds.webview;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.MainActivity;
import com.genius.minds.R;
import com.genius.minds.Utils.CustomLoader;

public class WebViewActivity extends AppCompatActivity {

    public static final String DATA = "urlpmt";
  //  public static final String TITLE = "title";
    private String url, title;
    private WebView webview;
    private ProgressBar progressBar;
     CustomLoader customLoader;
Button btn;
    String tem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        url = getIntent().getExtras().getString(DATA, "");
      //  title = getIntent().getExtras().getString(TITLE, "");
        //Toolbar_Set.INSTANCE.setToolbar(this, title);
        //Toolbar_Set.INSTANCE.setBottomNav(this);
        webview = findViewById(R.id.webview);
        btn = findViewById(R.id.btn);
        progressBar = (ProgressBar)findViewById(R.id.progress);

      /*  Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);
*/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(WebViewActivity.this, ""+tem, Toast.LENGTH_SHORT).show();
                UtilMethods.INSTANCE.paymtList(WebViewActivity.this,tem);
            }
        });
        customLoader = new CustomLoader(WebViewActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
        customLoader.show();
        if (UtilMethods.INSTANCE.isNetworkAvailable(WebViewActivity.this)) {
            webview.setWebViewClient(new MyBrowser());
            webview.getSettings().setLoadsImagesAutomatically(true);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.getSettings().setAllowFileAccess(true);
            webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            webview.clearCache(true);
            webview.loadUrl(url);
        } else {
            UtilMethods.INSTANCE.Error(WebViewActivity.this,getResources().getString(R.string.NOCONN));

        }
    }
    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
        /*    view.loadUrl(url);
            return true;*/
            Log.e("test map-----", url);
            try {
                if (url.contains("https://pragatisoulutions.com/geniusbetting/admin/api/success?")) {
                   System.out.println(url);
                   /* Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity( intent );*/
                } else if (url.contains("com.google.android.apps.maps")){
//                    System.out.println(decodeQueryString(url.replace("https://www.google.com/maps/dir/?", "")));
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity( intent );
                }
                else {
                    view.loadUrl(url);
                }
            } catch (Exception ignored) {

            }
            return true;
        }
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request)
        {
            Log.d("fresh url----", "shouldOverrideUrlLoading: "+request.getUrl());
            Uri uri = request.getUrl();
            return shouldOverrideUrlLoading(uri.toString());
        }

        private boolean shouldOverrideUrlLoading(String toString) {
            Uri uri=Uri.parse(toString);
            String client = uri.getQueryParameter("client_txn_id");
            String txn_id = uri.getQueryParameter("txn_id");
            Log.d("client_txn_id---", "shouldOverrideUrlLoading: "+client);
            gettid(client);
          //  Toast.makeText(WebViewActivity.this, ""+client+txn_id, Toast.LENGTH_SHORT).show();
            Log.i("tag---", "shouldOverrideUrlLoading() URL : " + toString);
            if (toString.contains("pay")){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(toString));
                startActivity( intent );
            }
            if (toString.contains("client_txn_id")){
               // btn.setVisibility(View.VISIBLE);
              /* Intent intent=new Intent(WebViewActivity.this, MainActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);*/
/*RedirectUrl*/
                final AlertDialog.Builder alert = new AlertDialog.Builder(WebViewActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.confirmdialog,null);
                Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
                alert.setView(mView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                btn_okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UtilMethods.INSTANCE.paymtList(WebViewActivity.this,client);
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
            /*

if (toString.contains("https://pragatisoulutions.com/geniusbetting/admin/api/RedirectUrl?client_txn_id=1954078623&txn_id=164111"))
            // Here put your code
{  // https://pragatisoulutions.com/geniusbetting/admin/api/RedirectUrl?client_txn_id=8503791246&txn_id=164116
}*/
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
          //  view.loadUrl("javascript:var redirect = document.getElementById(\"footer\"); footer.parentNode.removeChild(footer);");
            Log.d("fi----", "onPageFinished: "+url);
         //   progressBar.setVisibility(View.GONE);
            customLoader.dismiss();
            super.onPageFinished(view, url);
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
           // progressBar.setVisibility(View.VISIBLE);
            Log.d("pg---", "onPageStarted: "+url);
            customLoader.show();
            super.onPageStarted(view, url, favicon);
        }
    }

    private void gettid(String client) {
         tem=client;
    }


    @Override
    public void onBackPressed() {
        if (webview.canGoBack()){
            webview.goBack();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
       // Toolbar_Set.INSTANCE.getCartList(this);
    }

  /*  public boolean shouldOverrideUrlLoading(WebView view, String url) {

        Log.e("test map", url);
        try {
            if (url.contains("https://www.google.com/maps/dir/?")) {
//                    System.out.println(decodeQueryString(url.replace("https://www.google.com/maps/dir/?", "")));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity( intent );
            } else if (url.contains("com.google.android.apps.maps")){
//                    System.out.println(decodeQueryString(url.replace("https://www.google.com/maps/dir/?", "")));
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity( intent );
            }
            else {
                view.loadUrl(url);
            }
        } catch (Exception ignored) {

        }


        return true;
    }*/

    public static void pmt(Context context, String toString) {
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }

}