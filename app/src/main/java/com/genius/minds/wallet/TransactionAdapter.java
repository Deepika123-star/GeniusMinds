package com.genius.minds.wallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.R;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ProductViewHolder>{


    private Context mCtx;
    private List<TransactionModel> productList;


    public TransactionAdapter(Context mCtx, List<TransactionModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public TransactionAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // LayoutInflater inflater = LayoutInflater.from(mCtx);
        // View view = inflater.inflate(R.layout.card_legerboard, null);

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_transaction, parent, false);
        return new TransactionAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TransactionAdapter.ProductViewHolder holder, int position) {
        final TransactionModel product = productList.get(position);


        if(isNullOrEmpty(product.getMatchname()) || isNullOrEmpty(product.getSubcat()))
        {
            holder.reason.setText(product.getReason());
            holder.amount.setText(product.getAmount());
            if ((product.getStatus())==0 && (product.getReason()).equals("Withdraw Amount"))
            {
                holder.tdate.setText(product.getTdate()+"\n(Pending)");
            }else if((product.getStatus())==1 && (product.getReason()).equals("Withdraw Amount")){
                holder.tdate.setText(product.getTdate()+"\n(Success)");
            }else{
                holder.tdate.setText(product.getTdate());
            }
            holder.matchname.setVisibility(View.INVISIBLE);
            holder.subcat.setVisibility(View.INVISIBLE);
        }else{
            holder.reason.setText(product.getReason());
            holder.amount.setText(product.getAmount());
            holder.matchname.setText("Matchname : "+product.getMatchname());
            holder.subcat.setText("Subcategory : "+product.getSubcat());

            holder.matchname.setVisibility(View.VISIBLE);
            holder.subcat.setVisibility(View.VISIBLE);
            if ((product.getStatus())==0 && (product.getReason()).equals("Withdraw Amount"))
            {
                holder.tdate.setText(product.getTdate()+"\n(Pending)");
            }else if((product.getStatus())==1 && (product.getReason()).equals("Withdraw Amount")){
                holder.tdate.setText(product.getTdate()+"\n(Success)");
            }else{
                holder.tdate.setText(product.getTdate());
            }
        }




    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView reason,amount,tdate,matchname,subcat;
        LinearLayout lin;
        public ProductViewHolder(View itemView) {
            super(itemView);
            reason=itemView.findViewById(R.id.reason);
            amount=itemView.findViewById(R.id.amount);
            tdate=itemView.findViewById(R.id.tdate);
            matchname=itemView.findViewById(R.id.matchname);
            subcat=itemView.findViewById(R.id.subcat);


        }
    }

}
