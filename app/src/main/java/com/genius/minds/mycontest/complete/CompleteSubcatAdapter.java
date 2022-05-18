package com.genius.minds.mycontest.complete;

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

public class CompleteSubcatAdapter extends RecyclerView.Adapter<CompleteSubcatAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<CompleteSubCatModel> productList;

    String path="https://geniusbetting.in/admin/upload/";

    public CompleteSubcatAdapter(Context mCtx, List<CompleteSubCatModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.complete_card_subcategory, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final CompleteSubCatModel product = productList.get(position);


            holder.subcatname.setText(product.getSub_cat());

        holder.rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, CompleteContest.class);
                intent.putExtra("category",product.getCat_name());
                intent.putExtra("subcategory",product.getSub_cat());
                intent.putExtra("matchcode",product.getMatchcode());
                intent.putExtra("series",product.getSeries());
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
