package com.genius.minds.contest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.genius.minds.Model.ResponseAllContests.ContestListItem;
import com.genius.minds.R;
import com.skydoves.elasticviews.ElasticButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.genius.minds.Config.MyBaseUrl.UPDATE_CONTEST_STATUS;

public class MatchContestAdapter extends RecyclerView.Adapter<MatchContestAdapter.ProductViewHolder>{


     List<ContestListItem> productList;
    private Context mCtx;

    public MatchContestAdapter(List<ContestListItem> productList,Context mCtx) {
        this.productList = productList;
        this.mCtx = mCtx;
    }


    @Override
    public MatchContestAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_contest_join, null);
        return new MatchContestAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MatchContestAdapter.ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.contestname.setText(productList.get(position).getContestName());
        holder.team1.setText(productList.get(position).getFuser());
        holder.fteamfee.setText("Entry Fee : "+productList.get(position).getFteamfee());
//        holder.fteamwin.setText("Prize Money : "+productList.get(position).getFteamwin());
        holder.team2.setText(productList.get(position).getSuser());
        holder.steamfee.setText("Entry Fee : "+productList.get(position).getSteamfee());
//        holder.steamwin.setText("Prize Money : "+productList.get(position).getSteamwin());
        holder.fteambonus.setText("Use Bonus : "+productList.get(position).getFteambonus());
        holder.steambonus.setText("Use Bonus : "+productList.get(position).getSteambonus());
        holder.cdate.setText(productList.get(position).getDate());
        holder.tv_totalPrice.setText("\u20B9"+productList.get(position).getTotalPriceMoney());

        int userID = Integer.parseInt(productList.get(position).getId());
        holder.totalspot.setText("2 spot");
        int InGetSpot = Integer.parseInt(productList.get(position).getSpot());
        if (2-(InGetSpot)==0)
        {
            holder.remainspot.setText("2 spot left");
        }else if(2-(InGetSpot)==1)
        {
            holder.remainspot.setText("1 spot left");
        }else if (2-(InGetSpot)==2)
        {
            holder.remainspot.setText("contest full");
            holder.totalspot.setText(" ");
            holder.fill1.setBackgroundColor(Color.BLUE);
            holder.fill2.setBackgroundColor(Color.BLUE);
        }

        int rspot=2-InGetSpot;
        if (rspot==1)
            holder.fill1.setBackgroundColor(Color.BLUE);

        int InGetFJoin = Integer.parseInt(productList.get(position).getFjoin());


        if (InGetFJoin==1){
            holder.btnfteamwin.setText("Joined");
            holder.btnfteamwin.setBackgroundResource(R.drawable.disablebground);
            holder.btnfteamwin.setTextColor(Color.BLACK);
            holder.btnfteamwin.setClickable(false);
            holder.btnfteamwin.setEnabled(false);

        }

        int InGetSJoin = Integer.parseInt(productList.get(position).getSjoin());

        if (InGetSJoin==1){
            holder.btnsteamwin.setText("Joined");
            holder.btnsteamwin.setBackgroundResource(R.drawable.disablebground);
            holder.btnsteamwin.setTextColor(Color.BLACK);
            holder.btnsteamwin.setClickable(false);
            holder.btnsteamwin.setEnabled(false);
        }

        String date1 =productList.get(position).getDate();
        String time=productList.get(position).getEndtime();
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
                    holder.timeleft.setText("Contest closed");
                    holder.btnfteamwin.setClickable(false);
                    holder.btnfteamwin.setEnabled(false);
                    holder.btnsteamwin.setClickable(false);
                    holder.btnsteamwin.setEnabled(false);
                    loadProducts(userID);
                }

            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }



        holder.btnfteamwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, ConfirmJoining.class);
                intent.putExtra("team",productList.get(position).getFuser());
                intent.putExtra("fee",productList.get(position).getFteamfee());
                intent.putExtra("bonus",productList.get(position).getFteambonus());
                intent.putExtra("contestid",""+userID);
                mCtx.startActivity(intent);
            }
        });
        holder.btnsteamwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, ConfirmJoining.class);
                intent.putExtra("team",productList.get(position).getSuser());
                intent.putExtra("fee",productList.get(position).getSteamfee());
                intent.putExtra("bonus",productList.get(position).getSteambonus());
                intent.putExtra("contestid",""+userID);
                mCtx.startActivity(intent);
            }
        });
        holder.btn_prizeBreakUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, PrizeBreakUpTeamOneActivity.class);
                intent.putExtra("team",productList.get(position).getFuser());
                intent.putExtra("fee",productList.get(position).getSteamfee());
                intent.putExtra("bonus",productList.get(position).getSteambonus());
                intent.putExtra("contestid",productList.get(position).getId());
                mCtx.startActivity(intent);
            }
        });
        holder.btn_prizeBreakUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, PrizeBreakUpTeamOneActivity.class);
                intent.putExtra("team",productList.get(position).getSuser());
                intent.putExtra("fee",productList.get(position).getSteamfee());
                intent.putExtra("bonus",productList.get(position).getSteambonus());
                intent.putExtra("contestid",productList.get(position).getId());
                mCtx.startActivity(intent);
            }
        });



    }


    private void loadProducts(int id) {


        Log.d("completeupdateurl",UPDATE_CONTEST_STATUS+id);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, UPDATE_CONTEST_STATUS+id,
                new com.android.volley.Response.Listener<String>() {
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
     }


    public List<Long> findDifference(String end_date)
    {
        List<Long> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String start_date=sdf.format(new Date());
        try {

            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);

            long difference_In_Time = d2.getTime() - d1.getTime();

            long difference_In_Seconds = (difference_In_Time / 1000) % 60;

            long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

            long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;

            long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

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
        TextView contestname,team1,fteamfee,tv_totalPrice,fteamwin,team2,steamfee,steamwin,timeleft,fteambonus,steambonus,cdate,fill1,fill2,remainspot,totalspot;
        Button btnfteamwin,btnsteamwin;
        ElasticButton btn_prizeBreakUp1,btn_prizeBreakUp2;
        public ProductViewHolder(View itemView) {
            super(itemView);
            btn_prizeBreakUp1=itemView.findViewById(R.id.btn_prizeBreakUp1);
            btn_prizeBreakUp2=itemView.findViewById(R.id.btn_prizeBreakUp2);
            btnfteamwin=itemView.findViewById(R.id.fteambutton);
            btnsteamwin=itemView.findViewById(R.id.steambutton);
            contestname=itemView.findViewById(R.id.contestname);
            team1=itemView.findViewById(R.id.team1);
            fteamfee=itemView.findViewById(R.id.fteamfee);
//            fteamwin=itemView.findViewById(R.id.fteamwin);
            team2=itemView.findViewById(R.id.team2);
            steamfee=itemView.findViewById(R.id.steamfee);
//            steamwin=itemView.findViewById(R.id.steamwin);
            timeleft=itemView.findViewById(R.id.timer);
            fteambonus=itemView.findViewById(R.id.fteambonus);
            steambonus=itemView.findViewById(R.id.steambonus);
            cdate=itemView.findViewById(R.id.date);
            fill1=itemView.findViewById(R.id.fill1);
            fill2=itemView.findViewById(R.id.fill2);
            remainspot=itemView.findViewById(R.id.remainspot);
            totalspot=itemView.findViewById(R.id.totalspot);
            tv_totalPrice=itemView.findViewById(R.id.tv_totalPrice);

        }
    }

}
