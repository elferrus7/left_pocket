package com.example.left_poecket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Spending {
	private double mAmount;
	private int mType; //Ingreo o egreso
	private int mFormPayment;
	public Date mDate;
	private String mComment;
	
	
	private static final String JSON_AMOUNT = "amount";
	private static final String JSON_TYPE= "type";
	private static final String JSON_FORM_PAYMENT = "form_payment";
	private static final String JSON_DATE = "date";
	private static final String JSON_COMMENT = "comment";
	
	
	
	public Spending(double amount, int type, int form_payment, Date d,String comment) {
		//super();
		this.mAmount = amount;
		this.mType = type;
		this.mFormPayment = form_payment;
		this.mDate = d;
		Log.d("SpendingLab","d: " + d.toString() + " mDate: " + this.mDate.toString());
		this.mComment = comment;
	}
	public Spending(JSONObject json) throws JSONException{
		mAmount = json.getInt(JSON_AMOUNT);
		mType = json.getInt(JSON_TYPE);
		mFormPayment = json.getInt(JSON_TYPE);
		SimpleDateFormat  format = new SimpleDateFormat("MM/dd/yyyy");
		try {
		    mDate = format.parse(json.getString(JSON_DATE));  
		} catch (ParseException e) {  
		    // TODO Auto-generated catch block  
		    e.printStackTrace();  
		}
		mComment = json.getString(JSON_COMMENT);
	}
	public JSONObject toJSON() throws JSONException{
		//mDate = new Date();
		JSONObject json = new JSONObject();
		json.put(JSON_AMOUNT, mAmount);
		json.put(JSON_TYPE, mType);
		json.put(JSON_FORM_PAYMENT, mFormPayment);
		SimpleDateFormat  formattter = new SimpleDateFormat("MM/dd/yyyy");
		json.put(JSON_DATE, formattter.format(mDate));
		json.put(JSON_COMMENT, mComment);
		Log.d("SpendingLab","Json; " + json.toString());
		return json;
	}
	
	public double getAmount() {
		return mAmount;
	}
	public int getType() {
		return mType;
	}
	public void setType(int type) {
		this.mType = type;
	}
	public int getForm_payment() {
		return mFormPayment;
	}
	public void setForm_payment(int form_payment) {
		this.mFormPayment = form_payment;
	}
	public Date getDate() {
		return mDate;
	}
	public void setDate(Date date) {
		this.mDate = date;
	}
	public void setAmount(double d) {
		this.mAmount = d;
	}
	public String getmComment() {
		return mComment;
	}
	public void setmComment(String mComment) {
		this.mComment = mComment;
	}	
}
