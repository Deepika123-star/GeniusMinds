package com.genius.minds.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Model.ResponseWinnigHistorys.TransactionHistoryItem;
import com.genius.minds.R;

import java.util.List;

public class AdapterWinningAmountHistory extends RecyclerView.Adapter<AdapterWinningAmountHistory.AdapterViewHolder> {
   List<TransactionHistoryItem> modelWinningAmountHistories;
   Context context;

   public AdapterWinningAmountHistory(List<TransactionHistoryItem> modelWinningAmountHistories, Context context) {
      this.modelWinningAmountHistories = modelWinningAmountHistories;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_winningamounthistory,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_categoryNameWinning.setText(modelWinningAmountHistories.get(position).getCategoryName());
      holder.tv_matchName.setText(modelWinningAmountHistories.get(position).getSeriesName());
      holder.tv_subCategoryWinning.setText(modelWinningAmountHistories.get(position).getSubCategory());
      holder.tv_ContestNameWinning.setText(modelWinningAmountHistories.get(position).getContestName());
      holder.tv_entryFeeWinning.setText("\u20B9 "+modelWinningAmountHistories.get(position).getEntryFee());
      holder.tv_winningAmounts.setText("\u20B9 "+modelWinningAmountHistories.get(position).getWinningAmount());
      holder.tv_winningDate.setText(modelWinningAmountHistories.get(position).getTdate());
      holder.tv_TimeWinningAmount.setText(modelWinningAmountHistories.get(position).getTxnTime());
      holder.tv_sectionWinning.setText(modelWinningAmountHistories.get(position).getSectionName());

   }
   @Override
   public int getItemCount() {
      return modelWinningAmountHistories.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView tv_categoryNameWinning,tv_matchName,tv_sectionWinning,tv_subCategoryWinning,tv_ContestNameWinning,tv_entryFeeWinning,tv_winningAmounts,
              tv_winningDate,tv_TimeWinningAmount;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_categoryNameWinning=itemView.findViewById(R.id.tv_categoryNameWinning);
         tv_matchName=itemView.findViewById(R.id.tv_matchName);
         tv_subCategoryWinning=itemView.findViewById(R.id.tv_subCategoryWinning);
         tv_ContestNameWinning=itemView.findViewById(R.id.tv_ContestNameWinning);
         tv_entryFeeWinning=itemView.findViewById(R.id.tv_entryFeeWinning);
         tv_winningAmounts=itemView.findViewById(R.id.tv_winningAmounts);
         tv_winningDate=itemView.findViewById(R.id.tv_winningDate);
         tv_TimeWinningAmount=itemView.findViewById(R.id.tv_TimeWinningAmount);
         tv_sectionWinning=itemView.findViewById(R.id.tv_sectionWinning);

      }
   }

}
