package com.genius.minds.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Model.ResponseTransation.TransactionHistoryItem;
import com.genius.minds.R;

import java.util.List;

public class AdapterAddWalletHistory extends RecyclerView.Adapter<AdapterAddWalletHistory.AdapterViewHolder> {
   List<TransactionHistoryItem> modelAddWalletHistories;
   Context context;

   public AdapterAddWalletHistory(List<TransactionHistoryItem> modelAddWalletHistories, Context context) {
      this.modelAddWalletHistories = modelAddWalletHistories;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_addmonetwallethistory,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_paymentMode.setText(modelAddWalletHistories.get(position).getMode());
      holder.tv_Date.setText(modelAddWalletHistories.get(position).getTdate());
      holder.tv_time.setText(modelAddWalletHistories.get(position).getTxnTime());
      holder.tv_amount.setText("\u20B9 "+modelAddWalletHistories.get(position).getAmount());
      holder.tv_status.setText(modelAddWalletHistories.get(position).getStatus());

   }
   @Override
   public int getItemCount() {
      return modelAddWalletHistories.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView tv_paymentMode,tv_Date,tv_time,tv_amount,tv_status;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_paymentMode=itemView.findViewById(R.id.tv_paymentMode);
         tv_Date=itemView.findViewById(R.id.tv_Date);
         tv_time=itemView.findViewById(R.id.tv_time);
         tv_amount=itemView.findViewById(R.id.tv_amount);
         tv_status=itemView.findViewById(R.id.tv_status);

      }
   }

}
