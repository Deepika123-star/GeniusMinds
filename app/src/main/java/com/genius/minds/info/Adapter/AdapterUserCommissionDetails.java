package com.genius.minds.info.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Model.ResponseUserCommissionDetails.CommissionDetailsItem;
import com.genius.minds.Network.API_Config;
import com.genius.minds.Network.ApiServices;
import com.genius.minds.R;
import com.genius.minds.Utils.CustomLoader;
import com.genius.minds.info.ContestDetailActivity;
import com.genius.minds.info.Modeles.ResponseCommissionAmounts.ResponseCommissionAmount;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterUserCommissionDetails extends RecyclerView.Adapter<AdapterUserCommissionDetails.AdapterViewHolder> {
   List<CommissionDetailsItem> commissionDataItems;
   Context context;

   public AdapterUserCommissionDetails(List<CommissionDetailsItem> commissionDataItems, Context context) {
      this.commissionDataItems = commissionDataItems;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_newusercommission,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_serialNumberCommission.setText(commissionDataItems.get(position).getSn());
      holder.tv_dateCommission.setText(commissionDataItems.get(position).getDate());
      holder.tv_categoryCommission.setText(commissionDataItems.get(position).getCategory());
      holder.tv_seriesNumber.setText(commissionDataItems.get(position).getSeries());
      holder.tv_subCategoryCommission.setText(commissionDataItems.get(position).getSubCategory());
      holder.tv_ContestDetailsCommission.setText(commissionDataItems.get(position).getContestDetails());
      holder.tv_winningCommission.setText("\u20B9"+commissionDataItems.get(position).getRefContestWinningAmount());
      holder.tv_refMatchStatus.setText(commissionDataItems.get(position).getRefMatchStatus());
      holder.tv_sectionNew.setText(commissionDataItems.get(position).getSection());
      holder.tv_contestFeeNew.setText("\u20B9"+commissionDataItems.get(position).getEntryFeeCommMy());
      holder.tv_EntryFeeNEew.setText("\u20B9"+commissionDataItems.get(position).getContestFee());
      holder.tv_RefWinAmtMyCommi.setText("\u20B9"+commissionDataItems.get(position).getRefWinAmtMyCommi());
      holder.tv_totalPriceMoney.setText("\u20B9"+commissionDataItems.get(position).getTotalPriceMoney());



//      holder.tv_ContestDetailsCommission.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//            Intent intent= new Intent(context, ContestDetailActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            context.startActivity(intent);
//         }
//      });

   }


   @Override
   public int getItemCount() {
      return commissionDataItems.size();
   }

   public static class AdapterViewHolder extends RecyclerView.ViewHolder{
     static TextView tv_serialNumberCommission,tv_dateCommission,tv_categoryCommission,tv_seriesNumber,tv_subCategoryCommission,
              tv_ContestDetailsCommission,tv_winningCommission,tv_refMatchStatus,tv_sectionNew,tv_contestFeeNew,tv_EntryFeeNEew,tv_RefWinAmtMyCommi,tv_totalPriceMoney;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_serialNumberCommission=itemView.findViewById(R.id.tv_serialNumberCommission);
         tv_dateCommission=itemView.findViewById(R.id.tv_dateCommission);
         tv_categoryCommission=itemView.findViewById(R.id.tv_categoryCommission);
         tv_seriesNumber=itemView.findViewById(R.id.tv_seriesNumber);
         tv_subCategoryCommission=itemView.findViewById(R.id.tv_subCategoryCommission);
         tv_ContestDetailsCommission=itemView.findViewById(R.id.tv_ContestDetailsCommission);
         tv_winningCommission=itemView.findViewById(R.id.tv_winningCommission);
         tv_refMatchStatus=itemView.findViewById(R.id.tv_refMatchStatus);
         tv_sectionNew=itemView.findViewById(R.id.tv_sectionNew);
         tv_contestFeeNew=itemView.findViewById(R.id.tv_contestFeeNew);
         tv_EntryFeeNEew=itemView.findViewById(R.id.tv_EntryFeeNEew);
         tv_RefWinAmtMyCommi=itemView.findViewById(R.id.tv_RefWinAmtMyCommi);
         tv_totalPriceMoney=itemView.findViewById(R.id.tv_totalPriceMoney);

      }
   }

}
