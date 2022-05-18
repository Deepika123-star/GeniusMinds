package com.genius.minds.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Model.ModelContinue;
import com.genius.minds.R;

import java.util.List;

public class AdapterContinue extends RecyclerView.Adapter<AdapterContinue.AdapterViewHolder> {
   List<ModelContinue> model_banners;
   Context context;

   public AdapterContinue(List<ModelContinue> model_banners, Context context) {
      this.model_banners = model_banners;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_continue,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_playerNameContinue.setText(model_banners.get(position).getPlayerName());
      holder.tv_pointsContinue.setText(model_banners.get(position).getPoints());

   }
   @Override
   public int getItemCount() {
      return model_banners.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView tv_bow,tv_wk,tv_playerNameContinue,tv_pointsContinue,tv_caption,tv_viceCaption,tv_captionPoints,tv_viceCaptionPoints;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_bow=itemView.findViewById(R.id.tv_bow);
         tv_wk=itemView.findViewById(R.id.tv_wk);
         tv_playerNameContinue=itemView.findViewById(R.id.tv_playerNameContinue);
         tv_pointsContinue=itemView.findViewById(R.id.tv_pointsContinue);
         tv_caption=itemView.findViewById(R.id.tv_caption);
         tv_viceCaption=itemView.findViewById(R.id.tv_viceCaption);
         tv_captionPoints=itemView.findViewById(R.id.tv_captionPoints);
         tv_viceCaptionPoints=itemView.findViewById(R.id.tv_viceCaptionPoints);

      }
   }

}
