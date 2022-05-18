package com.genius.minds.mycontest.complete.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.genius.minds.R;
import com.genius.minds.mycontest.complete.Model.ModelViewMoreComplete;

import java.util.List;

public class AdapterViewMoreComplete extends RecyclerView.Adapter<AdapterViewMoreComplete.AdapterViewHolder> {
    List<ModelViewMoreComplete> model_banners;
    Context context;

    public AdapterViewMoreComplete(List<ModelViewMoreComplete> model_banners, Context context) {
        this.model_banners = model_banners;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
        View view=layoutInflate.inflate(R.layout.layout_completeviewmore,null);
        return new AdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.tv_serialNumberComplete.setText(model_banners.get(position).getSerialNumber());
        holder.tv_bettingNameComplete.setText(model_banners.get(position).getBettingName());
        holder.tv_entryFeeComplete.setText(model_banners.get(position).getEntryFee());
        holder.tv_rateComplete.setText(model_banners.get(position).getRate());
        holder.tv_winningAmount.setText(model_banners.get(position).getWinningPrice());
        holder.tv_result.setText(model_banners.get(position).getResult());

    }

    @Override
    public int getItemCount() {
        return model_banners.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tv_serialNumberComplete,tv_bettingNameComplete,tv_entryFeeComplete,tv_rateComplete,tv_winningAmount,tv_result;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_serialNumberComplete=itemView.findViewById(R.id.tv_serialNumberComplete);
            tv_bettingNameComplete=itemView.findViewById(R.id.tv_bettingNameComplete);
            tv_entryFeeComplete=itemView.findViewById(R.id.tv_entryFeeComplete);
            tv_rateComplete=itemView.findViewById(R.id.tv_rateComplete);
            tv_winningAmount=itemView.findViewById(R.id.tv_winningAmount);
            tv_result=itemView.findViewById(R.id.tv_result);

        }
    }

}
