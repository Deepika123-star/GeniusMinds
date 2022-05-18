package com.genius.minds.utills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.R;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ProductViewHolder>{

    private Context mCtx;

    private List<NotificationModel> productList;
    public NotificationAdapter(Context mCtx, List<NotificationModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public NotificationAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_notification,parent,false);


        return new NotificationAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotificationAdapter.ProductViewHolder holder, int position) {
        final NotificationModel product = productList.get(position);
        holder.title.setText(product.getTitle());
        holder.message.setText(product.getMessage());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView title,message;

        public ProductViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            message=itemView.findViewById(R.id.message);


        }
    }

}
