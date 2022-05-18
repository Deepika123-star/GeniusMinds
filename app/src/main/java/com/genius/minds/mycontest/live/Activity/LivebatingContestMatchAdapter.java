package com.genius.minds.mycontest.live.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Activity.LiveBetting.ShowMyBetActivity;
import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.HelperClass.SessionManager;
import com.genius.minds.HelperClass.SharedPrefManager;
import com.genius.minds.HelperClass.User;
import com.genius.minds.R;
import com.genius.minds.common;
import com.genius.minds.mycontest.live.LiveBatingContestModel;
import com.skydoves.elasticviews.ElasticButton;

import java.util.List;

public class LivebatingContestMatchAdapter extends RecyclerView.Adapter<LivebatingContestMatchAdapter.ProductViewHolder> {


    List<LiveBatingContestModel> productList;
    private Context context;
    SessionManager session;


    public LivebatingContestMatchAdapter(Context context, List<LiveBatingContestModel> productList) {
        this.productList = productList;
        this.context = context;
    }


    @Override
    public LivebatingContestMatchAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_live_contest_join, null);
        return new LivebatingContestMatchAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LivebatingContestMatchAdapter.ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.contestname.setText(productList.get(position).getContest_name());
        holder.team1.setText(productList.get(position).getTeam1());
        holder.fTeamRate.setText("Bet Rate : " + " \u20B9 " + productList.get(position).getF_betting_rate());
        holder.team2.setText(productList.get(position).getTeam2());
        holder.sTeamRate.setText("Bet Rate : " + " \u20B9 " + productList.get(position).getS_betting_rate());


        holder.tv_seriesNameLive.setText(productList.get(position).getSeries());
        holder.tv_dateLive.setText(productList.get(position).getMdate());
        holder.tv_teamOneWinningAmount.setText("Winning Amount : " + " \u20B9 " + common.multiply("0", productList.get(position).getF_betting_rate()));
        holder.tv_teamTwoWinningAmount.setText("Winning Amount : " + " \u20B9 " + common.multiply("0", productList.get(position).getS_betting_rate()));
        holder.sTeamEnterAmount.setVisibility(View.GONE);
        holder.fTeamEnterAmount.setVisibility(View.GONE);
        holder.fTeamJoinButton.setVisibility(View.GONE);
        holder.sTeamJoinButton.setVisibility(View.GONE);
        holder.tv_teamOneWinningAmount.setVisibility(View.GONE);
        holder.tv_teamTwoWinningAmount.setVisibility(View.GONE);
        holder.fTeamEnterAmount.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (!holder.fTeamEnterAmount.getText().toString().equalsIgnoreCase("")) {
                    String result = common.multiply(holder.fTeamEnterAmount.getText().toString(), productList.get(position).getF_betting_rate());
                    holder.tv_teamOneWinningAmount.setText("Winning Amount : " + " \u20B9 " + result);

                } else {
                    String result = common.multiply("0", productList.get(position).getF_betting_rate());
                    holder.tv_teamOneWinningAmount.setText("Winning Amount : " + " \u20B9 " + result);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }
        });
        holder.sTeamEnterAmount.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if (!holder.sTeamEnterAmount.getText().toString().equalsIgnoreCase("")) {
                    String result = common.multiply(holder.sTeamEnterAmount.getText().toString(), productList.get(position).getS_betting_rate());
                    holder.tv_teamTwoWinningAmount.setText("Winning Amount : " + " \u20B9 " + result);
                } else {
                    String result = common.multiply("0", productList.get(position).getF_betting_rate());
                    holder.tv_teamTwoWinningAmount.setText("Winning Amount : " + " \u20B9 " + result);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });


        SharedPreferences pref = context.getSharedPreferences("simplifiedcodingsharedpref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        session = new SessionManager(context);
        User user = SharedPrefManager.getInstance(context).getUser();
        String Email_address = user.getEmail();
        holder.fTeamJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.fTeamEnterAmount.getText().toString().trim().isEmpty()) {
                    Toast.makeText(context, "Please Enter Amount", Toast.LENGTH_SHORT).show();

                } else if (UtilMethods.INSTANCE.isNetworkAvailable(context)) {
                    UtilMethods.INSTANCE.liveAllMatchListss(context,
                            productList.get(position).getMatchcode(),
                            productList.get(position).getF_betting_rate(),
                            holder.sTeamEnterAmount.getText().toString(),
                            productList.get(position).getId(),
                            Email_address,
                            productList.get(position).getSeries());
                    holder.fTeamEnterAmount.setText("");
                    holder.sTeamEnterAmount.setText("");
                    Intent intent = new Intent(context, ShowMyBetActivity.class);
                    intent.putExtra("contest_id", productList.get(position).getId());
                    intent.putExtra("matchCode", productList.get(position).getMatchcode());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.NOCONN));
                }
            }
        });

        holder.sTeamJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.sTeamEnterAmount.getText().toString().trim().isEmpty()) {
                    Toast.makeText(context, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                } else if (UtilMethods.INSTANCE.isNetworkAvailable(context)) {
                    UtilMethods.INSTANCE.liveAllMatchListss(context,
                            productList.get(position).getMatchcode(),
                            productList.get(position).getS_betting_rate(),
                            holder.sTeamEnterAmount.getText().toString(),
                            productList.get(position).getId(),
                            Email_address,
                            productList.get(position).getSeries());
                    holder.fTeamEnterAmount.setText("");
                    holder.sTeamEnterAmount.setText("");
                    Intent intent = new Intent(context, ShowMyBetActivity.class);
                    intent.putExtra("contest_id", productList.get(position).getId());
                    intent.putExtra("matchCode", productList.get(position).getMatchcode());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    UtilMethods.INSTANCE.Error(context, context.getResources().getString(R.string.NOCONN));
                }
            }
        });


        holder.el_showMyBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowMyBetActivity.class);
                intent.putExtra("contest_id", productList.get(position).getId());
                intent.putExtra("matchCode", productList.get(position).getMatchcode());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        if (productList.get(position).getBetting().equalsIgnoreCase("UnLock")) {
            holder.tv_first_lock.setVisibility(View.GONE);
            holder.tv_second_lock.setVisibility(View.GONE);
            holder.fTeamEnterAmount.setEnabled(true);
            holder.sTeamEnterAmount.setEnabled(true);
        } else {
            holder.tv_first_lock.setVisibility(View.VISIBLE);
            holder.tv_second_lock.setVisibility(View.VISIBLE);
            holder.fTeamEnterAmount.setEnabled(false);
            holder.sTeamEnterAmount.setEnabled(false);
        }

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView contestname, team1, tv_seriesNameLive, fTeamRate, team2, sTeamRate, tv_dateLive, tv_teamOneWinningAmount, tv_teamTwoWinningAmount, tv_first_lock, tv_second_lock;
        ElasticButton fTeamJoinButton, sTeamJoinButton, el_showMyBet;
        EditText fTeamEnterAmount, sTeamEnterAmount;

        public ProductViewHolder(View itemView) {
            super(itemView);
            fTeamJoinButton = itemView.findViewById(R.id.fTeamJoinButton);
            sTeamJoinButton = itemView.findViewById(R.id.sTeamJoinButton);
            contestname = itemView.findViewById(R.id.contestname);
            team1 = itemView.findViewById(R.id.team1);
            fTeamRate = itemView.findViewById(R.id.fTeamRate);
            team2 = itemView.findViewById(R.id.team2);
            tv_seriesNameLive = itemView.findViewById(R.id.tv_seriesNameLive);
            sTeamRate = itemView.findViewById(R.id.sTeamRate);
            tv_dateLive = itemView.findViewById(R.id.tv_dateLive);
            fTeamEnterAmount = itemView.findViewById(R.id.fTeamEnterAmount);
            sTeamEnterAmount = itemView.findViewById(R.id.sTeamEnterAmount);
            el_showMyBet = itemView.findViewById(R.id.el_showMyBet);
            tv_teamOneWinningAmount = itemView.findViewById(R.id.tv_teamOneWinningAmount);
            tv_teamTwoWinningAmount = itemView.findViewById(R.id.tv_teamTwoWinningAmount);
            tv_first_lock = itemView.findViewById(R.id.tv_first_lock);
            tv_second_lock = itemView.findViewById(R.id.tv_second_lock);
        }
    }

}

