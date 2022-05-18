package com.genius.minds.mycontest.live;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Activity.UtilMethods;
import com.genius.minds.Model.ResponseTotalPrizeMoneys.ResponseTotalPriizeMoney;
import com.genius.minds.R;
import com.genius.minds.contest.PrizeBreakUpTeamOneActivity;
import com.genius.minds.mycontest.LedgerBoard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skydoves.elasticviews.ElasticButton;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LiveContestAdapter extends RecyclerView.Adapter<LiveContestAdapter.ProductViewHolder>{

    private Context mCtx;
    private List<LiveContestModel> productList;
    public LiveContestAdapter(Context mCtx, List<LiveContestModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public LiveContestAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_contest_upcoming, null);
        return new LiveContestAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LiveContestAdapter.ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final LiveContestModel product = productList.get(position);


        holder.contestname.setText(product.getContest_name());
        holder.team1.setText(product.getFuser());
        holder.fteamfee.setText("Entry Fee : \u20B9" + product.getFteamfee());
        holder.fteamwin.setText("Prize Money : \u20B9"+product.getFteamwin());
        holder.cdate.setText(product.getDate());
        holder.team2.setText(product.getSuser());
        holder.steamfee.setText("Entry Fee : \u20B9"+product.getSteamfee());
        holder.steamwin.setText("Prize Money : \u20B9"+product.getSteamwin());
        holder.fteambonus.setText("Use Bonus : \u20B9"+product.getFteambonus());
        holder.steambonus.setText("Use Bonus : \u20B9"+product.getSteambonus());
        holder.tv_totalPrice.setText("\u20B9"+product.getTotalprizemoney());


        String date1 =product.getDate();
        String time=product.getEndtime();

        System.out.println("Date "+date1+" Time "+time);
        String startDate = date1 +" "+time+":00";
        final List<Long> list = findDifference(startDate);
        if (holder.timer != null) {
            holder.timer.cancel();
        }
        try {
            int FlashCount = (int) (list.get(0)*31536000)+(int) (list.get(1)*86400)+(int) (list.get(2)*3600)+(int) (list.get(3)*60);
            long millisUntilFinished = FlashCount * 1000;
            System.out.println("Time left "+millisUntilFinished);
            holder.timer = new CountDownTimer(millisUntilFinished, 1000) {
                public void onTick(long millisUntilFinished) {
                    long Days = TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
                    long Hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished));
                    long Minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
                    long Seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
                    String format = "%1$02d";
                    holder.timeleft.setText(String.format(format, Days) + ":" + String.format(format, Hours) + ":" + String.format(format, Minutes) + ":" + String.format(format, Seconds)+" left");
                }

                public void onFinish() {
                    holder.timeleft.setText("Contest is Live");
                    //loadProducts(product.getContestid());
                }

            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }



        holder.btn_prizeBreakUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, PrizeBreakUpTeamOneActivity.class);
                intent.putExtra("team",product.getFuser());
                intent.putExtra("fee",productList.get(position).getFteamfee());
                intent.putExtra("bonus",productList.get(position).getSteambonus());
                intent.putExtra("contestid",String.valueOf(productList.get(position).getContestid()));
                mCtx.startActivity(intent);
            }
        });
        holder.btn_prizeBreakUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, PrizeBreakUpTeamOneActivity.class);
                intent.putExtra("team",product.getSuser());
                intent.putExtra("fee",productList.get(position).getSteamfee());
                intent.putExtra("bonus",productList.get(position).getSteambonus());
                intent.putExtra("contestid",String.valueOf(productList.get(position).getContestid()));
                mCtx.startActivity(intent);
            }
        });







        holder.ledgerboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, LedgerBoard.class);
                intent.putExtra("contestid",""+product.getContestid());
                mCtx.startActivity(intent);
            }
        });
    }

   /* private void loadProducts(int id) {

        Log.d("completeupdateurl",LIVE_TO_COMPLETE+id);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LIVE_TO_COMPLETE+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(this.mCtx).add(stringRequest);
    }*/

    public List<Long> findDifference(String end_date)
    {
        List<Long> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String start_date=sdf.format(new Date());
        try {

            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            long difference_In_Time
                    = d2.getTime() - d1.getTime();

            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            list.add(difference_In_Years);
            list.add(difference_In_Days);
            list.add(difference_In_Hours);
            list.add(difference_In_Minutes);
            list.add(difference_In_Seconds);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        CountDownTimer timer;
        TextView contestname,team1,fteamfee,fteamwin,team2,steamfee,steamwin,fteambonus,steambonus,timeleft,cdate,tv_totalPrice;
        Button ledgerboard;

        ElasticButton btn_prizeBreakUp1,btn_prizeBreakUp2;

        public ProductViewHolder(View itemView) {
            super(itemView);

            contestname=itemView.findViewById(R.id.contestname);
            team1=itemView.findViewById(R.id.team1);
            fteamfee=itemView.findViewById(R.id.fteamfee);
            fteamwin=itemView.findViewById(R.id.fteamwin);
            team2=itemView.findViewById(R.id.team2);
            steamfee=itemView.findViewById(R.id.steamfee);
            steamwin=itemView.findViewById(R.id.steamwin);
            ledgerboard=itemView.findViewById(R.id.legerboard);
            fteambonus=itemView.findViewById(R.id.fteambonus);
            steambonus=itemView.findViewById(R.id.steambonus);
            timeleft=itemView.findViewById(R.id.timer);
            cdate=itemView.findViewById(R.id.date);
            btn_prizeBreakUp1=itemView.findViewById(R.id.btn_prizeBreakUp1);
            btn_prizeBreakUp2=itemView.findViewById(R.id.btn_prizeBreakUp2);
            tv_totalPrice=itemView.findViewById(R.id.tv_totalPrice);
        }
    }

}
