package com.genius.minds.info;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Helper.Constaints;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseMyRefralLists.MyreferalItem;
import com.genius.minds.Model.ResponseMyRefralLists.ResponseMyReferalList;
import com.genius.minds.R;
import com.genius.minds.info.Adapter.AdapterMyRefferalList;
import com.genius.minds.info.Modeles.ModelViewMoreRefer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MyRefferalsDetailsActivity extends BaseActivity {
    Activity activity;
    ImageView iv_backMore;
    SwipeRefreshLayout swiperefresh;
   static RecyclerView rec_viewRefer;
    static AdapterMyRefferalList adapterMyRefferalList;
    ArrayList<ModelViewMoreRefer> modelViewMores;
    static ArrayList<MyreferalItem> myreferalItems = new ArrayList<>();
   static LinearLayout ll_shopCarts;
    SessionManager session;

   int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_refferals_details);
        activity=MyRefferalsDetailsActivity.this;
        iv_backMore=findViewById(R.id.iv_backMore);
        rec_viewRefer=findViewById(R.id.rec_viewRefer);
        ll_shopCarts=findViewById(R.id.ll_shopCarts);
        swiperefresh=findViewById(R.id.swiperefresh);
        int myColor = Color.parseColor("#E00101");
        swiperefresh.setColorSchemeColors(myColor);
        iv_backMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences pref = getApplicationContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getApplicationContext());
        User user = SharedPrefManager.getInstance(this).getUser();
        Constaints.user_id=user.getId();

//        Toast.makeText(MyRefferalsDetailsActivity.this, "user id is "+Constaints.user_id, Toast.LENGTH_SHORT).show();

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override

                    public void run() {
                        swiperefresh.setRefreshing(false);
                        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)){

                            UtilMethods.INSTANCE.userReferalPage(activity, Constaints.user_id);

                        }else{
                            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
                        }
                    }
                },3000);
            }
        });

        if (UtilMethods.INSTANCE.isNetworkAvailable(activity)) {
            UtilMethods.INSTANCE.userReferalPage(activity, Constaints.user_id);
        } else {
            UtilMethods.INSTANCE.Error(activity, getResources().getString(R.string.NOCONN));

        }

    }

    public static void referalListData(Context context, String response) {

        Type type = new TypeToken<ResponseMyReferalList>() {
        }.getType();
        ResponseMyReferalList responseDashBoardList = new Gson().fromJson(response, type);

        myreferalItems = (ArrayList<MyreferalItem>) responseDashBoardList.getMyreferal();

        if (myreferalItems.isEmpty()) {

            rec_viewRefer.setVisibility(View.GONE);
            ll_shopCarts.setVisibility(View.VISIBLE);

        } else {

            rec_viewRefer.setVisibility(View.VISIBLE);

            ll_shopCarts.setVisibility(View.GONE);
        }
        if (myreferalItems.size() > 0) {
            adapterMyRefferalList = new AdapterMyRefferalList(myreferalItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rec_viewRefer.setLayoutManager(horizontalLayoutManagaer1);
            rec_viewRefer.setAdapter(adapterMyRefferalList);

        }
    }

}