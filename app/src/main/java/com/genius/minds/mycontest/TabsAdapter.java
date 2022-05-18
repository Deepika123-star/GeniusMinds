package com.genius.minds.mycontest;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.genius.minds.mycontest.complete.CompleteCategory;
import com.genius.minds.mycontest.live.LiveCategory;
import com.genius.minds.mycontest.upcoming.UpcomingCategory;

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                UpcomingCategory ongoing = new UpcomingCategory();
                return ongoing;
            case 1:
                LiveCategory live = new LiveCategory();
                return live;
            case 2:
                CompleteCategory complete = new CompleteCategory();
                return complete;
            default:
                return null;
        }
    }
}