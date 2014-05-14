package com.example.left_poecket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class DetallesActivity extends SingleFragmentActivity {

	
	
	/*
	String y = intent.getStringExtra("mes");
	*/
	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		DetallesFragment df = new DetallesFragment();
		
		Intent intent = getIntent();
		String x = intent.getStringExtra("mes");		

		df.setMonth(x);
		return df;
	}

}
