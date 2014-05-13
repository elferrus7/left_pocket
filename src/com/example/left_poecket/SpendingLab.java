package com.example.left_poecket;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

public class SpendingLab {
	private static final String TAG = "SpendingLab";
    private static final String FILENAME = "spendings3.json";
    
    private ArrayList<Spending> mSpendings;
    private SpendingJSONSerializer mSerializer;
    
    private static SpendingLab sSpendingLab;
    private Context mAppContext;
    
    private SpendingLab(Context appContext){
    	mAppContext = appContext;
    	mSerializer = new SpendingJSONSerializer(mAppContext, FILENAME);
    	
    	try {
    		mSpendings = mSerializer.loadSpendings();
    	} catch (Exception e){
    		Log.e(TAG,"Error Loading spendings",e);
    	}
    }
    
    public static SpendingLab get(Context c){
    	if(sSpendingLab == null){
    		sSpendingLab = new SpendingLab(c.getApplicationContext());
    	}
    	return sSpendingLab;
    }
    
    public void addSpending(Spending s){
    	mSpendings.add(s);
    	saveSpendings();
    }
    
    public ArrayList<Spending> getSpendings(){
    	return mSpendings;
    }
    public boolean saveSpendings(){
    	try {
    		mSerializer.saveSpendings(mSpendings);
    		Log.d(TAG,"Spendings saved");
    		return true;
    	} catch (Exception e){
    		Log.e(TAG,"Error saving spendings: ", e);
    		return false;
    	}
    }
}
