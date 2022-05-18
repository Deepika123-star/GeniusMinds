package com.genius.minds.matches;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.genius.minds.Activity.AllCategoryActivity;
import com.genius.minds.Model.ResponseListMatchs.MatchListItem;
import com.genius.minds.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewMatchAdapter extends RecyclerView.Adapter<NewMatchAdapter.AdapterViewHolder> {
   List<MatchListItem> model_banners;
   Context context;

   public NewMatchAdapter(List<MatchListItem> model_banners, Context context) {
      this.model_banners = model_banners;
      this.context = context;
   }

   @NonNull
   @Override
   public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflate=LayoutInflater.from(parent.getContext());
      View view=layoutInflate.inflate(R.layout.layout_newcardmatch,null);
      return new AdapterViewHolder(view);

   }

   @Override
   public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
      holder.tv_seriesName.setText(model_banners.get(position).getSeries());
      holder.tv_teamOne.setText(model_banners.get(position).getTeam1());
      holder.tv_teamTwo.setText(model_banners.get(position).getTeam2());
      holder.tv_seriesDate.setText(model_banners.get(position).getMdate());
//      holder.tv_seriesNamesTime.setText(model_banners.get(position).getMtime());
      Picasso.get().load(model_banners.get(position).getFLogo()).into(holder.img_teamOne);
      Picasso.get().load(model_banners.get(position).getSLogo()).into(holder.img_teamTwo);

      String date1 =model_banners.get(position).getMdate();
      String time =model_banners.get(position).getMtime();
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





      holder.cv_first.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent intent=new Intent(context, AllCategoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            intent.putExtra("id1",model_banners.get(position).getId());
            intent.putExtra("category",model_banners.get(position).getCategory());
            intent.putExtra("team",model_banners.get(position).getTeam1()+" VS "+model_banners.get(position).getTeam2());
            intent.putExtra("team_one",model_banners.get(position).getTeam1());
            intent.putExtra("team_two",model_banners.get(position).getTeam2());
            intent.putExtra("series",model_banners.get(position).getSeries());
            intent.putExtra("matchcode",model_banners.get(position).getMatchcode());
            intent.putExtra("matchtime",model_banners.get(position).getMtime());
            intent.putExtra("matchdate",model_banners.get(position).getMdate());
            intent.putExtra("logo1",model_banners.get(position).getFLogo());
            intent.putExtra("logo2",model_banners.get(position).getSLogo());
            context.startActivity(intent);
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
      return model_banners.size();
   }

   public class AdapterViewHolder extends RecyclerView.ViewHolder{
      CircleImageView img_teamOne,img_teamTwo,img_panther1,img_panther2;
      CardView cv_first;
      CountDownTimer timer;

      TextView tv_seriesName,tv_teamOne,tv_teamTwo,timeleft,tv_seriesDate;
      public AdapterViewHolder(@NonNull View itemView) {
         super(itemView);
         tv_seriesName=itemView.findViewById(R.id.tv_seriesName);
         tv_teamOne=itemView.findViewById(R.id.tv_teamOne);
         tv_teamTwo=itemView.findViewById(R.id.tv_teamTwo);
         img_teamOne=itemView.findViewById(R.id.img_teamOne);
         img_teamTwo=itemView.findViewById(R.id.img_teamTwo);
//         tv_seriesNamesTime=itemView.findViewById(R.id.tv_seriesNamesTime);
         cv_first=itemView.findViewById(R.id.cv_first);
         timeleft=itemView.findViewById(R.id.timeleft);
         tv_seriesDate=itemView.findViewById(R.id.tv_seriesDate);

      }
   }

}
