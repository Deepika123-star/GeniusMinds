package com.genius.minds.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.genius.minds.Activity.HowToAddMoneyActivity;
import com.genius.minds.Activity.HowToPlayActivity;
import com.genius.minds.Activity.RefferalCommissionActivity;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.R;
import com.genius.minds.info.EditProfile;
import com.genius.minds.utills.UserLoginActivity;
import com.genius.minds.utills.Webview;

public class MoreFragment extends Fragment {

    LinearLayout myprofile,howToAddMoney,howToPlay,refferalCommission,terms,privacy,registerCertificate,GstCertificate,refund,vidhindi,videnglish,About,contactUs;
    Button logout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_more, container, false);
        myprofile=(LinearLayout)v.findViewById(R.id.refer);
        howToAddMoney=(LinearLayout)v.findViewById(R.id.howToAddMoney);
        howToPlay=(LinearLayout)v.findViewById(R.id.howToPlay);
        refferalCommission=(LinearLayout)v.findViewById(R.id.refferalCommission);
        terms=(LinearLayout)v.findViewById(R.id.terms);
        privacy=(LinearLayout)v.findViewById(R.id.privacy);
        logout=(Button)v.findViewById(R.id.logout);
        registerCertificate=(LinearLayout)v.findViewById(R.id.registerCertificate);
        GstCertificate=(LinearLayout)v.findViewById(R.id.GstCertificate);
        refund=(LinearLayout)v.findViewById(R.id.refund);
        vidhindi=(LinearLayout)v.findViewById(R.id.vidhindi);
        videnglish=(LinearLayout)v.findViewById(R.id.videnglish);
        About=v.findViewById(R.id.About);
        contactUs=v.findViewById(R.id.contactUs);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogout();
            }
        });
        registerCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Gst.class);
                getActivity().startActivity(intent);
            }
        });
        GstCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), GstDownload.class);
                getActivity().startActivity(intent);
            }
        });
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), EditProfile.class);
                getActivity().startActivity(intent);
            }
        });
        howToAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HowToAddMoneyActivity.class);

                getActivity().startActivity(intent);
            }
        });
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), HowToPlayActivity.class);
                getActivity().startActivity(intent);
            }
        });
        refferalCommission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), RefferalCommissionActivity.class);

                getActivity().startActivity(intent);
            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Webview.class);
//                intent.putExtra("url","https://geniusbetting.in/appterm ");
                intent.putExtra("url","https://pragatisoulutions.com/geniusbetting/terms ");
                intent.putExtra("title","Terms and Conditions");
                getActivity().startActivity(intent);
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Webview.class);
//                intent.putExtra("url","https://geniusbetting.in/appprivacy");
                intent.putExtra("url","https://pragatisoulutions.com/geniusbetting/privacypolicy");
                intent.putExtra("title","Privacy Policy");
                getActivity().startActivity(intent);
            }
        });
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Webview.class);
//                intent.putExtra("url","https://geniusbetting.in/apprefund");
                intent.putExtra("url","https://pragatisoulutions.com/geniusbetting/refund");
                intent.putExtra("title","Refund Policy");
                getActivity().startActivity(intent);
            }
        });
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Webview.class);
//                intent.putExtra("url","https://geniusbetting.in/apprefund");
                intent.putExtra("url","https://pragatisoulutions.com/geniusbetting/aboutus");
                intent.putExtra("title","About Us");
                getActivity().startActivity(intent);
            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Webview.class);
//                intent.putExtra("url","https://geniusbetting.in/apprefund");
                intent.putExtra("url","https://pragatisoulutions.com/geniusbetting/contact");
                intent.putExtra("title","Contact Us");
                getActivity().startActivity(intent);
            }
        });
        videnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Addmonwyvideo.class);
                intent.putExtra("title","addmoney");
                getActivity().startActivity(intent);
            }
        });
        vidhindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Playvideo.class);
                intent.putExtra("title","howplay");
                getActivity().startActivity(intent);
            }
        });
        return v;
    }
    private void userlogout() {
        SharedPrefManager.getInstance(this.getActivity()).logout();
        startActivity(new Intent(this.getActivity(), UserLoginActivity.class));
    }
}