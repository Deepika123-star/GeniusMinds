package com.genius.minds.matches;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.R;
import com.genius.minds.subcategory.BettingCategory;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ProductViewHolder>{


    private Context mCtx;
    private List<MatchModel> productList;

    String path="https://geniusbetting.in/admin/upload/";

    public MatchAdapter(Context mCtx, List<MatchModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.card_match, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final MatchModel product = productList.get(position);
        holder.teamname.setText(product.getTeam1()+" VS "+product.getTeam2());
        holder.series.setText(product.getSeries());
        Picasso.get().load(path+product.getF_logo()).into(holder.f_logo);
        Picasso.get().load(path+product.getS_logo()).into(holder.s_logo);

        String date1 =product.getMdate();
        String time=product.getMtime();
        System.out.println("Date "+date1+" Time "+time);
        String startDate = date1 +" "+time+":00";
        final List<Long> list = findDifference(startDate);
        if (holder.timer != null) {
            holder.timer.cancel();
        }
        try {
            int FlashCount = (int) (list.get(0)*31536000)+(int) (list.get(1)*86400)+(int) (list.get(2)*3600)+(int) (list.get(3)*60);
            final long millisUntilFinished = FlashCount * 1000;
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

                    holder.timeleft.setText("Live");

                }

            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (product.getSubcatdisstatus() == 0)
        {
            holder.lin.setBackgroundColor(Color.LTGRAY);

        }else {
            holder.rel1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, BettingCategory.class);
                    intent.putExtra("category",product.getCategory());
                    intent.putExtra("team",product.getTeam1()+" VS "+product.getTeam2());
                    intent.putExtra("series",product.getSeries());
                    intent.putExtra("matchcode",product.getMatchcode());
                    intent.putExtra("timeleft",product.getMdate()+"  "+product.getMtime());
                    intent.putExtra("logo1",path+product.getF_logo());
                    intent.putExtra("logo2",path+product.getS_logo());
                    mCtx.startActivity(intent);
                }
            });
        }

    }
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
       ImageView f_logo,s_logo;
       TextView teamname,timeleft,series;
       LinearLayout lin;

        CardView rel1;
        public ProductViewHolder(View itemView) {
            super(itemView);
            rel1=itemView.findViewById(R.id.rel);
            f_logo=itemView.findViewById(R.id.f_logo);
            s_logo=itemView.findViewById(R.id.s_logo);
            teamname=itemView.findViewById(R.id.teamname);
            timeleft=itemView.findViewById(R.id.timeleft);
            series=itemView.findViewById(R.id.series);
            lin=itemView.findViewById(R.id.lin);

        }
    }

}
