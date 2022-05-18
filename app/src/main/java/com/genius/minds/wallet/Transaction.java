package com.genius.minds.wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Adapter.ProfileViewPagerAdapter;
import com.genius.minds.Fragment.AddMoneyInWalletFragment;
import com.genius.minds.Fragment.AllRounderFragment;
import com.genius.minds.Fragment.BatsmanFragment;
import com.genius.minds.Fragment.BowlerFragment;
import com.genius.minds.Fragment.JoinContestFragment;
import com.genius.minds.Fragment.UserRefferalCommissionFragment;
import com.genius.minds.Fragment.WicketKeeperFragment;
import com.genius.minds.Fragment.WinningAmountFragment;
import com.genius.minds.Fragment.WithDrawTransationHistoryFragment;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.R;
import com.genius.minds.info.ReferFragment;
import com.genius.minds.mycontest.HistoryTabAdapter;
import com.genius.minds.mycontest.TabsAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.genius.minds.Config.MyBaseUrl.TRANSACTION_URL;

public class Transaction extends AppCompatActivity {

    SessionManager session;
    private ProgressBar spinner;
    private static final String TAG = "Genius";
    List<TransactionModel> productList;
    RecyclerView recyclerView;
    private TabLayout tab_layout;
    private ViewPager viewPager1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        viewPager1 = findViewById(R.id.viewpager1);
        setupViewPager(viewPager1);
        tab_layout = findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(viewPager1);

        initToolbar();
    }
    private void setupViewPager(ViewPager viewPager1) {
        ProfileViewPagerAdapter profileViewPagerAdapter = new ProfileViewPagerAdapter(getSupportFragmentManager());
        profileViewPagerAdapter.addFragment(new AddMoneyInWalletFragment(), "AddMoney In Wallet");
        profileViewPagerAdapter.addFragment(new JoinContestFragment(), "Contest Join Details");
        profileViewPagerAdapter.addFragment(new UserRefferalCommissionFragment(), "MyRefferal Commission");
        profileViewPagerAdapter.addFragment(new WinningAmountFragment(), "Contest Winning  Amount");
        profileViewPagerAdapter.addFragment(new WithDrawTransationHistoryFragment(), "With Draw Details");
        viewPager1.setAdapter(profileViewPagerAdapter);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("All Transaction");
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