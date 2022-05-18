package com.genius.minds.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Model.ResponseJoiContestsHistory.TransactionHistoryItem;
import com.genius.minds.R;

import java.util.List;

public class AdapterJoinContestHistory extends RecyclerView.Adapter<AdapterJoinContestHistory.AdapterViewHolder> {
   List<TransactionHistoryItem> modelJoinContestHistories;
   Context context;

   public AdapterJoinContestHistory(List<TransactionHistoryItem> modelJoinContestHistories, Context context) {
      this.modelJoinContestHistories = modelJoinContestHistories;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_joincontesthistory,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_categoryNameJoin.setText(modelJoinContestHistories.get(position).getCategoryName());
      holder.tv_SeriesNameJoin.setText(modelJoinContestHistories.get(position).getSeriesName());
      holder.tv_sectionJoinContest.setText(modelJoinContestHistories.get(position).getSectionName());
      holder.tv_subCategoryJoin.setText(modelJoinContestHistories.get(position).getSubCategory());
      holder.tv_contestNameJoin.setText(modelJoinContestHistories.get(position).getContestName());
      holder.tv_entryFeeJoin.setText("\u20B9"+modelJoinContestHistories.get(position).getEntryFee());
      holder.tv_dateJoin.setText(modelJoinContestHistories.get(position).getTdate());
      holder.tv_timeJoin.setText(modelJoinContestHistories.get(position).getTxnTime());

   }
   @Override
   public int getItemCount() {
      return modelJoinContestHistories.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView tv_categoryNameJoin,tv_SeriesNameJoin,tv_sectionJoinContest,tv_subCategoryJoin,tv_contestNameJoin,tv_entryFeeJoin,tv_dateJoin,tv_timeJoin;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_categoryNameJoin=itemView.findViewById(R.id.tv_categoryNameJoin);
         tv_SeriesNameJoin=itemView.findViewById(R.id.tv_SeriesNameJoin);
         tv_sectionJoinContest=itemView.findViewById(R.id.tv_sectionJoinContest);
         tv_subCategoryJoin=itemView.findViewById(R.id.tv_subCategoryJoin);
         tv_contestNameJoin=itemView.findViewById(R.id.tv_contestNameJoin);
         tv_entryFeeJoin=itemView.findViewById(R.id.tv_entryFeeJoin);
         tv_dateJoin=itemView.findViewById(R.id.tv_dateJoin);
         tv_timeJoin=itemView.findViewById(R.id.tv_timeJoin);

      }
   }

}
