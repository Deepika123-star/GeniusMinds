package com.genius.minds.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Model.ResponseCategory.CategoryListItem;
import com.genius.minds.R;
import com.genius.minds.matches.NewMatchActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.AdapterViewHolder> {
   List<CategoryListItem> model_banners;
   Context context;

   public AdapterHome(List<CategoryListItem> model_banners, Context context) {
      this.model_banners = model_banners;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_homecategory,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_android.setText(model_banners.get(position).getName());
      Picasso.get().load(model_banners.get(position).getImage()).into(holder.img_android);

      Picasso.get().load(model_banners.get(position).getImage()).placeholder(R.drawable.dream).into(holder.img_android);


      holder.cv_home.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent intent=new Intent(context, NewMatchActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("cat_name",model_banners.get(position).getName());
            context.startActivity(intent);
         }
      });


   }

   @Override
   public int getItemCount() {
      return model_banners.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      ImageView img_android;
      CardView cv_home;
      TextView tv_android;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_android=itemView.findViewById(R.id.tv_android);
         img_android=itemView.findViewById(R.id.img_android);
         cv_home=itemView.findViewById(R.id.cv_home);

      }
   }

}
