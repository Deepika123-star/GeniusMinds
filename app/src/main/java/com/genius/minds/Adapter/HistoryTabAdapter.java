package com.genius.minds.mycontest;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.genius.minds.Fragment.AddMoneyInWalletFragment;
import com.genius.minds.Fragment.JoinContestFragment;
import com.genius.minds.Fragment.UserRefferalCommissionFragment;
import com.genius.minds.Fragment.WinningAmountFragment;

public class HistoryTabAdapter extends FragmentStatePagerAdapter {
   int mNumOfTabs;
   public HistoryTabAdapter(FragmentManager fm, int NoofTabs){
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
            AddMoneyInWalletFragment aadMoney = new AddMoneyInWalletFragment();
            return aadMoney;
         case 1:
            JoinContestFragment joinContestFragment = new JoinContestFragment();
            return joinContestFragment;
         case 2:
            UserRefferalCommissionFragment refferalCommissionFragment = new UserRefferalCommissionFragment();
            return refferalCommissionFragment;
         case 3:
            WinningAmountFragment winningAmount = new WinningAmountFragment();
            return winningAmount;
         default:
            return null;
      }
   }
}