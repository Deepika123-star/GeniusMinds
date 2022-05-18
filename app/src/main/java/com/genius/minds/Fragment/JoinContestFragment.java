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
import com.genius.minds.Adapter.AdapterJoinContestHistory;
import com.genius.minds.Helper.BaseFragment;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseJoiContestsHistory.ResponseJoinContestHistory;
import com.genius.minds.Model.ResponseJoiContestsHistory.TransactionHistoryItem;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JoinContestFragment extends BaseFragment {

    static RecyclerView rec_joinHistory;
    static AdapterJoinContestHistory adapterJoinContestHistory;
    static List<TransactionHistoryItem> modelJoinContestHistories =new ArrayList<>();

    SessionManager session;
    String type="",index="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_join_contest, container, false);
        rec_joinHistory=view.findViewById(R.id.rec_joinHistory);


        SharedPreferences pref = getContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getContext());
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        String  Email_address=user.getEmail();


        if (UtilMethods.INSTANCE.isNetworkAvailable(context)){
            UtilMethods.INSTANCE.historyJoinContestLists(context,Email_address,index);

        }else{
            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
        }

        return  view;
    }

    @Override
    public void onViewCreatedStuff(View view, @Nullable Bundle savedInstanceState) {

    }

    public static void getcontesthistory(Context context, String response) {
        Type type = new TypeToken<ResponseJoinContestHistory>() {}.getType();
        ResponseJoinContestHistory responseJoinContestHistory = new Gson().fromJson(response, type);

        modelJoinContestHistories=responseJoinContestHistory.getGeniusbetting().getTransactionHistory();
        if (modelJoinContestHistories.size() > 0) {
            adapterJoinContestHistory = new AdapterJoinContestHistory(modelJoinContestHistories, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            rec_joinHistory.setLayoutManager(horizontalLayoutManagaer1);
            rec_joinHistory.setAdapter(adapterJoinContestHistory);

        }
    }

}