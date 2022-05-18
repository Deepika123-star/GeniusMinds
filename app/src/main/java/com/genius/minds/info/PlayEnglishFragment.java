package com.genius.minds.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.genius.minds.R;

public class PlayEnglishFragment extends Fragment {


    ProgressBar progress_bar;
    WebView webView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_howplayenglish, container, false);
        webView=v.findViewById(R.id.webview);
        progress_bar=v.findViewById(R.id.progress_bar);

        webView.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int progress) {
                if(progress>=80){
                    progress_bar.setVisibility(View.GONE);
                }
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://geniussports.in/appplayenglish");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl("https://geniussports.in/appplayenglish");
                return false;
            }
        });
        return v;
    }
}