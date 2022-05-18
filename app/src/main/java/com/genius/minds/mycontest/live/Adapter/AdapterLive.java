package com.genius.minds.mycontest.live.Adapter;

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
import com.genius.minds.mycontest.live.Adapter.ModelLive.LiveModel;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;

public class AdapterLive extends RecyclerView.Adapter<AdapterLive.AdapterViewHolder> {
    List<LiveModel> model_banners;
    Context context;

    public AdapterLive(List<LiveModel> model_banners, Context context) {
        this.model_banners = model_banners;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
        View view=layoutInflate.inflate(R.layout.layout_live,null);
        return new AdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.tv_categoryScore.setText(model_banners.get(position).getCategoryScore());
        holder.tv_subCategoryScore.setText(model_banners.get(position).getSubCategoryScore());
        holder.tv_matchDateScore.setText(model_banners.get(position).getMatchDateScore());
        holder.tv_firstTeamNameScore.setText(model_banners.get(position).getFirstTeamNameScore());
        holder.tv_lastTeamScore.setText(model_banners.get(position).getLastTeamNameScore());
        holder.tv_contestNameScore.setText(model_banners.get(position).getContestnameScore());
        holder.tv_dateScore.setText(model_banners.get(position).getDateScore());
        holder.tv_viewFullScore.setText(model_banners.get(position).getViewFullRecord());
        holder.bt_viewMoreScore.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_categoryScore,tv_subCategoryScore,tv_matchDateScore,tv_firstTeamNameScore,tv_lastTeamScore,tv_contestNameScore,tv_dateScore,tv_viewFullScore;
        ElasticButton bt_viewMoreScore;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_categoryScore=itemView.findViewById(R.id.tv_categoryScore);
            tv_subCategoryScore=itemView.findViewById(R.id.tv_subCategoryScore);
            tv_matchDateScore=itemView.findViewById(R.id.tv_matchDateScore);
            tv_firstTeamNameScore=itemView.findViewById(R.id.tv_firstTeamNameScore);
            tv_lastTeamScore=itemView.findViewById(R.id.tv_lastTeamScore);
            tv_contestNameScore=itemView.findViewById(R.id.tv_contestNameScore);
            tv_dateScore=itemView.findViewById(R.id.tv_dateScore);
            tv_viewFullScore=itemView.findViewById(R.id.tv_viewFullScore);
            bt_viewMoreScore=itemView.findViewById(R.id.bt_viewMoreScore);

        }
    }

}
