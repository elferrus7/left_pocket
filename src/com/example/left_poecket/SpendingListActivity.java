package com.example.left_poecket;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class SpendingListActivity extends FragmentActivity {
	SpendingPageAdapter mPagerAdapter;
	 @Override
	 protected void onCreate(Bundle savedInstance){
		 super.onCreate(savedInstance);
		 setContentView(R.layout.activity_spending_pager);
		 List<Fragment> fragments = getFragments();
		 mPagerAdapter = new SpendingPageAdapter(getSupportFragmentManager(),fragments);
		 ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		 pager.setAdapter(mPagerAdapter);
	 }
	 
	 private List<Fragment> getFragments(){
		 List<Fragment> fragments = new ArrayList<Fragment>();
		 Calendar today = Calendar.getInstance();
		 int aux = today.get(today.MONTH);
		 Log.d("tag","today: "+ today.toString());
		 for(int i = 0; i < 12; i++){
			 Log.d("tag","Monht: " + today.get(today.MONTH)+ " aunx: " + aux + " i: " + i);
			 if(aux >= 12)
				 aux = 1;
			 SpendingListFragment f = new SpendingListFragment();
			 f.setMonth(aux);
			 fragments.add(f);
			 aux++;
		 }
		 return fragments;
	 }
	 
	 public class SpendingPageAdapter extends FragmentPagerAdapter {
		 private List<Fragment> fragments;
		 public SpendingPageAdapter (FragmentManager fm, List<Fragment> fragments){
			 super(fm);
			 this.fragments = fragments;
		 }
		 @Override
		 public Fragment getItem(int position){
			 return this.fragments.get(position);
		 }
		 
		 @Override
		 public int getCount(){
			 return this.fragments.size();
		 }
	 }
}
