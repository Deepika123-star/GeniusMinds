package com.genius.minds.mycontest.upcoming.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.R;
import com.genius.minds.mycontest.ViewMoreActivity;
import com.genius.minds.mycontest.upcoming.Model.NewUpcommingModel;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;

public class AdapterNewUpcomming extends RecyclerView.Adapter<AdapterNewUpcomming.AdapterViewHolder> {
    List<NewUpcommingModel> model_banners;
    Context context;

    public AdapterNewUpcomming(List<NewUpcommingModel> model_banners, Context context) {
        this.model_banners = model_banners;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
        View view=layoutInflate.inflate(R.layout.layout_upcomming,null);
        return new AdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.tv_category.setText(model_banners.get(position).getCategory());
        holder.tv_subCategory.setText(model_banners.get(position).getSubCategory());
        holder.tv_matchDate.setText(model_banners.get(position).getMatchDate());
        holder.tv_firstTeam.setText(model_banners.get(position).getFirstTeam());
        holder.tv_firstTeam.setText(model_banners.get(position).getFirstTeam());
        holder.tv_lastTeam.setText(model_banners.get(position).getLastTeam());
        holder.tv_contestName.setText(model_banners.get(position).getContestname());
        holder.tv_date.setText(model_banners.get(position).getDate());
        holder.tv_startTime.setText(model_banners.get(position).getStartTime());
        holder.bt_viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ViewMoreActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                intent.putExtra("cat_name",model_banners.get(position).getName());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return model_banners.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tv_category,tv_subCategory,tv_matchDate,tv_firstTeam,tv_lastTeam,tv_contestName,tv_date,tv_startTime;
        ElasticButton bt_viewMore;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category=itemView.findViewById(R.id.tv_category);
            tv_subCategory=itemView.findViewById(R.id.tv_subCategory);
            tv_matchDate=itemView.findViewById(R.id.tv_matchDate);
            tv_firstTeam=itemView.findViewById(R.id.tv_firstTeam);
            tv_lastTeam=itemView.findViewById(R.id.tv_lastTeam);
            tv_contestName=itemView.findViewById(R.id.tv_contestName);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_startTime=itemView.findViewById(R.id.tv_startTime);
            bt_viewMore=itemView.findViewById(R.id.bt_viewMore);

        }
    }

}
