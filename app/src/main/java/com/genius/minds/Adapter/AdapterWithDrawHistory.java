package com.genius.minds.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Model.ResponseJoiContestsHistory.TransactionHistoryItem;
import com.genius.minds.Model.ResponseWithDrawHistoryies.WidhraHistoryItem;
import com.genius.minds.R;

import java.util.List;

public class AdapterWithDrawHistory extends RecyclerView.Adapter<AdapterWithDrawHistory.AdapterViewHolder> {
   List<WidhraHistoryItem> modelJoinContestHistories;
   Context context;

   public AdapterWithDrawHistory(List<WidhraHistoryItem> modelJoinContestHistories, Context context) {
      this.modelJoinContestHistories = modelJoinContestHistories;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_withdraw,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

      holder.tv_amountWithDraw.setText("\u20B9"+modelJoinContestHistories.get(position).getWithdrawAmt());
      holder.tv_Mode.setText(modelJoinContestHistories.get(position).getMethod());
      holder.tv_dateWithDraw.setText(modelJoinContestHistories.get(position).getWithdate());
      holder.tv_statusWithDraw.setText(modelJoinContestHistories.get(position).getStatus());

   }
   @Override
   public int getItemCount() {
      return modelJoinContestHistories.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView tv_seriesNameWithDraw,tv_dateWithDraw,tv_amountWithDraw,tv_Mode,tv_statusWithDraw;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_seriesNameWithDraw=itemView.findViewById(R.id.tv_seriesNameWithDraw);
         tv_dateWithDraw=itemView.findViewById(R.id.tv_dateWithDraw);
         tv_amountWithDraw=itemView.findViewById(R.id.tv_amountWithDraw);
         tv_Mode=itemView.findViewById(R.id.tv_Mode);
         tv_statusWithDraw=itemView.findViewById(R.id.tv_statusWithDraw);


      }
   }

}
