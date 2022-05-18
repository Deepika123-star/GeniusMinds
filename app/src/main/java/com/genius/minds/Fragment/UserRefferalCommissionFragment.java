package com.genius.minds.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Adapter.AdapterJoinContestHistory;
import com.genius.minds.Adapter.AdapterReferCommissionHistory;
import com.genius.minds.Adapter.AdapterWinningAmountHistory;
import com.genius.minds.Helper.BaseActivity;
import com.genius.minds.Helper.BaseFragment;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ModelJoinContestHistory;
import com.genius.minds.Model.ModelReferCommissionHistory;
import com.genius.minds.Model.ResponseRefferalCommissionsHistory.ResponseRefferalCommissionHistory;
import com.genius.minds.Model.ResponseRefferalCommissionsHistory.TransactionHistoryItem;
import com.genius.minds.Model.ResponseWinnigHistorys.ResponseJoinWinningHistory;
import com.genius.minds.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserRefferalCommissionFragment extends BaseFragment {


    static RecyclerView rec_refer;
    static AdapterReferCommissionHistory adapterReferCommissionHistory;
    static List<TransactionHistoryItem> modelReferCommissionHistories =new ArrayList<>();

    String [] entryFee={"300","300","300","590","559","755","900","202","2020","533",
            "300","300","300","300","300"};
    String [] winningAmount={"300","300","300","590","559","755","900","202","2020","533",
            "300","300","300","300","300"};
    String [] commission={"300","300","300","590","559","755","900","202","2020","533",
            "300","300","300","300","300"};

    SessionManager session;
    String type="",index="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_refferal_commission, container, false);
        rec_refer=view.findViewById(R.id.rec_refer);


        SharedPreferences pref = getContext().getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(getContext());
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        String  Email_address=user.getEmail();

//
//        RecyclerView.LayoutManager vertical = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
//        rec_refer.setLayoutManager(vertical);
//        rec_refer.setItemAnimator(new DefaultItemAnimator());
//        for (int i = 0; i < entryFee.length; i++) {
//            ModelReferCommissionHistory ab = new ModelReferCommissionHistory(entryFee[i],winningAmount[i],commission[i]);
//            modelReferCommissionHistories.add(ab);
//        }
//        adapterReferCommissionHistory = new AdapterReferCommissionHistory(modelReferCommissionHistories,context);
//        rec_refer.setAdapter(adapterReferCommissionHistory);


        if (UtilMethods.INSTANCE.isNetworkAvailable(context)){
            UtilMethods.INSTANCE.historyRefferalWinHistory(context,Email_address,index);

        }else{
            UtilMethods.INSTANCE.Error(context,getResources().getString(R.string.NOCONN));
        }


        return view;
    }

    @Override
    public void onViewCreatedStuff(View view, @Nullable Bundle savedInstanceState) {

    }


    public static void getRefferalhistory(Context context, String response) {

        Type type = new TypeToken<ResponseRefferalCommissionHistory>() {
        }.getType();
        ResponseRefferalCommissionHistory responseJoinContestHistory = new Gson().fromJson(response, type);

        modelReferCommissionHistories =  responseJoinContestHistory.getGeniusbetting().getTransactionHistory();
        if (modelReferCommissionHistories.size() > 0) {
            adapterReferCommissionHistory = new AdapterReferCommissionHistory(modelReferCommissionHistories, context);
            LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            rec_refer.setLayoutManager(horizontalLayoutManagaer1);
            rec_refer.setAdapter(adapterReferCommissionHistory);

        }
    }
}