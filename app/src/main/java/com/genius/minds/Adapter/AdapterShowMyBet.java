package com.genius.minds.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Activity.LiveBetting.ShowMyBetActivity;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.Model.ResponseGetAllMatchLiveContest.ContestListItem;
import com.genius.minds.Model.ResponseShowMyBets.MyBetListItem;
import com.genius.minds.R;
import com.genius.minds.contest.ConfirmJoining;
import com.genius.minds.contest.MatchContest;
import com.skydoves.elasticviews.ElasticButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.genius.minds.Config.MyBaseUrl.UPDATE_CONTEST_STATUS;

public class AdapterShowMyBet extends RecyclerView.Adapter<AdapterShowMyBet.ProductViewHolder>{


   List<MyBetListItem> myBetListItems;
   private Context context;
   SessionManager session;


   public AdapterShowMyBet(List<MyBetListItem> myBetListItems, Context context) {
      this.myBetListItems = myBetListItems;
      this.context = context;
   }


   @Override
   public AdapterShowMyBet.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(context);
      View view = inflater.inflate(R.layout.layout_showmybet, null);
      return new AdapterShowMyBet.ProductViewHolder(view);
   }

   @Override
   public void onBindViewHolder(final AdapterShowMyBet.ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {

      holder.tv_serialNumbers.setText(myBetListItems.get(position).getSn());
      holder.tv_BettingName.setText(myBetListItems.get(position).getBettingName());
      holder.tv_entryFees.setText("\u20B9 "+myBetListItems.get(position).getEntryFee());
      holder.tv_rates.setText("\u20B9 "+myBetListItems.get(position).getRate());
      holder.tv_prizeMoney.setText("\u20B9 "+myBetListItems.get(position).getPrizeMoney());
      holder.tv_RefferalNames.setText(myBetListItems.get(position).getReferralName());




      if (myBetListItems.get(position).getWinningAmount().isEmpty())
      {
         holder.resultAmount.setText(" ");
      }
      else {
         holder.WinningAmount.setText("\u20B9 "+myBetListItems.get(position).getWinningAmount());
      }
      holder.resultAmount.setText(myBetListItems.get(position).getResult());

      holder.tv_RefferalWinningAmount.setText("\u20B9 "+myBetListItems.get(position).getReferralWinnigAmount());




   }





   @Override
   public int getItemCount() {
      return myBetListItems.size();
   }

   class ProductViewHolder extends RecyclerView.ViewHolder {

      TextView resultAmount,tv_serialNumbers,tv_BettingName,tv_entryFees,tv_rates,tv_prizeMoney,tv_RefferalWinningAmount,tv_RefferalNames,WinningAmount;
      public ProductViewHolder(View itemView) {
         super(itemView);
         tv_serialNumbers=itemView.findViewById(R.id.tv_serialNumbers);
         tv_BettingName=itemView.findViewById(R.id.tv_BettingName);
         tv_entryFees=itemView.findViewById(R.id.tv_entryFees);
         tv_rates=itemView.findViewById(R.id.tv_rates);
         tv_prizeMoney=itemView.findViewById(R.id.tv_prizeMoney);
         tv_RefferalWinningAmount=itemView.findViewById(R.id.tv_RefferalWinningAmount);
         tv_RefferalNames=itemView.findViewById(R.id.tv_RefferalNames);
         WinningAmount=itemView.findViewById(R.id.WinningAmount);
         resultAmount=itemView.findViewById(R.id.resultAmount);

      }
   }

}
