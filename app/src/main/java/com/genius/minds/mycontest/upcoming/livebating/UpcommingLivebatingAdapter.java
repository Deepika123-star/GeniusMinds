package com.genius.minds.mycontest.upcoming.livebating;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.R;
import com.genius.minds.mycontest.upcoming.UpcomingMatchModel;
import com.genius.minds.mycontest.upcoming.UpcomingSubCategory;
import com.genius.minds.mycontest.upcoming.UpcommingContestActivity;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpcommingLivebatingAdapter extends RecyclerView.Adapter<UpcommingLivebatingAdapter.ProductViewHolder>{


    private Context mCtx;
    private List<UpcommingLivebatingModel> productList;

    //    String path="https://geniusbetting.in/admin/upload/";
//    String path="https://pragatisoulutions.com/geniusbetting/geniusapi/upload/";

    public UpcommingLivebatingAdapter(Context mCtx, List<UpcommingLivebatingModel> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public UpcommingLivebatingAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.upcoming_card_match, null);
        return new UpcommingLivebatingAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UpcommingLivebatingAdapter.ProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final UpcommingLivebatingModel product = productList.get(position);
        holder.tv_teamOne.setText(product.getTeam1());
        holder.tv_teamTwo.setText(product.getTeam2());
        // holder.timeleft.setText(product.getMtime());
        holder.tv_seriesName.setText(product.getSeries());
        holder.tv_seriesDate.setText(product.getMdate());
        Picasso.get().load(product.getF_logo()).into(holder.img_teamOne);
//        Toast.makeText(mCtx, ""+holder.img_teamOne, Toast.LENGTH_SHORT).show();
        Picasso.get().load(product.getS_logo()).into(holder.img_teamTwo);
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
                    holder.timeleft.setText("Live");
                }

            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.rel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(mCtx, UpcommingAllCatgoryActivity.class);
//
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                intent.putExtra("id1",productList.get(position).getId());
//                intent.putExtra("category",productList.get(position).getCategory());
//                intent.putExtra("team",productList.get(position).getTeam1()+" VS "+productList.get(position).getTeam2());
//                intent.putExtra("series",productList.get(position).getSeries());
//                intent.putExtra("matchcode",productList.get(position).getMatchcode());
//                intent.putExtra("matchtime",productList.get(position).getMtime());
//                intent.putExtra("matchdate",productList.get(position).getMdate());
//                intent.putExtra("logo1",path+productList.get(position).getF_logo());
//                intent.putExtra("logo2",path+productList.get(position).getS_logo());
//                mCtx.startActivity(intent);
                Intent intent=new Intent(mCtx, UpcommingContestActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.putExtra("categoryNew",productList.get(position).getCategory());
                intent.putExtra("newtem1",productList.get(position).getTeam1()+" VS "+productList.get(position).getTeam2());
                intent.putExtra("id12",productList.get(position).getId());
                intent.putExtra("series1",productList.get(position).getSeries());
                intent.putExtra("matchcodeone",productList.get(position).getMatchcode());
                intent.putExtra("matchtime1",productList.get(position).getMtime());
                intent.putExtra("matchdate1",productList.get(position).getMdate());
                intent.putExtra("logo11",productList.get(position).getF_logo());
                intent.putExtra("logo12",productList.get(position).getS_logo());
                mCtx.startActivity(intent);

            }
        });
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
        CircleImageView img_teamOne,img_teamTwo;
        TextView tv_teamOne,timeleft,tv_seriesName,tv_teamTwo,tv_seriesDate;
        CardView rel1;
        public ProductViewHolder(View itemView) {
            super(itemView);
            rel1=itemView.findViewById(R.id.cv_first);
            img_teamOne=itemView.findViewById(R.id.img_teamOne);
            img_teamTwo=itemView.findViewById(R.id.img_teamTwo);
            tv_teamOne=itemView.findViewById(R.id.tv_teamOne);
            tv_teamTwo=itemView.findViewById(R.id.tv_teamTwo);
            timeleft=itemView.findViewById(R.id.timeleft);
            tv_seriesName=itemView.findViewById(R.id.tv_seriesName);
            tv_seriesDate=itemView.findViewById(R.id.tv_seriesDate);

        }
    }

}