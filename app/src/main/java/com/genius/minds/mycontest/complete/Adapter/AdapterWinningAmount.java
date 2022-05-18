package com.genius.minds.mycontest.complete.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Model.ResponseSubCategories.SubcategoryListItem;
import com.genius.minds.R;
import com.genius.minds.contest.MatchContest;

import java.util.List;

public class AdapterWinningAmount extends RecyclerView.Adapter<AdapterWinningAmount.AdapterViewHolder> {
    List<SubcategoryListItem> model_banners;
    Context context;

    public AdapterWinningAmount(List<SubcategoryListItem> model_banners, Context context) {
        this.model_banners = model_banners;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
        View view=layoutInflate.inflate(R.layout.layout_completewinngamount,null);
        return new AdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.tv_ReferNameWinning.setText(model_banners.get(position).getSubcat());
        holder.tv_mobileNumberWinning.setText(model_banners.get(position).getSubcat());
        holder.tv_EntryFeeWinning.setText(model_banners.get(position).getSubcat());
        holder.tv_WinningAmountReferalCommssion.setText(model_banners.get(position).getSubcat());


    }
    @Override
    public int getItemCount() {
        return model_banners.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tv_ReferNameWinning,tv_mobileNumberWinning,tv_EntryFeeWinning,tv_WinningAmountReferalCommssion;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ReferNameWinning=itemView.findViewById(R.id.tv_ReferNameWinning);
            tv_mobileNumberWinning=itemView.findViewById(R.id.tv_mobileNumberWinning);
            tv_EntryFeeWinning=itemView.findViewById(R.id.tv_EntryFeeWinning);
            tv_WinningAmountReferalCommssion=itemView.findViewById(R.id.tv_WinningAmountReferalCommssion);

        }
    }

}
