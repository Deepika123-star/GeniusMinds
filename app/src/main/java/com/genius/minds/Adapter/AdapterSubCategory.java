package com.genius.minds.Adapter;

import android.annotation.SuppressLint;
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

public class AdapterSubCategory extends RecyclerView.Adapter<AdapterSubCategory.AdapterViewHolder> {
   List<SubcategoryListItem> model_banners;
   Context context;

   public AdapterSubCategory(List<SubcategoryListItem> model_banners, Context context) {
      this.model_banners = model_banners;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.card_subcategory,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
      holder.subcatname.setText(model_banners.get(position).getSubcat());
      holder.rel.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(context, MatchContest.class);
            intent.putExtra("category",model_banners.get(position).getCategory());
            intent.putExtra("subcategory",model_banners.get(position).getSubcat());
            intent.putExtra("team_one",model_banners.get(position).getTeam1());
            intent.putExtra("team_two",model_banners.get(position).getTeam2());
            intent.putExtra("matchCode",model_banners.get(position).getMatchcode());
            intent.putExtra("series",model_banners.get(position).getSeries());

            context.startActivity(intent);
         }
      });


   }
   @Override
   public int getItemCount() {
      return model_banners.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView subcatname;
      CardView rel;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         subcatname=itemView.findViewById(R.id.subcatname);
         rel=itemView.findViewById(R.id.rel);

      }
   }

}
