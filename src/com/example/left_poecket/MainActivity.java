package com.example.left_poecket;

import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new GastoFragment();
	}

   

}
