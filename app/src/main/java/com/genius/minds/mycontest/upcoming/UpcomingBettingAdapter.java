package com.genius.minds.mycontest.upcoming;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.R;


import java.util.List;

public class UpcomingBettingAdapter extends RecyclerView.Adapter<UpcomingBettingAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<UpcomingSubCatModel> productList;

//    String path="https://geniusbetting.in/admin/upload/";
    String path="https://pragatisoulutions.com/geniusbetting/admin/upload/";


    public UpcomingBettingAdapter(Context mCtx, List<UpcomingSubCatModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.upcoming_card_subcategory, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final UpcomingSubCatModel product = productList.get(position);


            holder.subcatname.setText(product.getSub_cat());

        holder.rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, UpcomingMatchContest.class);
                intent.putExtra("category",productList.get(position).getCat_name());
                intent.putExtra("subcategory",productList.get(position).getSub_cat());
//                intent.putExtra("team_one",productList.get(position).get());
//                intent.putExtra("team_two",productList.get(position).getTeam2());
                intent.putExtra("matchcode",productList.get(position).getMatchcode());
                intent.putExtra("series",productList.get(position).getSeries());
                mCtx.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

       TextView subcatname;
       CardView rel1;
        public ProductViewHolder(View itemView) {
            super(itemView);
            subcatname=itemView.findViewById(R.id.subcatname);
            rel1=itemView.findViewById(R.id.rel);

        }
    }

}
