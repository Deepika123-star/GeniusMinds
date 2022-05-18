package com.genius.minds.mycontest.live;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LiveCategoryAdapter extends RecyclerView.Adapter<LiveCategoryAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<LiveCategoryModel> productList;

    String path="https://geniusbetting.in/admin/upload/";

    public LiveCategoryAdapter(Context mCtx, List<LiveCategoryModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.live_card_category, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final LiveCategoryModel product = productList.get(position);

        holder.catname.setText(product.getCat_name());
        Picasso.get().load(path+product.getCat_image()).into(holder.catimage);
        holder.rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, LiveMatches.class);
                intent.putExtra("category",product.getCat_name());
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView catimage;
        TextView catname;
        CardView rel1;
        public ProductViewHolder(View itemView) {
            super(itemView);
            catimage=itemView.findViewById(R.id.img_android);
            catname=itemView.findViewById(R.id.tv_android);
            rel1=itemView.findViewById(R.id.rel);
        }
    }

}
