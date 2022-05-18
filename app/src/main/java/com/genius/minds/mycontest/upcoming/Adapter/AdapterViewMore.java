package com.genius.minds.mycontest.upcoming.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.genius.minds.R;
import com.genius.minds.mycontest.upcoming.Model.ModelViewMore;

import java.util.List;

public class AdapterViewMore extends RecyclerView.Adapter<AdapterViewMore.AdapterViewHolder> {
    List<ModelViewMore> model_banners;
    Context context;

    public AdapterViewMore(List<ModelViewMore> model_banners, Context context) {
        this.model_banners = model_banners;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
        View view=layoutInflate.inflate(R.layout.layout_viewmore,null);
        return new AdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.tv_serialNumber.setText(model_banners.get(position).getSerialNumber());
        holder.tv_bettingName.setText(model_banners.get(position).getBettingName());
        holder.tv_entryFee.setText("\u20B9 "+model_banners.get(position).getEntryFee());
        holder.tv_rate.setText("\u20B9 "+model_banners.get(position).getRate());
        holder.tv_priceMoney.setText("\u20B9 "+model_banners.get(position).getPriceMoney());



    }

    @Override
    public int getItemCount() {
        return model_banners.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tv_serialNumber,tv_bettingName,tv_entryFee,tv_rate,tv_priceMoney;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_serialNumber=itemView.findViewById(R.id.tv_serialNumber);
            tv_bettingName=itemView.findViewById(R.id.tv_bettingName);
            tv_entryFee=itemView.findViewById(R.id.tv_entryFee);
            tv_rate=itemView.findViewById(R.id.tv_rate);
            tv_priceMoney=itemView.findViewById(R.id.tv_priceMoney);

        }
    }

}
