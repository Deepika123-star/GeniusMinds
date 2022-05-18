package com.genius.minds.info.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.genius.minds.R;
import com.genius.minds.info.Modeles.ModelViewMoreRefer;
import com.genius.minds.info.UserCommissionActivity;

import java.util.List;

public class AdapterReferMore extends RecyclerView.Adapter<AdapterReferMore.AdapterViewHolder> {
   List<ModelViewMoreRefer> model_banners;
   Context context;

   public AdapterReferMore(List<ModelViewMoreRefer> model_banners, Context context) {
      this.model_banners = model_banners;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_referviewmore,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_serialNumber1.setText(model_banners.get(position).getSerialNumber());
      holder.tv_ReferName.setText(model_banners.get(position).getReferName());
      holder.tv_mobileNumber.setText(model_banners.get(position).getMobileNumber());
      holder.tv_joiningDate.setText(model_banners.get(position).getJoiningData());
      holder.tv_userCommission.setText("\u20B9 "+model_banners.get(position).getUserCommission());

   holder.tv_userCommission.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
         Intent intent= new Intent(context, UserCommissionActivity.class);
         intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
         context.startActivity(intent);
      }
   });

   }

   @Override
   public int getItemCount() {
      return model_banners.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      TextView tv_serialNumber1,tv_ReferName,tv_mobileNumber,tv_joiningDate,tv_userCommission;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_serialNumber1=itemView.findViewById(R.id.tv_serialNumber1);
         tv_ReferName=itemView.findViewById(R.id.tv_ReferName);
         tv_mobileNumber=itemView.findViewById(R.id.tv_mobileNumber);
         tv_joiningDate=itemView.findViewById(R.id.tv_joiningDate);
         tv_userCommission=itemView.findViewById(R.id.tv_userCommission);

      }
   }

}
