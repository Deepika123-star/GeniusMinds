package com.genius.minds.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Model.ModelAddWalletHistory;
import com.genius.minds.Model.ModelContinue;
import com.genius.minds.Model.ModelJoinContestHistory;
import com.genius.minds.Model.ModelReferCommissionHistory;
import com.genius.minds.Model.ResponseRefferalCommissionsHistory.TransactionHistoryItem;
import com.genius.minds.R;

import java.util.List;

public class AdapterReferCommissionHistory extends RecyclerView.Adapter<AdapterReferCommissionHistory.AdapterViewHolder> {
   List<TransactionHistoryItem> modelReferCommissionHistories;
   Context context;

   public AdapterReferCommissionHistory(List<TransactionHistoryItem> modelReferCommissionHistories, Context context) {
      this.modelReferCommissionHistories = modelReferCommissionHistories;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_refercommissionhistory,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_categoryNameRefer.setText(modelReferCommissionHistories.get(position).getCategoryName());
      holder.tv_seriesNameRefer.setText(modelReferCommissionHistories.get(position).getSeriesName());

      if (modelReferCommissionHistories.get(position).getType().equals("1")){
//         holder.tv_ReferWinningAmount.setText("\u20B9 "+modelReferCommissionHistories.get(position).getAmount());

       holder.ll_winningAmount.setVisibility(View.GONE);

      }else if (modelReferCommissionHistories.get(position).getType().equals("2")){

         holder.ll_winningAmount.setVisibility(View.VISIBLE);

         holder.tv_ReferWinningAmount.setText("\u20B9 "+modelReferCommissionHistories.get(position).getAmount());

      }



      if (modelReferCommissionHistories.get(position).getType().equals("1")){

       holder.ll_entryFeeMyMyCommissionAmount1.setVisibility(View.VISIBLE);
       holder.ll_refferalWinningAmountMyCommission2.setVisibility(View.GONE);

         holder.tv_entryFeeMyMyCommissionAmount1.setText("\u20B9 "+modelReferCommissionHistories.get(position).getMyCommission());


      }else if (modelReferCommissionHistories.get(position).getType().equals("2")){

         holder.ll_entryFeeMyMyCommissionAmount1.setVisibility(View.GONE);
         holder.ll_refferalWinningAmountMyCommission2.setVisibility(View.VISIBLE);

         holder.tv_userReferCommission.setText("\u20B9 "+modelReferCommissionHistories.get(position).getMyCommission());


      }
      holder.tv_sectionRefer.setText(modelReferCommissionHistories.get(position).getSectionName());
      holder.tv_subCategoryRefer.setText(modelReferCommissionHistories.get(position).getSubCategory());
      holder.tv_ContestNameRefer.setText(modelReferCommissionHistories.get(position).getContestName());
      holder.tv_entryFeeRefer.setText("\u20B9 "+modelReferCommissionHistories.get(position).getEntryFee());
      holder.tv_ReferNameAndMobile.setText(modelReferCommissionHistories.get(position).getReferalNameMobile());

      holder.tv_ReferDate.setText(modelReferCommissionHistories.get(position).getTdate());
      holder.tv_TimeRefer.setText(modelReferCommissionHistories.get(position).getTxnTime());

   }
   @Override
   public int getItemCount() {
      return modelReferCommissionHistories.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView tv_categoryNameRefer,tv_seriesNameRefer,tv_sectionRefer,tv_subCategoryRefer,tv_ContestNameRefer,tv_entryFeeRefer,tv_ReferNameAndMobile,
              tv_ReferWinningAmount,tv_userReferCommission,tv_ReferDate,tv_TimeRefer,tv_entryFeeMyMyCommissionAmount1;
      LinearLayout ll_winningAmount,ll_entryFeeMyMyCommissionAmount1,ll_refferalWinningAmountMyCommission2;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_categoryNameRefer=itemView.findViewById(R.id.tv_categoryNameRefer);
         tv_seriesNameRefer=itemView.findViewById(R.id.tv_seriesNameRefer);
         tv_sectionRefer=itemView.findViewById(R.id.tv_sectionRefer);
         tv_subCategoryRefer=itemView.findViewById(R.id.tv_subCategoryRefer);
         tv_ContestNameRefer=itemView.findViewById(R.id.tv_ContestNameRefer);
         tv_entryFeeRefer=itemView.findViewById(R.id.tv_entryFeeRefer);
         tv_ReferNameAndMobile=itemView.findViewById(R.id.tv_ReferNameAndMobile);
         tv_ReferWinningAmount=itemView.findViewById(R.id.tv_ReferWinningAmount);
         tv_userReferCommission=itemView.findViewById(R.id.tv_userReferCommission);
         tv_ReferDate=itemView.findViewById(R.id.tv_ReferDate);
         tv_TimeRefer=itemView.findViewById(R.id.tv_TimeRefer);
         ll_winningAmount=itemView.findViewById(R.id.ll_winningAmount);
         ll_entryFeeMyMyCommissionAmount1=itemView.findViewById(R.id.ll_entryFeeMyMyCommissionAmount1);
         ll_refferalWinningAmountMyCommission2=itemView.findViewById(R.id.ll_refferalWinningAmountMyCommission2);
         tv_entryFeeMyMyCommissionAmount1=itemView.findViewById(R.id.tv_entryFeeMyMyCommissionAmount1);

      }
   }

}
