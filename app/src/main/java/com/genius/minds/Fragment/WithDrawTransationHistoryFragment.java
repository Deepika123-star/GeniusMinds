package com.genius.minds.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Adapter.AdapterJoinContestHistory;
import com.genius.minds.Adapter.AdapterWinningAmountHistory;
import com.genius.minds.Adapter.AdapterWithDrawHistory;
import com.genius.minds.Helper.BaseFragment;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseJoiContestsHistory.TransactionHistoryItem;
import com.genius.minds.Model.ResponseWinnigHistorys.ResponseJoinWinningHistory;
import com.genius.minds.Model.ResponseWithDrawHistoryies.ResponseWithDrawHistory;
import com.genius.minds.Model.ResponseWithDrawHistoryies.WidhraHistoryItem;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class WithDrawTransationHistoryFragment extends BaseFragment {

    static RecyclerView rec_withDrawHistory;
    static AdapterWithDrawHistory adapterWithDrawHistory;
    static List<WidhraHistoryItem> widhraHistoryItems =new ArrayList<>();

    SessionManager session;
    String type="",index="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_with_draw_transation_history, container, false);

        rec_withDrawHistory=view.findViewById(R.id.rec_withDrawHistory);


        SharedPreferences pref = getContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getContext());
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        String  Email_address=user.getEmail();
        Toast.makeText(context, ""+Email_address, Toast.LENGTH_SHORT).show();

        if (UtilMethods.INSTANCE.isNetworkAvailable(context)){
            UtilMethods.INSTANCE.historyWithDraw(context,Email_address,index);

        }else{
            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
        }
        return view;
    }

    @Override
    public void onViewCreatedStuff(View view, @Nullable Bundle savedInstanceState) {

    }


    public static void getWithDrawHistory(Context context, String response) {


        Type type = new TypeToken<ResponseWithDrawHistory>() {
        }.getType();
        ResponseWithDrawHistory responseJoinContestHistory = new Gson().fromJson(response, type);

        widhraHistoryItems = responseJoinContestHistory.getGeniusbetting().getWidhraHistory();
        if (widhraHistoryItems.size() > 0) {
            adapterWithDrawHistory = new AdapterWithDrawHistory(widhraHistoryItems, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rec_withDrawHistory.setLayoutManager(horizontalLayoutManagaer1);
            rec_withDrawHistory.setAdapter(adapterWithDrawHistory);
        }
    }
}