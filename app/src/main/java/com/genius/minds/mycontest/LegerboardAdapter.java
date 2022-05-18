package com.genius.minds.mycontest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Inerfaces.InterfaceLeaderBoard;
import com.genius.minds.R;
import com.genius.minds.mycontest.complete.Activity.WinningAmountReferalCommissionActivity;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;

public class LegerboardAdapter extends RecyclerView.Adapter<LegerboardAdapter.ProductViewHolder>{


    private Context mCtx;
    private List<LegerboardModel> productList;
   InterfaceLeaderBoard interfaceLeaderBoard;

    public LegerboardAdapter(Context mCtx, List<LegerboardModel> productList,InterfaceLeaderBoard interfaceLeaderBoard) {
        this.mCtx = mCtx;
        this.productList = productList;
        this.interfaceLeaderBoard = interfaceLeaderBoard;
    }

    @Override
    public LegerboardAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // LayoutInflater inflater = LayoutInflater.from(mCtx);
       // View view = inflater.inflate(R.layout.card_legerboard, null);

        View view =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_legerboard, parent, false);
        return new LegerboardAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LegerboardAdapter.ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final LegerboardModel product = productList.get(position);

        holder.username.setText(product.getUsername());
        holder.winstatus.setText(product.getWinstatus());
        holder.winamount.setText(product.getWinamount());

        if (product.getWinstatus().equals("You win"))
            holder.winstatus.setTextColor(Color.RED);



        String contestis= String.valueOf(productList.get(position).getContestid());

          String userrEmailx=product.getUseremail();
        interfaceLeaderBoard.onRowClick(userrEmailx);
//        Toast.makeText(mCtx, ""+userrEmailx, Toast.LENGTH_SHORT).show();

        holder.btb_referalCommission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, WinningAmountReferalCommissionActivity.class);
                intent.putExtra("contest_ids", contestis);
                intent.putExtra("email_idUser", product.getUseremail());
                intent.putExtra("matchcode", productList.get(position).getMatchcode());
                mCtx.startActivity(intent);
            }
        });
//        Toast.makeText(mCtx, ""+contestis, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

       ElasticButton btb_referalCommission;
        TextView winamount,winstatus,username;
        public ProductViewHolder(View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            winstatus=itemView.findViewById(R.id.winstatus);
            winamount=itemView.findViewById(R.id.winamount);
            btb_referalCommission=itemView.findViewById(R.id.btb_referalCommission);
        }
    }

}
