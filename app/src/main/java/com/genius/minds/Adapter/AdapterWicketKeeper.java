package com.genius.minds.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Model.ModelWicketKeeper;
import com.genius.minds.R;

import java.util.List;

public class AdapterWicketKeeper extends RecyclerView.Adapter<AdapterWicketKeeper.AdapterViewHolder> {
   List<ModelWicketKeeper> model_banners;
   Context context;

   public AdapterWicketKeeper(List<ModelWicketKeeper> model_banners, Context context) {
      this.model_banners = model_banners;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_wicketkeeper,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_teamName.setText(model_banners.get(position).getTeamName());
      holder.tv_playerName.setText(model_banners.get(position).getPlayerName());
      holder.tv_selectBy.setText(model_banners.get(position).getSelectBy());
      holder.tv_points.setText(model_banners.get(position).getPoints());
      holder.tv_credit.setText(model_banners.get(position).getCredits());


   }

   @Override
   public int getItemCount() {
      return model_banners.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView tv_teamName,tv_playerName,tv_selectBy,tv_points,tv_credit;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_teamName=itemView.findViewById(R.id.tv_teamName);
         tv_playerName=itemView.findViewById(R.id.tv_playerName);
         tv_selectBy=itemView.findViewById(R.id.tv_selectBy);
         tv_points=itemView.findViewById(R.id.tv_points);
         tv_credit=itemView.findViewById(R.id.tv_credit);

      }
   }

}
