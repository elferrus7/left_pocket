package com.example.left_poecket;


import android.support.v4.app.Fragment;

public class SpendingListActivity extends SingleFragmentActivity {
	
	 @Override
	 protected Fragment createFragment() {
	        return new SpendingListFragment();
	 }
}
