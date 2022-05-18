package com.genius.minds.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.genius.minds.Adapter.AdapterWicketKeeper;
import com.genius.minds.Helper.BaseFragment;
import com.genius.minds.Model.ModelWicketKeeper;
import com.genius.minds.R;

import java.util.ArrayList;


public class AllRounderFragment extends BaseFragment {
    RecyclerView rec_allRounder;
    AdapterWicketKeeper adapterWicketKeeper;
    ArrayList<ModelWicketKeeper> modelWicketKeepers;
    String [] teamName={"SA","IND","PAK","AUS","SRI","ZIM","SA","IND","PAK","AUS","SRI","ZIM","SA","IND","PAK"};
    String [] PlayerName={"V Kohali","Q Kock","J Buttler","J Baristow","V Kohali","Q Kock","J Buttler","Cricket","J Buttler","J Buttler",
            "V Kohali","Q Kock","J Buttler","J Baristow","V Kohali"};
    String [] selectBy={"Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%","Sel by 26.83%"};
    String [] point={"705","109","987","212","765","234","433","109","655","977","103","150","708","786","109"};
    String [] credit={"10.5","6.70","6.60","9.90","1.01","2.09","5.09","4.09","40.0","6.00","7.09","8.09","9.09","1.09","5.90"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_rounder, container, false);
        rec_allRounder=view.findViewById(R.id.rec_allRounder);
        RecyclerView.LayoutManager vertical = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
        rec_allRounder.setLayoutManager(vertical);
        rec_allRounder.setItemAnimator(new DefaultItemAnimator());
        modelWicketKeepers = new ArrayList<>();
        for (int i = 0; i < PlayerName.length; i++) {
            ModelWicketKeeper ab = new ModelWicketKeeper(PlayerName[i],teamName[i],selectBy[i],point[i],credit[i]);
            modelWicketKeepers.add(ab);
        }
        adapterWicketKeeper = new AdapterWicketKeeper(modelWicketKeepers,context);
        rec_allRounder.setAdapter(adapterWicketKeeper);

        return view;
    }

    @Override
    public void onViewCreatedStuff(View view, @Nullable Bundle savedInstanceState) {

    }
}