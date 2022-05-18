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
import com.genius.minds.Adapter.AdapterAddWalletHistory;
import com.genius.minds.Helper.BaseFragment;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseTransation.ResponseTransationHistory;
import com.genius.minds.Model.ResponseTransation.TransactionHistoryItem;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddMoneyInWalletFragment extends BaseFragment {

static RecyclerView rec_addMoney;
static AdapterAddWalletHistory adapterAddWalletHistory;
static List<TransactionHistoryItem> modelAddWalletHistories = new ArrayList<>();


    SessionManager session;
    String type="",index="";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_money_in_wallet, container, false);
        rec_addMoney=view.findViewById(R.id.rec_addMoney);


        SharedPreferences pref = getContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getContext());
        User user = SharedPrefManager.getInstance(getContext()).getUser();
       String  Email_address=user.getEmail();


        if (UtilMethods.INSTANCE.isNetworkAvailable(context)){
            UtilMethods.INSTANCE.historyAddMoneyLists(context,Email_address,index);

        }else{
            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
        }
        return view;
    }

    @Override
    public void onViewCreatedStuff(View view, @Nullable Bundle savedInstanceState) {

    }

    public static void getMoney(Context context, String response) {
        Type type = new TypeToken<ResponseTransationHistory>() {}.getType();
        ResponseTransationHistory responseDashBoardList = new Gson().fromJson(response, type);

        modelAddWalletHistories=responseDashBoardList.getGeniusbetting().getTransactionHistory();
        if (modelAddWalletHistories.size() > 0) {
            adapterAddWalletHistory = new AdapterAddWalletHistory(modelAddWalletHistories, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            rec_addMoney.setLayoutManager(horizontalLayoutManagaer1);
            rec_addMoney.setAdapter(adapterAddWalletHistory);

        }
    }

}