package com.genius.minds.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.genius.minds.Activity.CreateTeamActivity;
import com.genius.minds.Helper.BaseFragment;
import com.genius.minds.R;
import com.skydoves.elasticviews.ElasticButton;

public class FantasyContestFragment extends BaseFragment {
    ElasticButton bt_CreateTeam;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fantasy_contest, container, false);
        bt_CreateTeam=view.findViewById(R.id.bt_CreateTeam);
        bt_CreateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CreateTeamActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreatedStuff(View view, @Nullable Bundle savedInstanceState) {

    }
}