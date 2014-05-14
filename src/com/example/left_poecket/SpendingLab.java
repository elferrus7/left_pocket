package com.example.left_poecket;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.util.Log;

public class SpendingLab {
	private static final String TAG = "SpendingLab";
    private static final String FILENAME = "spendings5.json";
    
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
    public ArrayList<Spending> getSpendingsByMonth(int month){
    	ArrayList<Spending> orderedS = new ArrayList<Spending>();
    	
    	for(Spending s: getSpendings()){
    		Date d = s.getDate();
    		if(d.getMonth() == month){
    			orderedS.add(s);
    		}
    	}
    	
    	return orderedS;
    }
    public ArrayList<Spending> getSpendingsByTypeAndMonth(int month,int type){
    	ArrayList<Spending> orderedS = new ArrayList<Spending>();
    	ArrayList<Spending> auxS = new ArrayList<Spending>();
    	
    	for(Spending s: getSpendings()){
    		Date d = s.getDate();
    		int t = s.getType();
    		if(d.getMonth() == month){
    			if(t == type){
    				orderedS.add(s);
    			}
    		}
    	}
    	
    	
    	return orderedS;
    }
    public ArrayList<Spending> getSpendingsByFormPayment(int form){
    	ArrayList<Spending> orderedS = new ArrayList<Spending>();
    	
    	for(Spending s: getSpendings()){
    		int f = s.getForm_payment();
    		if(f == form){
    			orderedS.add(s);
    		}
    	}
    	
    	return orderedS;
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
