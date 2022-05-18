package com.genius.minds.info.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.genius.minds.R;
import com.genius.minds.info.Modeles.ModelContestDetail;

import java.util.List;

public class AdapterContestDetails extends RecyclerView.Adapter<AdapterContestDetails.AdapterViewHolder> {
   List<ModelContestDetail> model_banners;
   Context context;

   public AdapterContestDetails(List<ModelContestDetail> model_banners, Context context) {
      this.model_banners = model_banners;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_contestdetails,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_contestNames.setText(model_banners.get(position).getContestName());
      holder.tv_EntryFee.setText("\u20B9 "+model_banners.get(position).getEntryFee());


   }

   @Override
   public int getItemCount() {
      return model_banners.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView tv_contestNames,tv_EntryFee;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_contestNames=itemView.findViewById(R.id.tv_contestNames);
         tv_EntryFee=itemView.findViewById(R.id.tv_EntryFee);

      }
   }

}
