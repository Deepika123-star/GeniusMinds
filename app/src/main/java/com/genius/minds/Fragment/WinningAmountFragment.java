package com.genius.minds.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Adapter.AdapterWinningAmountHistory;
import com.genius.minds.Helper.BaseFragment;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseWinnigHistorys.ResponseJoinWinningHistory;
import com.genius.minds.Model.ResponseWinnigHistorys.TransactionHistoryItem;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class WinningAmountFragment extends BaseFragment {


    static RecyclerView rec_winningAmount;
    static AdapterWinningAmountHistory adapterWinningAmountHistory;
    static List<TransactionHistoryItem> modelWinningAmountHistories =new ArrayList<>();

    SessionManager session;
    String type="",index="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_winning_amount, container, false);
        rec_winningAmount=view.findViewById(R.id.rec_winningAmount);

        SharedPreferences pref = getContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getContext());
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        String  Email_address=user.getEmail();


        if (UtilMethods.INSTANCE.isNetworkAvailable(context)){
            UtilMethods.INSTANCE.historyWinnigContestLists(context,Email_address,index);

        }else{
            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
        }


        return  view;
    }

    @Override
    public void onViewCreatedStuff(View view, @Nullable Bundle savedInstanceState) {

    }

    public static void getWinninghistory(Context context, String response) {
        Type type = new TypeToken<ResponseJoinWinningHistory>() {}.getType();
        ResponseJoinWinningHistory responseJoinContestHistory = new Gson().fromJson(response, type);

        modelWinningAmountHistories=responseJoinContestHistory.getGeniusbetting().getTransactionHistory();
        if (modelWinningAmountHistories.size() > 0) {
            adapterWinningAmountHistory = new AdapterWinningAmountHistory(modelWinningAmountHistories, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            rec_winningAmount.setLayoutManager(horizontalLayoutManagaer1);
            rec_winningAmount.setAdapter(adapterWinningAmountHistory);

        }
    }

}